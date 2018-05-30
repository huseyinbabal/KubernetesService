package com.huseyin.kubernetes.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.huseyin.kubernetes.dto.ScaleResponseDto;
import com.huseyin.kubernetes.service.ApplicationService;
import io.fabric8.kubernetes.client.KubernetesClientException;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
@AutoConfigureMockMvc(secure = false)
public class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    @Test
    public void shouldScaleWhenValidResourceNameProvided() throws Exception {
        when(applicationService.scale("nginx", 5)).thenReturn(new ScaleResponseDto("Scale operation initiated"));
        this.mockMvc.perform(get("/app/scale?name=nginx&replicas=5")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("data", Matchers.is("Scale operation initiated")));
    }

    @Test
    public void shouldThrowWhenResourceNotFound() throws Exception {
        KubernetesClientException exception = new KubernetesClientException("Resource not found");
        when(applicationService.scale("nginx1", 5)).thenThrow(exception);
        this.mockMvc.perform(get("/app/scale?name=nginx1&replicas=5")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError())
                .andExpect(jsonPath("userMessage", Matchers.is("Resource not found")));
    }
}
