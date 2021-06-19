package label;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequestBuilder;
import api.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.label.Label;
import entities.project.Project;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class LabelBaseTest {

    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiResponse apiResponse = new ApiResponse();
    Project projectToSend = new Project();
    Label labelToSend = new Label();

    @BeforeClass
    public void setUp() {
        requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5");
    }

    @BeforeMethod(onlyForGroups = "getLabel")
    public void createProjectForGet() throws JsonProcessingException {
        projectToSend.setName("projectc 1000010");
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

    @BeforeMethod(dependsOnMethods = "createProjectForGet", onlyForGroups = "getLabel")
    public void createLabelForGet() throws JsonProcessingException {
        labelToSend.setName("label 4 created");
        requestBuilder
                .addEndpoint("/projects/{projectID}/labels")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(labelToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "getLabel")
    public void deleteProjectForGet() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Label.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "updateLabel")
    public void createProjectForUpdate() throws JsonProcessingException {
        projectToSend.setName("projectc 1000010");
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

    @BeforeMethod(dependsOnMethods = "createProjectForUpdate", onlyForGroups = "updateLabel")
    public void createLabelForUpdate() throws JsonProcessingException {
        labelToSend.setName("label 4 created");
        requestBuilder
                .addEndpoint("/projects/{projectID}/labels")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(labelToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "updateLabel")
    public void deleteProjectForUpdate() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Label.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "deleteLabel")
    public void createProjectForDelete() throws JsonProcessingException {
        projectToSend.setName("projectc 1000010");
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

    @BeforeMethod(dependsOnMethods = "createProjectForDelete", onlyForGroups = "deleteLabel")
    public void createLabelForDelete() throws JsonProcessingException {
        labelToSend.setName("label 4 created");
        requestBuilder
                .addEndpoint("/projects/{projectID}/labels")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(labelToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "deleteLabel")
    public void deleteProjectForDelete() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Label.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "createLabel")
    public void createProjectForCreate() throws JsonProcessingException {
        projectToSend.setName("projectc 1000010");
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

    @AfterMethod(onlyForGroups = "createLabel")
    public void deleteProjectForCreate() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Label.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

}
