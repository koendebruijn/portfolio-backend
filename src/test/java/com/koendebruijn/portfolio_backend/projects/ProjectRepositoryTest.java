package com.koendebruijn.portfolio_backend.projects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ProjectRepositoryTest {

    private final ProjectRepository underTest;

    @Autowired
    ProjectRepositoryTest(ProjectRepository underTest) {
        this.underTest = underTest;
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindAllProjectsWithoutProfileRepo() {
        // given
        Project project = new Project(347069020, "koendebruijn/koendebruijn", "", "", "");
        underTest.save(project);
        // when
        List<Project> projects = underTest.findAllExceptProfileConfig();
        // then
        assertThat(projects.size()).isEqualTo(0);
    }
}