package com.khayrullin.sarafan.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.khayrullin.sarafan.domain.Message;
import com.khayrullin.sarafan.domain.Views;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonView(Views.FullMessage.class)
public class MessagePageDto {
    private List<Message> messages;
    private int curPage;
    private int totalPages;
}
