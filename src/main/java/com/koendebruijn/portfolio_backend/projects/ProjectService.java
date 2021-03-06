package com.koendebruijn.portfolio_backend.projects;

import com.koendebruijn.portfolio_backend.projects.exception.ProjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    /**
     * Get a list of projects from the database
     *
     * @return a list of projects
     */
    public List<Project> getProjects() {
        return projectRepository.findAllExceptProfileConfig();
    }

    /**
     * Adds a list of projects to the database
     *
     * @param projects a list of projects you want to add.
     */
    public void addProjects(List<Project> projects) {
        projectRepository.saveAll(projects);
    }

    /**
     * Get a single project by id
     *
     * @param id the id of the project you want to get
     * @return the project
     */
    public Project getProjectById(Long id) {

        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isEmpty()) {
            throw new ProjectNotFoundException(id);
        }

        return optionalProject.get();
    }
}
