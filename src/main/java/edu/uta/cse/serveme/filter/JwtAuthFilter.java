package edu.uta.cse.serveme.filter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import edu.uta.cse.serveme.base.Constant;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.UserRole;
import edu.uta.cse.serveme.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author housirvip
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(Constant.AUTHORIZATION);

        // if header of Authorization contains nothing or not start with 'Bearer '
        if (token == null || !token.startsWith(Constant.TOKEN_PREFIX) || token.equals(Constant.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // firebaseToken verify failed
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseToken firebaseToken = null;
        try {
            firebaseToken = firebaseAuth.verifyIdToken(token.replace(Constant.TOKEN_PREFIX, ""));
        } catch (FirebaseAuthException e) {
            log.warn("FirebaseAuthException: {}", e.getMessage());
        }
        if (firebaseToken == null) {
            chain.doFilter(request, response);
            return;
        }

        String firebaseTokenUid = firebaseToken.getUid();
        User user = userService.findUserByFirebaseUid(firebaseTokenUid);
        if (user == null) {
            chain.doFilter(request, response);
            return;
        }
        List<UserRole> roleList = user.getRole();

        List<GrantedAuthority> authorities = new ArrayList<>();
        roleList.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
        UsernamePasswordAuthenticationToken authorization = new UsernamePasswordAuthenticationToken(user.getId(), firebaseTokenUid, authorities);
        authorization.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(authorization);

        chain.doFilter(request, response);
    }
}
