package com.edu.board.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.edu.board.domain.User;
import com.edu.board.dto.CreatePostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest // SpringBoot 위에서 돌아가는 테스트 선언
@AutoConfigureMockMvc // Mock 객체를 만드는것을 자동으로 설정
public class UserControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    private final String ControllerURL = "/posts";

    @Test
    void postTest() throws Exception {
        // Test를 위한 사용자 생성
        User user = new User();
        user.setUserId("test1");
        user.setPassword("test");
        user.setUserName("testman");

        // MockMvc를 통한 검증
        mvc.perform(MockMvcRequestBuilders
                .post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").exists())
            .andDo(print());
            
        // Test를 위한 게시글 생성
        CreatePostRequest postRequest = new CreatePostRequest();
        
        // 사용자 정보 + 게시글제목, 내용
        postRequest.setUserId("test1");
        postRequest.setPassword("test");
        postRequest.setTitle("TEST");
        postRequest.setContent("This is Test");

        // Test를 위한 게시글 생성
        mvc.perform(MockMvcRequestBuilders
                .post(ControllerURL)
                .content(objectMapper.writeValueAsString(postRequest))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").exists())
            .andDo(print());
    }


}