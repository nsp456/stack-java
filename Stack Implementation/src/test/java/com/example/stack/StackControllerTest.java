package com.example.stack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPush() throws Exception {
        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 1}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 2}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 3}"))
                .andExpect(status().isOk());
                        
        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 4}"))
                .andExpect(status().isOk());
        
        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 5}"))
                .andExpect(status().isOk());

        // Test stack overflow
        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 6}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testPop() throws Exception {
        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 1}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/pop"))
                .andExpect(status().isOk());

        // Test stack underflow
        mockMvc.perform(post("/pop"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testPeek() throws Exception {
        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 1}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/peek"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        mockMvc.perform(post("/pop"))
                .andExpect(status().isOk());

        // Test stack underflow
        mockMvc.perform(get("/peek"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSetSize() throws Exception {
        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 1}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 2}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 3}"))
                .andExpect(status().isOk());

        // Test setting size smaller than current stack size
        mockMvc.perform(post("/setSize")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 2}"))
                .andExpect(status().isBadRequest());

        // Test setting size larger than current stack size
        mockMvc.perform(post("/setSize")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 5}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSize() throws Exception {
        mockMvc.perform(get("/getSize"))
                .andExpect(status().isOk())
                .andExpect(content().string("5")); // Assuming initial size is 5
    }

    @Test
    void testGetStack() throws Exception {
        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 1}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 2}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 3}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/stack"))
                .andExpect(status().isOk())
                .andExpect(content().json("[1, 2, 3]"));
    }
}