package integration;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequestBuilder;
import api.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.integration.Integration;
import entities.integration.IntegrationCreate;
import entities.project.Project;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class IntegrationBaseTest {

    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiResponse apiResponse = new ApiResponse();
    Project projectToSend = new Project();
    IntegrationCreate integrationCreateToSend = new IntegrationCreate();
    Integration integrationToSend = new Integration();

    @BeforeClass
    public void setUp() {
        requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5");
    }

    @BeforeMethod(onlyForGroups = "getIntegration")
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

    @BeforeMethod(dependsOnMethods = "createProjectForGet", onlyForGroups = "getIntegration")
    public void createIntegrationForGet() throws JsonProcessingException {
        integrationCreateToSend.setName("something6");
        integrationCreateToSend.setActive(true);
        integrationCreateToSend.setBase_url("https://come.thing/g");
        integrationCreateToSend.setType("other");
        requestBuilder
                .addEndpoint("/projects/{projectID}/integrations?type=other")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(integrationCreateToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "getIntegration")
    public void deleteProjectForGet() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Integration.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "updateIntegration")
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

    @BeforeMethod(dependsOnMethods = "createProjectForUpdate", onlyForGroups = "updateIntegration")
    public void createIntegrationForUpdate() throws JsonProcessingException {
        integrationCreateToSend.setName("something6");
        integrationCreateToSend.setActive(true);
        integrationCreateToSend.setBase_url("https://come.thing/g");
        integrationCreateToSend.setType("other");
        requestBuilder
                .addEndpoint("/projects/{projectID}/integrations?type=other")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(integrationCreateToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "updateIntegration")
    public void deleteProjectForUpdate() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Integration.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "deleteIntegration")
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

    @BeforeMethod(dependsOnMethods = "createProjectForDelete", onlyForGroups = "deleteIntegration")
    public void createIntegrationForDelete() throws JsonProcessingException {
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

    @AfterMethod(onlyForGroups = "deleteIntegration")
    public void deleteProjectForDelete() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Label.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

//    @BeforeMethod(onlyForGroups = "createLabel")
//    public void createProjectForCreate() throws JsonProcessingException {
//        projectToSend.setName("projectc 1000010");
//        requestBuilder
//                .clearPathParams()
//                .addEndpoint("/projects")
//                .addBody(new ObjectMapper().writeValueAsString(projectToSend))
//                .addMethod(ApiMethod.POST)
//                .build();
//        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
////        try {
////            Thread.sleep(15000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//    }
//
//    @AfterMethod(onlyForGroups = "createLabel")
//    public void deleteProjectForCreate() {
//        requestBuilder
//                .clearPathParams()
//                .addEndpoint("/projects/{projectId}")
//                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Label.class).getProject_id()))
//                .addMethod(ApiMethod.DELETE)
//                .build();
//        ApiManager.execute(requestBuilder.build());
//    }

}
