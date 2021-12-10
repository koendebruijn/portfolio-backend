package com.koendebruijn.portfolio_backend.projects;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    @DisplayName(value = "Should find all projects except the GitHub profile repo")
    void shouldFindAllProjectsWithoutProfileRepo() {
        // given
        Project project = new Project(347069020, "koendebruijn/koendebruijn", "", "", "", "");
        underTest.save(project);
        // when
        List<Project> projects = underTest.findAllExceptProfileConfig();
        // then
        assertThat(projects.size()).isEqualTo(0);
    }
}