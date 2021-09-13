package com.koendebruijn.portfolio_backend.projects.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProjectNotFoundException extends ResponseStatusException {
    public ProjectNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Project with id::" + id + " does not exist");
    }
}
