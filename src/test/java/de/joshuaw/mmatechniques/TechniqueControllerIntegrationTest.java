package de.joshuaw.mmatechniques;

import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MmaTechniquesApplication.class)
@AutoConfigureMockMvc
public class TechniqueControllerIntegrationTest {

  public static final String UUID_REGEX =
      "[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}";
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void whenGettingAllTechniques_ReturnsSuccessfulResponse() throws Exception {
    this.mockMvc
        .perform(get("/techniques"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
  }

  @Test
  public void whenPostingTechnique_ReturnsId() throws Exception {
    final ObjectMapper mapper = new ObjectMapper();
    final ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
    final String requestJson = objectWriter.writeValueAsString(new Technique("Kimura Grip"));
    final MvcResult result = this.mockMvc
        .perform(
            post("/techniques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andReturn();

    assertThat(result.getResponse().getContentAsString(), matchesRegex(UUID_REGEX));
  }
}
