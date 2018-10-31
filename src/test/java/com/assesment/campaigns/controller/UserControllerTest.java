package com.assesment.campaigns.controller;

import com.assesment.campaigns.domain.User;
import com.assesment.campaigns.service.CampaignService;
import com.assesment.campaigns.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private CampaignService campaignService;

    @Test
    public void signupUser_succeed_loginWithCorrectCredentials_succeed() throws Exception {
        given(userService.saveUser(any(User.class))).willReturn(new User("10000001", "test", "1234abc"));
        String requestBody = "{\"username\":\"test\",\"password\":\"1234abc\"}";

        MvcResult mvcResultSignup = mockMvc.perform(MockMvcRequestBuilders
                .post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk()).andReturn();

        Assert.assertEquals(mvcResultSignup.getResponse().getContentAsString(), "true");

        given(userService.getUser(any(User.class))).willReturn(new User("10000001", "test", "1234abc"));

        MvcResult mvcResultLogin = mockMvc.perform(MockMvcRequestBuilders
                .post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk()).andReturn();

        Assert.assertEquals(mvcResultLogin.getResponse().getContentAsString(), "true");
    }
}
