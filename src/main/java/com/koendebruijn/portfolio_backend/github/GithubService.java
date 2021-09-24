package com.koendebruijn.portfolio_backend.github;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koendebruijn.portfolio_backend.projects.Project;
import com.koendebruijn.portfolio_backend.projects.ProjectService;
import io.circe.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class GithubService {
    private final ProjectService projectService;
    private final Logger logger = LoggerFactory.getLogger(GithubService.class);

    @Autowired
    public GithubService(ProjectService projectService) {
        this.projectService = projectService;
    }

    // one week
    @Scheduled(fixedRate = 604800000)
    public void fetchRepositories() throws IOException {
        logger.info("Fetching github repos");
        ObjectMapper objectMapper = new ObjectMapper();
        OkHttpClient client = new OkHttpClient();

        String token = System.getenv().get("TOKEN");


        Request request = new Request.Builder()
                .url("https://api.github.com/user/repos?visibility=public&affiliation=owner")
                .get()
                .addHeader("Authorization", "token " + token)
                .build();

            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String json = response.body().string();

            List<Project> projects = objectMapper.readValue(json, new TypeReference<>() {
            });
            logger.info("fetched github repos");

            projects.forEach(project -> {
                try {
                    project.setReadMe(getReadme("koendebruijn/" + project.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            projectService.addProjects(projects);

    }
    
    private String getReadme(String repoName) throws IOException {
        String url = "https://api.github.com/repos/" + repoName + "/contents/README.md";
        OkHttpClient client = new OkHttpClient();

        String token = System.getenv().get("TOKEN");

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "token " + token)
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        JSONObject json = new JSONObject(response.body().string());


        return json.getString("content");
    }
}
