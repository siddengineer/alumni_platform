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
package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.config.JwtUtil;
import com.college.platform.alumni_platform.entity.User;
import com.college.platform.alumni_platform.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        User user = userRepository.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(403)
                    .body(Map.of("error", "Invalid credentials"));
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "role", user.getRole(),
                        "message", user.getRole() + " login successful"
                )
        );
    }
}