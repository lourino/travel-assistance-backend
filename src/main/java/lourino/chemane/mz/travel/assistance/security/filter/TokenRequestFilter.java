package lourino.chemane.mz.travel.assistance.security.filter;

import lourino.chemane.mz.travel.assistance.TravelAssistanceApp;
import lourino.chemane.mz.travel.assistance.security.service.impl.UserServiceImpl;
import lourino.chemane.mz.travel.assistance.security.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lourino Chemane
 */
public class TokenRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            username = JwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserServiceImpl userService = TravelAssistanceApp.getBean(UserServiceImpl.class);
            UserDetails user = userService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities()
                    );

            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
