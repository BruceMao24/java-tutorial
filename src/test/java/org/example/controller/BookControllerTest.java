package org.example.controller;

import org.example.common.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * BookController 的接口测试：用 MockMvc 模拟 HTTP 请求，不启动真实端口
 */
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listShouldReturnResult() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ErrorCode.SUCCESS.getCode()))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void createWithInvalidBodyShouldReturnParamInvalid() throws Exception {
        // 缺 title、price 为负 → 触发参数校验，业务码应为 PARAM_INVALID(10001)
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"price\":-1}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ErrorCode.PARAM_INVALID.getCode()));
    }

    @Test
    void updateMissingBookShouldReturnBookNotFound() throws Exception {
        // id=999999 不存在 → 更新失败，业务码应为 BOOK_NOT_FOUND(20001)
        mockMvc.perform(put("/books/999999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"x\",\"price\":1.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ErrorCode.BOOK_NOT_FOUND.getCode()));
    }
}
