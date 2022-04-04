package com.khayrullin.sarafan.repo;

import com.khayrullin.sarafan.domain.Message;
import com.khayrullin.sarafan.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    @EntityGraph(attributePaths = "comments")
    Page<Message> findByAuthorIn(List<User> users, Pageable pageable);
}
