// package com.college.platform.alumni_platform.repository;

// import com.college.platform.alumni_platform.entity.User;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

// public interface UserRepository extends JpaRepository<User, Long> {

//     Optional<User> findByEmail(String email);
// }
// package com.college.platform.alumni_platform.repository;

// import com.college.platform.alumni_platform.entity.User;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface UserRepository extends JpaRepository<User, Long> {
//     User findByEmail(String email);
// }


// package com.college.platform.alumni_platform.repository;

// import com.college.platform.alumni_platform.entity.User;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.Optional;

// @Repository
// public interface UserRepository extends JpaRepository<User, Long> {

//     Optional<User> findByEmail(String email);
// }





package com.college.platform.alumni_platform.repository;

import com.college.platform.alumni_platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // Admin: filter users by role
    List<User> findByRole(String role);

    // Admin: count users by role for stats
    long countByRole(String role);
}