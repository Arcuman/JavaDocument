package com.arcuman.borto;

import org.junit.Assert;
import com.arcuman.borto.Repository.CommentRepository;
import com.arcuman.borto.Repository.DocumentRepository;
import com.arcuman.borto.Repository.UserRepository;
import com.arcuman.borto.models.Comment;
import com.arcuman.borto.models.Document;
import com.arcuman.borto.services.impl.UserServiceImpl;
import org.hamcrest.Matchers;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BortoApplicationTests {

	@MockBean
	private DocumentRepository documentRepository;

	@MockBean
	private CommentRepository commentRepository;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	UserServiceImpl userService;

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@WithMockUser(username = "w")
	public void getAllDocuments() throws Exception {
		setUp();
		List<Document> documents = Arrays.asList(
				new Document("xc","zxc"),
				new Document("asd","asd1")
		);

		when(documentRepository.findAll()).thenReturn(documents);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/documents"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[*].description", Matchers.containsInAnyOrder("zxc", "asd1")))
				.andExpect(jsonPath("$[*].title", Matchers.containsInAnyOrder("xc", "asd")));
	}


  @Test
  void existsUserByUserName() {
    try {
      Assert.assertTrue(userService.isExistUsername("w"));
    } catch (ServiceException e) {
      e.printStackTrace();
    }
		}

	@Test
	void existsUserByEmail() {
		try {
			Assert.assertTrue(userService.isExistEmail("wef@mail.ru"));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}


}
