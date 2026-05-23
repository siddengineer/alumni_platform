// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.config.JwtUtil;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import lombok.Data;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;

// @RestController
// @RequestMapping("/api/v1/auth")
// @CrossOrigin
// public class AuthController {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtUtil jwtUtil;

//     public AuthController(UserRepository userRepository,
//                           PasswordEncoder passwordEncoder,
//                           JwtUtil jwtUtil) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//         this.jwtUtil = jwtUtil;
//     }

//     // ================= REGISTER =================
//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody User user) {

//         if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//             return ResponseEntity.badRequest().body("Email already exists");
//         }

//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         user.setStatus(User.Status.PENDING);

//         userRepository.save(user);

//         return ResponseEntity.ok("Registration successful. Await admin approval.");
//     }

//     // ================= LOGIN =================
//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody LoginRequest request) {

//         User user = userRepository.findByEmail(request.getEmail())
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                     .body("Invalid credentials");
//         }

//         if (user.getStatus() != User.Status.APPROVED) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                     .body("Account not approved");
//         }

//         String token = jwtUtil.generateToken(
//                 user.getEmail(),
//                 user.getRole().name()
//         );

//         return ResponseEntity.ok(
//                 Map.of(
//                         "message", "Login successful",
//                         "token", token,
//                         "role", user.getRole().name()
//                 )
//         );
//     }

//     // ================= DTO =================
//     @Data
//     static class LoginRequest {
//         private String email;
//         private String password;
//     }
// }
// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.config.JwtUtil;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/v1/auth")
// public class AuthController {

//     private final UserRepository userRepository;
//     private final JwtUtil jwtUtil;

//     public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
//         this.userRepository = userRepository;
//         this.jwtUtil = jwtUtil;
//     }

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody Map<String, String> userMap) {
//         String email = userMap.get("email");
//         String password = userMap.get("password");

//         User user = userRepository.findByEmail(email);
//         if (user == null || !user.getPassword().equals(password)) {
//             return ResponseEntity.status(401).body("Invalid credentials");
//         }

//         String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

//         Map<String, Object> response = new HashMap<>();
//         response.put("token", token);
//         response.put("role", user.getRole());
//         response.put("message", "Login successful");

//         return ResponseEntity.ok(response);
//     }
// }
// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.config.JwtUtil;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;

// @RestController
// @RequestMapping("/admin")
// public class AuthController {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private JwtUtil jwtUtil;

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

//         String email = request.get("email");
//         String password = request.get("password");

//         User user = userRepository.findByEmail(email);

//         if (user == null || !user.getPassword().equals(password)) {
//             return ResponseEntity.status(403)
//                     .body(Map.of("error", "Invalid credentials"));
//         }

//         String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

//         return ResponseEntity.ok(
//                 Map.of(
//                         "token", token,
//                         "role", user.getRole(),
//                         "message", "Login successful"
//                 )
//         );
//     }
// }
// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.config.JwtUtil;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final UserRepository userRepository;
//     private final JwtUtil jwtUtil;

//     public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
//         this.userRepository = userRepository;
//         this.jwtUtil = jwtUtil;
//     }

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

//         String email = request.get("email");
//         String password = request.get("password");

//         User user = userRepository.findByEmail(email);

//         if (user == null || !user.getPassword().equals(password)) {
//             return ResponseEntity.status(403)
//                     .body(Map.of("error", "Invalid credentials"));
//         }

//         String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

//         return ResponseEntity.ok(
//                 Map.of(
//                         "token", token,
//                         "role", user.getRole(),
//                         "message", user.getRole() + " login successful"
//                 )
//         );
//     }
// }


// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.config.JwtUtil;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final UserRepository userRepository;
//     private final JwtUtil jwtUtil;
//     private final PasswordEncoder passwordEncoder;

//     public AuthController(UserRepository userRepository,
//                           JwtUtil jwtUtil,
//                           PasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.jwtUtil = jwtUtil;
//         this.passwordEncoder = passwordEncoder;
//     }

//     // =========================
//     // REGISTER
//     // =========================
//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody Map<String, String> request) {

//         String email = request.get("email");
//         String password = request.get("password");
//         String role = request.get("role");

//         if (email == null || password == null || role == null) {
//             return ResponseEntity.badRequest()
//                     .body(Map.of("error", "Email, password and role are required"));
//         }

//         if (userRepository.findByEmail(email).isPresent()) {
//             return ResponseEntity.badRequest()
//                     .body(Map.of("error", "Email already exists"));
//         }

//         User user = new User();
//         user.setEmail(email);
//         user.setPassword(passwordEncoder.encode(password)); // 🔥 BCrypt encoding
//         user.setRole(role);

//         userRepository.save(user);

//         return ResponseEntity.ok(
//                 Map.of("message", "User registered successfully")
//         );
//     }

//     // =========================
//     // LOGIN
//     // =========================
//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

//         String email = request.get("email");
//         String password = request.get("password");

//         if (email == null || password == null) {
//             return ResponseEntity.badRequest()
//                     .body(Map.of("error", "Email and password are required"));
//         }

//         User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         if (!passwordEncoder.matches(password, user.getPassword())) {
//             return ResponseEntity.status(403)
//                     .body(Map.of("error", "Invalid credentials"));
//         }

//         String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

//         return ResponseEntity.ok(
//                 Map.of(
//                         "token", token,
//                         "role", user.getRole(),
//                         "message", user.getRole() + " login successful"
//                 )
//         );
//     }
// }



// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.config.JwtUtil;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;
// import java.util.Set;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final UserRepository userRepository;
//     private final JwtUtil jwtUtil;
//     private final PasswordEncoder passwordEncoder;

//     private static final Set<String> ALLOWED_ROLES =
//             Set.of("ADMIN", "STUDENT", "ALUMNI");

//     public AuthController(UserRepository userRepository,
//                           JwtUtil jwtUtil,
//                           PasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.jwtUtil = jwtUtil;
//         this.passwordEncoder = passwordEncoder;
//     }

//     // =========================
//     // REGISTER
//     // =========================
//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody Map<String, String> request) {

//         String email = request.get("email");
//         String password = request.get("password");
//         String role = request.get("role");

//         if (email == null || password == null || role == null) {
//             return ResponseEntity.badRequest()
//                     .body(Map.of("error", "Email, password and role are required"));
//         }

//         role = role.toUpperCase();

//         if (!ALLOWED_ROLES.contains(role)) {
//             return ResponseEntity.badRequest()
//                     .body(Map.of("error", "Invalid role. Use ADMIN, STUDENT or ALUMNI"));
//         }

//         if (userRepository.findByEmail(email).isPresent()) {
//             return ResponseEntity.badRequest()
//                     .body(Map.of("error", "Email already exists"));
//         }

//         User user = new User();
//         user.setEmail(email);
//         user.setPassword(passwordEncoder.encode(password));
//         user.setRole(role);

//         userRepository.save(user);

//         return ResponseEntity.ok(
//                 Map.of("message", "User registered successfully")
//         );
//     }

//     // =========================
//     // LOGIN
//     // =========================
//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

//         String email = request.get("email");
//         String password = request.get("password");

//         if (email == null || password == null) {
//             return ResponseEntity.badRequest()
//                     .body(Map.of("error", "Email and password are required"));
//         }

//         User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         if (!passwordEncoder.matches(password, user.getPassword())) {
//             return ResponseEntity.status(403)
//                     .body(Map.of("error", "Invalid credentials"));
//         }

//         String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

//         return ResponseEntity.ok(
//                 Map.of(
//                         "token", token,
//                         "role", user.getRole(),
//                         "message", user.getRole() + " login successful"
//                 )
//         );
//     }
// }


package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.config.JwtUtil;
import com.college.platform.alumni_platform.entity.User;
import com.college.platform.alumni_platform.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private static final Set<String> ALLOWED_ROLES = Set.of("ADMIN", "STUDENT", "ALUMNI");

    public AuthController(UserRepository userRepository,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // ── REGISTER ──
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String email    = request.get("email");
        String password = request.get("password");
        String role     = request.get("role");
        String name     = request.get("name"); // ← now read from request

        if (email == null || password == null || role == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "email, password and role are required"));
        }

        role = role.toUpperCase();
        if (!ALLOWED_ROLES.contains(role)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid role. Use: STUDENT, ALUMNI, or ADMIN"));
        }

        if (password.length() < 6) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Password must be at least 6 characters"));
        }

        // ← FIX: block duplicate email registration
        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Email already registered"));
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setName(name);           // ← FIX: save the name
        user.setStatus("ACTIVE");     // ← FIX: set default status

        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Registered successfully as " + role));
    }

    // ── LOGIN ──
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email    = request.get("email");
        String password = request.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "email and password are required"));
        }

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return ResponseEntity.status(401)
                    .body(Map.of("error", "No account found with this email"));
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401)
                    .body(Map.of("error", "Incorrect password"));
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return ResponseEntity.ok(Map.of(
                "token",   token,
                "role",    user.getRole(),
                "email",   user.getEmail(),
                "name",    user.getName() != null ? user.getName() : "",  // ← also return name
                "id",      user.getId(),
                "message", "Login successful"
        ));
    }
}