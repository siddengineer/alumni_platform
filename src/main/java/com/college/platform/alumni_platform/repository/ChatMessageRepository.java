package com.college.platform.alumni_platform.repository;

import com.college.platform.alumni_platform.entity.Application;
import com.college.platform.alumni_platform.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // Get all messages for a specific application (thread), oldest first
    List<ChatMessage> findByApplicationOrderBySentAtAsc(Application application);
}