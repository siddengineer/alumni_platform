// package com.college.platform.alumni_platform.controller;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class RoleTestController {

//     @GetMapping("/admin/test")
//     public String adminTest() {
//         return "ADMIN endpoint accessed";
//     }

//     @GetMapping("/student/test")
//     public String studentTest() {
//         return "STUDENT endpoint accessed";
//     }

//     @GetMapping("/alumni/test")
//     public String alumniTest() {
//         return "ALUMNI endpoint accessed";
//     }
// }
package com.college.platform.alumni_platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleTestController {

    @GetMapping("/admin/test")
    public String adminTest() {
        return "ADMIN endpoint accessed";
    }

    @GetMapping("/student/test")
    public String studentTest() {
        return "STUDENT endpoint accessed";
    }

    @GetMapping("/alumni/test")
    public String alumniTest() {
        return "ALUMNI endpoint accessed";
    }
}