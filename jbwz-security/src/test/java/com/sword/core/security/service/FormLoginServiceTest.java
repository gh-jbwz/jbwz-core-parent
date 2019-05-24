package com.jbwz.core.security.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FormLoginServiceTest {

  @Autowired
  private WebApplicationContext context;
  @Autowired
  private Filter springSecurityFilterChain;

  @Autowired
  TestRestTemplate testRestTemplate;
  @Autowired
  PasswordEncoder passwordEncoder;
  MockMvc mockMvc;

  @Before
  public void before() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .addFilters(springSecurityFilterChain).build();  //构造MockMvc
  }

  @Test
  public void postLogin() throws Exception {
    MultiValueMap map = new LinkedMultiValueMap();
    map.put("username", Collections.singletonList("haha"));
    map.put("password", Collections.singletonList("111"));
    String r = mockMvc.perform(MockMvcRequestBuilders.post("/login").params(map)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andReturn().getResponse().getContentAsString();
    System.out.println("result>>>>>>>" + r);
  }

  @Test
  public void loadUserByUsername() {
    Map map = new HashMap();
    map.put("username", "haha");
    map.put("password", "111");
    String s = testRestTemplate
        .getForObject("/hello?username=haha&password=111", String.class);
    System.out.println("result>>>>>>>" + s);
  }
}