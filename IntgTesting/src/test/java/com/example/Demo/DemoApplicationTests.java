package com.example.Demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    WebApplicationContext webappConfiguration;

    private MockMvc mvc;
    private MockHttpServletResponse output;

    @Before
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(webappConfiguration).build();
    }

    @Test
    public void checkStudents() throws Exception {
        MockHttpServletResponse output = mvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(status().isOk())
                .andExpect(header().string("version", "v1.2"))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andReturn().getResponse();
        ObjectMapper mapper = new ObjectMapper();
        List<Student> students = mapper.readValue(output.getContentAsString(), new TypeReference<List<Student>>(){});
        students.forEach(s -> System.out.println(s));
        assertThat(students.size()).isEqualTo(3);
    }

}
