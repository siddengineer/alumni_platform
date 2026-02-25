// package com.college.platform.alumni_platform.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "users")
// public class User {

//     public enum Role {
//         ADMIN, ALUMNI, STUDENT
//     }

//     public enum Status {
//         PENDING, APPROVED
//     }

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;
//     private String email;
//     private String password;

//     @Enumerated(EnumType.STRING)
//     private Role role;

//     @Enumerated(EnumType.STRING)
//     private Status status;

//     public Long getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public Role getRole() {
//         return role;
//     }

//     public void setRole(Role role) {
//         this.role = role;
//     }

//     public Status getStatus() {
//         return status;
//     }

//     public void setStatus(Status status) {
//         this.status = status;
//     }
// }
// package com.college.platform.alumni_platform.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "users")
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, unique = true)
//     private String username;

//     @Column(nullable = false, unique = true)
//     private String email;

//     @Column(nullable = false)
//     private String password;

//     private String role;

//     // Constructors
//     public User() {}

//     public User(String username, String email, String password, String role) {
//         this.username = username;
//         this.email = email;
//         this.password = password;
//         this.role = role;
//     }

//     // Getters and setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getUsername() { return username; }
//     public void setUsername(String username) { this.username = username; }

//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }

//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }

//     public String getRole() { return role; }
//     public void setRole(String role) { this.role = role; }
// }
// package com.college.platform.alumni_platform.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "users")
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique = true)
//     private String email;

//     private String password;

//     private String role; // e.g., ADMIN or USER

//     // Getters and setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }

//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }

//     public String getRole() { return role; }
//     public void setRole(String role) { this.role = role; }
// }



package com.college.platform.alumni_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @JsonIgnore   // 🔥 VERY IMPORTANT
    private String password;

    private String role; // e.g., ADMIN or ALUMNI or STUDENT

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}