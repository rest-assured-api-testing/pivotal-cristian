package epic;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequestBuilder;
import api.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.epic.Epic;
import entities.project.Project;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class EpicBaseTest {

    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiResponse apiResponse = new ApiResponse();
    Project projectToSend = new Project();
    Epic epicToSend = new Epic();

    @BeforeClass
    public void setUp() {
        requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5");
    }

    @BeforeMethod(onlyForGroups = "getEpic")
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

    @BeforeMethod(dependsOnMethods = "createProjectForGet", onlyForGroups = "getEpic")
    public void createEpicForGet() throws JsonProcessingException {
        epicToSend.setName("Tractor Beams");
        requestBuilder
                .addEndpoint("/projects/{projectID}/epics")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(epicToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "getEpic")
    public void deleteProjectForGet() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", apiResponse.getBody(Epic.class).getProject_id().toString())
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "updateEpic")
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

    @BeforeMethod(dependsOnMethods = "createProjectForUpdate", onlyForGroups = "updateEpic")
    public void createEpicForUpdate() throws JsonProcessingException {
        epicToSend.setName("Tractor Beams");
        requestBuilder
                .addEndpoint("/projects/{projectID}/epics")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(epicToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "updateEpic")
    public void deleteProjectForUpdate() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", apiResponse.getBody(Epic.class).getProject_id().toString())
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "deleteEpic")
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

    @BeforeMethod(dependsOnMethods = "createProjectForDelete", onlyForGroups = "deleteEpic")
    public void createEpicForDelete() throws JsonProcessingException {
        epicToSend.setName("Tractor Beams");
        requestBuilder
                .addEndpoint("/projects/{projectID}/epics")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(epicToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "deleteEpic")
    public void deleteProjectForDelete() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", apiResponse.getBody(Epic.class).getProject_id().toString())
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "createEpic")
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

    @AfterMethod(onlyForGroups = "createEpic")
    public void deleteProjectForCreate() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", apiResponse.getBody(Epic.class).getProject_id().toString())
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

}
