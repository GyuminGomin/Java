package com.edu.board.dto;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String userId;
    private String password;
    private String title;
    private String content;
}
