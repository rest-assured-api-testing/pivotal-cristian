package project;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequestBuilder;
import api.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.project.Project;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ProjectBaseTest {
    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiResponse apiResponse = new ApiResponse();
    Project projectToSend = new Project();
    @BeforeClass
    public void setUp() {
        requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5");
    }

    @BeforeMethod(onlyForGroups = "getProject")
    public void createProjectForGet() throws JsonProcessingException {
//        Project projectToSend = new Project();
        projectToSend.setName("projectc 100002");
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects")
                .addBody(new ObjectMapper().writeValueAsString(projectToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
    }

    @AfterMethod(onlyForGroups = "getProject")
    public void deleteProjectForGet() {
        requestBuilder
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", apiResponse.getBody(Project.class).getId().toString())
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "updateProject")
    public void createProjectForUpdate() throws JsonProcessingException {
//        Project projectToSend = new Project();
        projectToSend.setName("projectc 100002");
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects")
                .addBody(new ObjectMapper().writeValueAsString(projectToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "updateProject")
    public void deleteProjectForUpdate() {
        requestBuilder
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", apiResponse.getBody(Project.class).getId().toString())
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "deleteProject")
    public void createProjectForDelete() throws JsonProcessingException {
//        Project projectToSend = new Project();
        projectToSend.setName("projectc 100002");
        requestBuilder
                .addEndpoint("/projects")
                .clearPathParams()
                .addBody(new ObjectMapper().writeValueAsString(projectToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "createProject")
    public void deleteProjectForCreate() {
        requestBuilder
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", apiResponse.getBody(Project.class).getId().toString())
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

}
