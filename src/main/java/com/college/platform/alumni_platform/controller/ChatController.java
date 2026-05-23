package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.entity.Application;
import com.college.platform.alumni_platform.entity.ChatMessage;
import com.college.platform.alumni_platform.entity.User;
import com.college.platform.alumni_platform.repository.ApplicationRepository;
import com.college.platform.alumni_platform.repository.ChatMessageRepository;
import com.college.platform.alumni_platform.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatMessageRepository chatRepo;
    private final ApplicationRepository applicationRepo;
    private final UserRepository userRepo;

    public ChatController(ChatMessageRepository chatRepo,
                          ApplicationRepository applicationRepo,
                          UserRepository userRepo) {
        this.chatRepo = chatRepo;
        this.applicationRepo = applicationRepo;
        this.userRepo = userRepo;
    }

    // GET /api/v1/chat/{applicationId}  — fetch all messages for this application
    @GetMapping("/{applicationId}")
    @Transactional(readOnly = true)
    public ResponseEntity<?> getMessages(@PathVariable Long applicationId,
                                         Authentication auth) {
        Application app = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        // Only the student or the alumni who owns the job can read the chat
        String email = auth.getName();
        boolean isStudent = app.getStudent().getEmail().equals(email);
        boolean isAlumni  = app.getJob().getAlumni().getEmail().equals(email);
        if (!isStudent && !isAlumni) {
            return ResponseEntity.status(403).body(Map.of("error", "Not authorized"));
        }

        List<ChatMessage> messages = chatRepo.findByApplicationOrderBySentAtAsc(app);
        return ResponseEntity.ok(messages);
    }

    // POST /api/v1/chat/{applicationId}  — send a message
    @PostMapping("/{applicationId}")
    @Transactional
    public ResponseEntity<?> sendMessage(@PathVariable Long applicationId,
                                          @RequestBody Map<String, String> body,
                                          Authentication auth) {
        String text = body.get("message");
        if (text == null || text.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Message cannot be empty"));
        }

        Application app = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        String email = auth.getName();
        boolean isStudent = app.getStudent().getEmail().equals(email);
        boolean isAlumni  = app.getJob().getAlumni().getEmail().equals(email);
        if (!isStudent && !isAlumni) {
            return ResponseEntity.status(403).body(Map.of("error", "Not authorized"));
        }

        User sender = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ChatMessage msg = new ChatMessage();
        msg.setApplication(app);
        msg.setSender(sender);
        msg.setMessage(text.trim());

        ChatMessage saved = chatRepo.save(msg);
        return ResponseEntity.ok(saved);
    }
}