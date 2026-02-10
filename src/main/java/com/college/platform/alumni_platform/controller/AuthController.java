// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.dto.LoginRequest;
// import com.college.platform.alumni_platform.dto.RegisterRequest;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/v1/auth")
// @RequiredArgsConstructor
// public class AuthController {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;

//     @PostMapping("/register")
//     public String register(@RequestBody RegisterRequest request) {

//         User user = new User();
//         user.setName(request.getName());
//         user.setEmail(request.getEmail());
//         user.setPassword(passwordEncoder.encode(request.getPassword()));
//         user.setRole(User.Role.valueOf(request.getRole()));
//         user.setStatus(User.Status.PENDING);

//         userRepository.save(user);

//         return "Registered Successfully";
//     }

//     @PostMapping("/login")
//     public String login(@RequestBody LoginRequest request) {

//         User user = userRepository.findByEmail(request.getEmail())
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//             throw new RuntimeException("Invalid credentials");
//         }

//         return "Login successful";
//     }
// }
// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.config.JwtUtil;
// import com.college.platform.alumni_platform.dto.LoginRequest;
// import com.college.platform.alumni_platform.dto.RegisterRequest;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/v1/auth")
// @RequiredArgsConstructor
// public class AuthController {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtUtil jwtUtil;

//     // ================= REGISTER =================
//     @PostMapping("/register")
//     public String register(@RequestBody RegisterRequest request) {

//         // check if email already exists
//         if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//             throw new RuntimeException("Email already registered");
//         }

//         User user = new User();
//         user.setName(request.getName());
//         user.setEmail(request.getEmail());
//         user.setPassword(passwordEncoder.encode(request.getPassword()));
//         user.setRole(User.Role.valueOf(request.getRole()));
//         user.setStatus(User.Status.PENDING);

//         userRepository.save(user);

//         return "Registered Successfully";
//     }

//     // ================= LOGIN =================
//     @PostMapping("/login")
//     public String login(@RequestBody LoginRequest request) {

//         User user = userRepository.findByEmail(request.getEmail())
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//             throw new RuntimeException("Invalid credentials");
//         }

//         // generate JWT token
//         return jwtUtil.generateToken(user.getEmail());
//     }
// }
package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.config.JwtUtil;
import com.college.platform.alumni_platform.dto.LoginRequest;
import com.college.platform.alumni_platform.dto.RegisterRequest;
import com.college.platform.alumni_platform.entity.User;
import com.college.platform.alumni_platform.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // ✅ EXPLICIT CONSTRUCTOR (MANDATORY)
    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.valueOf(request.getRole()));
        user.setStatus(User.Status.PENDING);

        userRepository.save(user);
        return "Registered Successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
