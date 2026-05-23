package com.college.platform.alumni_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The job/application this chat belongs to
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id")
    @JsonIgnoreProperties({"student", "job", "resumeLink"})
    private Application application;

    // Who sent this message
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    @JsonIgnoreProperties({"password"})
    private User sender;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    private LocalDateTime sentAt;

    public ChatMessage() {
        this.sentAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public Application getApplication() { return application; }
    public void setApplication(Application application) { this.application = application; }

    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getSentAt() { return sentAt; }
}