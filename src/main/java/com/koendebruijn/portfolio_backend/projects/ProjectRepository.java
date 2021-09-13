package com.koendebruijn.portfolio_backend.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * Get all the repositories without the GitHub profile config
     *
     * @return A list of projects without the GitHub config repo
     */
    @Query("SELECT p FROM Project p WHERE p.id != 347069020")
    List<Project> findAllExceptProfileConfig();
}
