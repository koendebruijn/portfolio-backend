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


    /**
     * Get all the projects
     *
     * @return A list of projects
     */
    @GetMapping
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    /**
     * Get a single project by id
     *
     * @param id the id of the project
     * @return project
     */
    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    /**
     * This endpoint is to manually update the repo's from github.
     *
     * @throws IOException could throw exception when request fails
     */
    @GetMapping("/update")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateProjects() throws IOException {
        githubService.fetchRepositories();
    }
}
