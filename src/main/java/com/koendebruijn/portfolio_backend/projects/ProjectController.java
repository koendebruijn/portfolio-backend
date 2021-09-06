package com.koendebruijn.portfolio_backend.projects;

import com.koendebruijn.portfolio_backend.github.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {
    private final GithubService githubService;

    @Autowired
    public ProjectController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping
    public List<Project> getProjects() {
        try {
            return githubService.fetchRepositories();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
