package com.khayrullin.sarafan.service;

import com.khayrullin.sarafan.domain.Comment;
import com.khayrullin.sarafan.domain.User;
import com.khayrullin.sarafan.domain.Views;
import com.khayrullin.sarafan.dto.EventType;
import com.khayrullin.sarafan.dto.ObjectType;
import com.khayrullin.sarafan.repo.CommentRepo;
import com.khayrullin.sarafan.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService  {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}
