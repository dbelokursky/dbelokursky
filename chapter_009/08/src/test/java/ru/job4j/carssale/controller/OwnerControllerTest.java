package ru.job4j.carssale.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.carssale.service.OwnerService;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {

    @MockBean
    OwnerService ownerService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getOwnersList() throws Exception {
        given(this.ownerService.findAll()).willReturn(new ArrayList<>());
        this.mockMvc.perform(get("/owners")
                .with(user("owner2").roles("ADMIN"))
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("ownersList"));
        verify(this.ownerService, times(1)).findAll();
    }

    @Test
    public void getAccessDeniedPage() throws Exception {
        given(this.ownerService.findAll()).willReturn(new ArrayList<>());
        this.mockMvc.perform(get("/owners")
                .with(user("owner1").roles("USER"))
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is4xxClientError())
                .andExpect(forwardedUrl("/accessdenied"));
    }
}