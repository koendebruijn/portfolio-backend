package com.koendebruijn.portfolio_backend.projects;

import com.koendebruijn.portfolio_backend.github.GithubService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final GithubService githubService;


    @GetMapping
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/update")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateProjects() throws IOException {
        githubService.fetchRepositories();
    }
}
