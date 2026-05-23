// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/api/v1/auth/**").permitAll()
//                 .anyRequest().authenticated()
//             )
//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return NoOpPasswordEncoder.getInstance(); // Only for dev/testing
//     }
// }

// package com.college.platform.alumni_platform.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     @Autowired
//     private JwtFilter jwtFilter;

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/admin/login").permitAll() // allow login without token
//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         // JWT filter only applies to authenticated endpoints
//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
// }
// package com.college.platform.alumni_platform.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     @Autowired
//     private JwtFilter jwtFilter;

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/admin/login").permitAll()

//                 .requestMatchers("/admin/**").hasRole("ADMIN")
//                 .requestMatchers("/student/**").hasRole("STUDENT")
//                 .requestMatchers("/alumni/**").hasRole("ALUMNI")

//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(
//             AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
// }


// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/login").permitAll()

//                 .requestMatchers("/admin/**").hasRole("ADMIN")
//                 .requestMatchers("/student/**").hasRole("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")

//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }
// }







// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 // Public endpoints
//                 .requestMatchers("/auth/login", "/auth/register").permitAll()

//                 // Admin APIs
//                 .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")

//                 // Student APIs
//                 .requestMatchers("/api/v1/students/**").hasRole("STUDENT")

//                 // Alumni APIs
//                 .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")

//                 // Everything else requires login
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         // JWT Filter
//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }
// }




// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/login", "/auth/register").permitAll()
//                 .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
//                 .requestMatchers("/api/v1/students/**").hasRole("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     // 🔥 ADD THIS
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }



// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/login", "/auth/register").permitAll()
//                 .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
//                 .requestMatchers("/api/v1/students/**").hasAuthority("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasAuthority("ALUMNI")
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }



// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 // Public endpoints
//                 .requestMatchers("/auth/login", "/auth/register").permitAll()

//                 // Role-based access
//                 .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
//                 .requestMatchers("/api/v1/students/**").hasAuthority("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasAuthority("ALUMNI")

//                 // All other requests require authentication
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }




// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**").permitAll()
//                 .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
//                 .requestMatchers("/api/v1/students/**").hasAuthority("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasAuthority("ALUMNI")
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }





// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableMethodSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**").permitAll()
//                 .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
//                 .requestMatchers("/api/v1/students/**").hasAuthority("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasAuthority("ALUMNI")
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     // 🔥 THIS WAS MISSING
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }

// @Configuration
// @EnableMethodSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             // .authorizeHttpRequests(auth -> auth
//             //     // .requestMatchers("/auth/**").permitAll()
//             //     .requestMatchers("/api/v1/alumni/**").permitAll()
//             //     .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
//             //     .requestMatchers("/api/v1/students/**").hasRole("STUDENT")
//             //     .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")
//             //     .anyRequest().authenticated()
//             // )
//             .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/auth/**").permitAll()
//                    .requestMatchers("/pay").permitAll()
//                    .requestMatchers("/razorpay/**").permitAll()
//                    .requestMatchers("/api/v1/alumni/jobs/payment/verify").permitAll()
//                    .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
//                    .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")
//                    .requestMatchers("/api/v1/student/**").hasRole("STUDENT")
//                    .anyRequest().authenticated()
//                )
//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             )

//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }




// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableMethodSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http

//             // Disable CSRF for REST API
//             .csrf(csrf -> csrf.disable())

//             // Route security
//             .authorizeHttpRequests(auth -> auth

//                 // PUBLIC
//                 .requestMatchers("/", "/index.html").permitAll()

//                 .requestMatchers("/auth/**").permitAll()

//                 .requestMatchers("/pay").permitAll()

//                 .requestMatchers("/razorpay/**").permitAll()

//                 .requestMatchers(
//                         "/api/v1/alumni/jobs/payment/verify"
//                 ).permitAll()

//                 // ADMIN
//                 .requestMatchers(
//                         "/api/v1/admin/**"
//                 ).hasRole("ADMIN")

//                 // ALUMNI
//                 .requestMatchers(
//                         "/api/v1/alumni/**"
//                 ).hasRole("ALUMNI")

//                 // STUDENT
//                 .requestMatchers(
//                         "/api/v1/student/**"
//                 ).hasRole("STUDENT")

//                 // REQUIRE LOGIN
//                 .anyRequest().authenticated()
//             )

//             // JWT → Stateless
//             .sessionManagement(session ->
//                     session.sessionCreationPolicy(
//                             SessionCreationPolicy.STATELESS
//                     )
//             )

//             // JWT FILTER
//             .addFilterBefore(
//                     jwtFilter,
//                     UsernamePasswordAuthenticationFilter.class
//             );

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }






// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import java.util.List;

// @Configuration
// @EnableMethodSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 // Static frontend files
//                 .requestMatchers("/", "/index.html", "/**.html", "/**.css", "/**.js").permitAll()
//                 // Auth endpoints - public
//                 .requestMatchers("/auth/**").permitAll()
//                 // Payment page - public
//                 .requestMatchers("/pay", "/razorpay/**").permitAll()
//                 // Payment verify - public (called after Razorpay redirects)
//                 .requestMatchers("/api/v1/alumni/jobs/payment/verify").permitAll()
//                 // Role-based
//                 .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
//                 .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")
//                 .requestMatchers("/api/v1/student/**").hasRole("STUDENT")
//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             )
//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     // ✅ CORS - allows frontend (localhost:8080 or any origin) to call the API
//     @Bean
//     public CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration config = new CorsConfiguration();
//         config.setAllowedOriginPatterns(List.of("*"));
//         config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         config.setAllowedHeaders(List.of("*"));
//         config.setAllowCredentials(true);
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", config);
//         return source;
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }




// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/api/v1/auth/**").permitAll()
//                 .anyRequest().authenticated()
//             )
//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return NoOpPasswordEncoder.getInstance(); // Only for dev/testing
//     }
// }

// package com.college.platform.alumni_platform.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     @Autowired
//     private JwtFilter jwtFilter;

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/admin/login").permitAll() // allow login without token
//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         // JWT filter only applies to authenticated endpoints
//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
// }
// package com.college.platform.alumni_platform.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     @Autowired
//     private JwtFilter jwtFilter;

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/admin/login").permitAll()

//                 .requestMatchers("/admin/**").hasRole("ADMIN")
//                 .requestMatchers("/student/**").hasRole("STUDENT")
//                 .requestMatchers("/alumni/**").hasRole("ALUMNI")

//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(
//             AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
// }


// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/login").permitAll()

//                 .requestMatchers("/admin/**").hasRole("ADMIN")
//                 .requestMatchers("/student/**").hasRole("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")

//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }
// }







// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 // Public endpoints
//                 .requestMatchers("/auth/login", "/auth/register").permitAll()

//                 // Admin APIs
//                 .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")

//                 // Student APIs
//                 .requestMatchers("/api/v1/students/**").hasRole("STUDENT")

//                 // Alumni APIs
//                 .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")

//                 // Everything else requires login
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         // JWT Filter
//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }
// }




// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/login", "/auth/register").permitAll()
//                 .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
//                 .requestMatchers("/api/v1/students/**").hasRole("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     // 🔥 ADD THIS
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }



// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/login", "/auth/register").permitAll()
//                 .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
//                 .requestMatchers("/api/v1/students/**").hasAuthority("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasAuthority("ALUMNI")
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }



// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 // Public endpoints
//                 .requestMatchers("/auth/login", "/auth/register").permitAll()

//                 // Role-based access
//                 .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
//                 .requestMatchers("/api/v1/students/**").hasAuthority("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasAuthority("ALUMNI")

//                 // All other requests require authentication
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }




// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**").permitAll()
//                 .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
//                 .requestMatchers("/api/v1/students/**").hasAuthority("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasAuthority("ALUMNI")
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }





// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableMethodSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**").permitAll()
//                 .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
//                 .requestMatchers("/api/v1/students/**").hasAuthority("STUDENT")
//                 .requestMatchers("/api/v1/alumni/**").hasAuthority("ALUMNI")
//                 .anyRequest().authenticated()
//             )

//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     // 🔥 THIS WAS MISSING
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }

// @Configuration
// @EnableMethodSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())

//             // .authorizeHttpRequests(auth -> auth
//             //     // .requestMatchers("/auth/**").permitAll()
//             //     .requestMatchers("/api/v1/alumni/**").permitAll()
//             //     .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
//             //     .requestMatchers("/api/v1/students/**").hasRole("STUDENT")
//             //     .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")
//             //     .anyRequest().authenticated()
//             // )
//             .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/auth/**").permitAll()
//                    .requestMatchers("/pay").permitAll()
//                    .requestMatchers("/razorpay/**").permitAll()
//                    .requestMatchers("/api/v1/alumni/jobs/payment/verify").permitAll()
//                    .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
//                    .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")
//                    .requestMatchers("/api/v1/student/**").hasRole("STUDENT")
//                    .anyRequest().authenticated()
//                )
//             .sessionManagement(session ->
//                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             )

//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }




// package com.college.platform.alumni_platform.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableMethodSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http

//             // Disable CSRF for REST API
//             .csrf(csrf -> csrf.disable())

//             // Route security
//             .authorizeHttpRequests(auth -> auth

//                 // PUBLIC
//                 .requestMatchers("/", "/index.html").permitAll()

//                 .requestMatchers("/auth/**").permitAll()

//                 .requestMatchers("/pay").permitAll()

//                 .requestMatchers("/razorpay/**").permitAll()

//                 .requestMatchers(
//                         "/api/v1/alumni/jobs/payment/verify"
//                 ).permitAll()

//                 // ADMIN
//                 .requestMatchers(
//                         "/api/v1/admin/**"
//                 ).hasRole("ADMIN")

//                 // ALUMNI
//                 .requestMatchers(
//                         "/api/v1/alumni/**"
//                 ).hasRole("ALUMNI")

//                 // STUDENT
//                 .requestMatchers(
//                         "/api/v1/student/**"
//                 ).hasRole("STUDENT")

//                 // REQUIRE LOGIN
//                 .anyRequest().authenticated()
//             )

//             // JWT → Stateless
//             .sessionManagement(session ->
//                     session.sessionCreationPolicy(
//                             SessionCreationPolicy.STATELESS
//                     )
//             )

//             // JWT FILTER
//             .addFilterBefore(
//                     jwtFilter,
//                     UsernamePasswordAuthenticationFilter.class
//             );

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }






package com.college.platform.alumni_platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Static frontend files
                .requestMatchers("/", "/index.html", "/**.html", "/**.css", "/**.js").permitAll()
                // Auth endpoints - public
                .requestMatchers("/auth/**").permitAll()
                // Payment page - public
                .requestMatchers("/pay", "/razorpay/**").permitAll()
                // Payment verify - public (called after Razorpay redirects)
                .requestMatchers("/api/v1/alumni/jobs/payment/verify").permitAll()
                // Role-based
                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/alumni/**").hasRole("ALUMNI")
                // GET /api/v1/student/jobs is open to any authenticated user (student, alumni, admin)
                // so that approved jobs are visible regardless of role token
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/v1/student/jobs").authenticated()
                // All other student endpoints remain STUDENT-only
                .requestMatchers("/api/v1/student/**").hasRole("STUDENT")
                .anyRequest().authenticated()
            )
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ CORS - allows frontend (localhost:8080 or any origin) to call the API
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}