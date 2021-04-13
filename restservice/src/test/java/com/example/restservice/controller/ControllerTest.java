package com.example.restservice.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void testStandartParams() throws Exception {
        String uri = "/result";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }
    @Test
    public void testNegativeNum1() throws Exception {
        String uri = "/result";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("num1", "-100").param("num2", "1")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }
    @Test
    public void testNegativeNum2() throws Exception {
        String uri = "/result";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("num1", "2000").param("num2", "-10")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }
    @Test
    public void testNegativeNum3() throws Exception {
        String uri = "/result";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("num1", "20").param("num2", "2").param("num2","-1")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }
}
