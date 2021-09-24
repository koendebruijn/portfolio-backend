package com.koendebruijn.portfolio_backend.projects;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectRepository projectRepository;

    private final Faker faker = new Faker();


    @Test
    void shouldGetAllProjects() throws Exception {
        // given
        List<Project> projects = List.of(
                new Project(faker.random().nextLong(), faker.name().name(), faker.lorem().characters(10), "", "", ""),
                new Project(faker.random().nextLong(), faker.name().name(), faker.lorem().characters(10), "", "", ""),
                new Project(faker.random().nextLong(), faker.name().name(), faker.lorem().characters(10), "", "", "")
        );
        // when
        when(projectRepository.findAllExceptProfileConfig()).thenReturn(projects);
        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/projects")
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

    }

    @Test
    void shouldGetProjectById() throws Exception {
        // given
        long id = 1;
        Project project = new Project(id, faker.name().name(), faker.lorem().characters(10), "", "", "");

        // when
        when(projectRepository.findById(id)).thenReturn(Optional.of(project));

        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/projects/" + id)
                        .contentType(MediaType.APPLICATION_JSON));
        // then
        resultActions
                .andExpect(status().isOk());
    }

    @Test
    void shouldThrowIfStudentDoesntExists() throws Exception {
        // when
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/projects/" + anyLong())
                        .contentType(MediaType.APPLICATION_JSON));
        // then
        resultActions
                .andExpect(status().isNotFound());
    }
}