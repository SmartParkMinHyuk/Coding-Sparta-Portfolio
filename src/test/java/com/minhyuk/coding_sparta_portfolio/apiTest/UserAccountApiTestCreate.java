package com.minhyuk.coding_sparta_portfolio.apiTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minhyuk.coding_sparta_portfolio.api.UserAccountApi;
import com.minhyuk.coding_sparta_portfolio.dto.UserAccountDto.UserAccountCreateReq;
import com.minhyuk.coding_sparta_portfolio.service.UserAccountService;

@SpringBootTest
@AutoConfigureMockMvc
class UserAccountApiTestCreate {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserAccountService userAccountService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // ✅ `Mockito.when(...).thenReturn(null);` -> `doNothing()`으로 변경
        Mockito.doNothing().when(userAccountService).create(Mockito.any());
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testLogEndpoint() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "guest", roles = "GUEST")
    void testCreateUser() throws Exception {
        String jsonRequest = "{\"email\":\"email1@example.com\",\"password\":\"password\",\"name\":\"username\"}";

        mockMvc.perform(post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "guest", roles = "GUEST")
    void testCreateUserWithInvalidEmail() throws Exception {
        String jsonRequest = "{\"email\":\"invalid-email\",\"password\":\"password\",\"name\":\"username\"}";

        mockMvc.perform(post("/user/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
                .andExpect(jsonPath("$.message").value("Invalid email format"));
    }

       @Test
       @WithMockUser(username = "guest", roles = "GUEST")
       void testCreateUserWithEmptyFields() throws Exception {
           String jsonRequest = "{\"email\":\"\",\"password\":\"password\",\"name\":\"username\"}";

           mockMvc.perform(post("/user/signup")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(jsonRequest))
                   .andExpect(jsonPath("$.message").value("Email required"));
       }

}



