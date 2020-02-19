package edu.uta.cse.serveme.security;

import edu.uta.cse.serveme.constant.ErrorMessage;
import edu.uta.cse.serveme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * @author housirvip
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) ->
                        // rejected because of UNAUTHORIZED, user should login or has some role rather than annoymous
                        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorMessage.UNAUTHORIZED))
                .and().authorizeRequests()
                // below path all permitted, needn't to check authorization
                .antMatchers("/actuator/**", "/auth/**", "/noauth/**").permitAll()
                .antMatchers("/**").authenticated()
                // path start with '/admin' should has role ADMIN or ROOT
                // .antMatchers("/admin/**").hasAnyRole("ADMIN", "ROOT")
                .and().addFilter(new JwtAuthFilter(authenticationManager(), userService));
    }
}
