// package com.college.platform.alumni_platform.config;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collections;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     private final JwtUtil jwtUtil;

//     public JwtFilter(JwtUtil jwtUtil) {
//         this.jwtUtil = jwtUtil;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain) throws ServletException, IOException {

//         final String authHeader = request.getHeader("Authorization");

//         String username = null;
//         String jwt = null;

//         if (authHeader != null && authHeader.startsWith("Bearer ")) {
//             jwt = authHeader.substring(7);
//             try {
//                 username = jwtUtil.extractUsername(jwt);
//             } catch (Exception e) {
//                 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                 response.getWriter().write("Invalid JWT Token");
//                 return;
//             }
//         }

//         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//             if (jwtUtil.validateToken(jwt, username)) {
//                 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                         username, null, Collections.emptyList() // roles can be added here
//                 );
//                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(authToken);
//             }
//         }

//         filterChain.doFilter(request, response);
//     }
// }
// package com.college.platform.alumni_platform.config;

// import io.jsonwebtoken.Claims;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collections;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     @Autowired
//     private JwtUtil jwtUtil;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {

//         String path = request.getRequestURI();

//         // ✅ allow login without token
//         if (path.equals("/admin/login")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             response.setStatus(403);
//             response.getWriter().write("{\"error\":\"Missing token\"}");
//             return;
//         }

//         String token = authHeader.substring(7);

//         try {
//             Claims claims = jwtUtil.validateToken(token);
//             String email = claims.getSubject();

//             UsernamePasswordAuthenticationToken authentication =
//                     new UsernamePasswordAuthenticationToken(
//                             email,
//                             null,
//                             Collections.emptyList()
//                     );

//             SecurityContextHolder.getContext().setAuthentication(authentication);

//         } catch (Exception e) {
//             response.setStatus(403);
//             response.getWriter().write("{\"error\":\"Invalid or expired token\"}");
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }

// package com.college.platform.alumni_platform.config;

// import io.jsonwebtoken.Claims;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.List;
// import java.util.Set;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     @Autowired
//     private JwtUtil jwtUtil; // ✅ USE SAME KEY

//     private static final Set<String> WHITELIST = Set.of(
//             "/admin/login",
//             "/swagger-ui",
//             "/v3/api-docs"
//     );

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {

//         String path = request.getRequestURI();

//         for (String open : WHITELIST) {
//             if (path.contains(open)) {
//                 filterChain.doFilter(request, response);
//                 return;
//             }
//         }

//         String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             response.getWriter().write("{\"error\":\"Missing token\"}");
//             return;
//         }

//         String token = authHeader.substring(7);

//         try {
//             Claims claims = jwtUtil.validateToken(token);

//             String email = claims.getSubject();
//             String role = claims.get("role", String.class);

//             UsernamePasswordAuthenticationToken authToken =
//                     new UsernamePasswordAuthenticationToken(
//                             email,
//                             null,
//                             List.of(new SimpleGrantedAuthority("ROLE_" + role))
//                     );

//             authToken.setDetails(
//                     new WebAuthenticationDetailsSource().buildDetails(request)
//             );

//             SecurityContextHolder.getContext().setAuthentication(authToken);

//         } catch (Exception e) {
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             response.getWriter().write("{\"error\":\"Invalid or expired token\"}");
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }


// package com.college.platform.alumni_platform.config;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.nio.charset.StandardCharsets;
// import java.util.List;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     private static final String SECRET =
//             "THIS_IS_A_VERY_LONG_256_BIT_SECRET_KEY_FOR_HS256_ALGORITHM";

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {

//         String path = request.getRequestURI();

//         // ✅ DO NOT CHECK JWT FOR LOGIN APIs
//         if (path.equals("/admin/login")
//                 || path.equals("/student/login")
//                 || path.equals("/alumni/login")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             response.getWriter().write("{\"error\":\"Missing token\"}");
//             return;
//         }

//         String token = authHeader.substring(7);

//         try {
//             Claims claims = Jwts.parserBuilder()
//                     .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
//                     .build()
//                     .parseClaimsJws(token)
//                     .getBody();

//             String email = claims.getSubject();
//             String role = claims.get("role", String.class);

//             UsernamePasswordAuthenticationToken authToken =
//                     new UsernamePasswordAuthenticationToken(
//                             email,
//                             null,
//                             List.of(new SimpleGrantedAuthority("ROLE_" + role))
//                     );

//             authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//             SecurityContextHolder.getContext().setAuthentication(authToken);

//         } catch (Exception e) {
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             response.getWriter().write("{\"error\":\"Invalid or expired token\"}");
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }

package com.college.platform.alumni_platform.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // ONLY login is public
    private static final Set<String> WHITELIST = Set.of(
            "/auth/login"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        for (String open : WHITELIST) {
            if (path.startsWith(open)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Missing token");
            return;
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = jwtUtil.validateToken(token);
            String email = claims.getSubject();
            String role = claims.get("role", String.class);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + role))
                    );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Invalid or expired token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}