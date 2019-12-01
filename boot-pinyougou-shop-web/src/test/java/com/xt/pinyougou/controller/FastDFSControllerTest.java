package com.xt.pinyougou.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastDFSControllerTest {

    @Autowired
    private WebApplicationContext wioc;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wioc).build();
    }

    @Test
    public void whenDeleteSuccess () throws Exception {
        mockMvc.perform(delete("/fdfs/delete")
                    .param("path", "http://192.168.239.13/group1/M00/00/00/wKjvDV3hu-GAFnJ1AACAtacdWAI511.jpg")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
