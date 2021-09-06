package com.koendebruijn.portfolio_backend.github;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koendebruijn.portfolio_backend.projects.Project;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GithubService {


    public List<Project> fetchRepositories() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OkHttpClient client = new OkHttpClient();

        String token = System.getenv().get("token");

        if (token == null) {
            token = "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";
        }

        Request request = new Request.Builder()
                .url("https://api.github.com/user/repos?visibility=public&affiliation=owner")
                .get()
                .addHeader("Authorization", "token " +  token)
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        String json = response.body().string();

        List<Project> projects = objectMapper.readValue(json, new TypeReference<List<Project>>(){});

        return projects;
    }
}
