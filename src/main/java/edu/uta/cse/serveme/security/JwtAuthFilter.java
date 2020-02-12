package edu.uta.cse.serveme.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import edu.uta.cse.serveme.constant.Constant;
import edu.uta.cse.serveme.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author housirvip
 */
public class JwtAuthFilter extends BasicAuthenticationFilter {

    private final JwtUtils jwtUtils;

    JwtAuthFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {

        super(authenticationManager);
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String token = request.getHeader(Constant.AUTHORIZATION);

        // if header of Authorization contains nothing or not start with 'Bearer '
        if (token == null || !token.startsWith(Constant.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // jwt verify failed
        DecodedJWT jwt = jwtUtils.decode(token.replace(Constant.TOKEN_PREFIX, ""));
        if (jwt == null) {
            chain.doFilter(request, response);
            return;
        }

        Long uid = jwt.getClaim(Constant.UID).asLong();
        String[] role = jwt.getClaim(Constant.ROLE).asArray(String.class);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(uid, null,
                        AuthorityUtils.createAuthorityList(role)));

        chain.doFilter(request, response);
    }
}
