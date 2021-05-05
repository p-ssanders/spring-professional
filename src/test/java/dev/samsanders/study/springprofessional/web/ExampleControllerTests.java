package dev.samsanders.study.springprofessional.web;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ExampleControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void voidControllerMethod() throws Exception {
		this.mockMvc.perform(put("/example-resources"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void mapToJson() throws Exception {
		this.mockMvc.perform(get("/example-resources"))
				.andDo(print())
				.andExpect(content().json("{\"key\": \"value\"}"));
	}

	@Test
	void restApiControllerAdvice() throws Exception {
		this.mockMvc.perform(get("/example-resources/bad-resource")).andDo(print())
				.andExpect(status().isIAmATeapot());
	}

	@Test
	@WithMockUser(roles = {"SOME_ROLE"})
	void someRoleRequired() throws Exception {
		this.mockMvc.perform(get("/secured-resource")).andDo(print())
				.andExpect(status().isOk());
	}

}