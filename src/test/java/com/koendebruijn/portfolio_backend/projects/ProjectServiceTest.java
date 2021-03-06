package com.koendebruijn.portfolio_backend.projects;

import com.koendebruijn.portfolio_backend.projects.exception.ProjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;
    private ProjectService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ProjectService(projectRepository);
    }

    @Test
    @DisplayName(value = "Should get all the projects")
    void shouldGetAllProjects() {
        // when
        underTest.getProjects();

        // then
        verify(projectRepository).findAllExceptProfileConfig();
    }

    @Test
    @DisplayName(value = "Should add project")
    void shouldAddProject() {
        // given
        List<Project> projects = List.of(
                new Project(1,
                        "koendebruijn/test-repo",
                        "",
                        "",
                        "",
                        ""
                ),
                new Project(1,
                        "koendebruijn/test-repo",
                        "",
                        "",
                        "",
                        ""
                )
        );

        // when
        underTest.addProjects(projects);

        // then
        verify(projectRepository).saveAll(projects);
    }

    @Test
    @DisplayName(value = "Should get project by ID")
    void shouldGetProjectById() {
        // given
        Project project = new Project(1,
                "koendebruijn/test-repo",
                "",
                "",
                "",
                ""
        );
        given(projectRepository.findById(project.getId())).willReturn(Optional.of(project));

        // when
        underTest.getProjectById(project.getId());

        // then
        verify(projectRepository).findById(project.getId());
    }

    @Test
    @DisplayName(value = "Should throw not found if project with ID does not exists")
    void shouldThrowWhenProjectWithIdDoesNotExists() {
        // given
        long id = 1L;
        given(projectRepository.findById(anyLong())).willReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> underTest.getProjectById(id))
                .isInstanceOf(ProjectNotFoundException.class);
        verify(projectRepository).findById(id);

    }
}