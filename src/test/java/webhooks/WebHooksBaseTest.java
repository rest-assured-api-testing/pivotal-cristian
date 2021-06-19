package webhooks;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequestBuilder;
import api.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.project.Project;
import entities.webhooks.WebHooks;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class WebHooksBaseTest {

    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiResponse apiResponse = new ApiResponse();
    Project projectToSend = new Project();
    WebHooks webHooksToSend = new WebHooks();

    @BeforeClass
    public void setUp() {
        requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5");
    }

    @BeforeMethod(onlyForGroups = "getWebHooks")
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

    @BeforeMethod(dependsOnMethods = "createProjectForGet", onlyForGroups = "getWebHooks")
    public void createWebHooksForGet() throws JsonProcessingException {
        webHooksToSend.setEnabled(true);
        webHooksToSend.setWebhook_url("https://pastebin.com/fred");
        requestBuilder
                .addEndpoint("/projects/{projectID}/webhooks")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(webHooksToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "getWebHooks")
    public void deleteProjectForGet() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(WebHooks.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "updateWebHooks")
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

    @BeforeMethod(dependsOnMethods = "createProjectForUpdate", onlyForGroups = "updateWebHooks")
    public void createWebHooksForUpdate() throws JsonProcessingException {
        webHooksToSend.setEnabled(true);
        webHooksToSend.setWebhook_url("https://pastebin.com/fred");
        requestBuilder
                .addEndpoint("/projects/{projectID}/webhooks")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(webHooksToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "updateWebHooks")
    public void deleteProjectForUpdate() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(WebHooks.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "deleteWebHooks")
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

    @BeforeMethod(dependsOnMethods = "createProjectForDelete", onlyForGroups = "deleteWebHooks")
    public void createWebHookForDelete() throws JsonProcessingException {
        webHooksToSend.setEnabled(true);
        webHooksToSend.setWebhook_url("https://pastebin.com/fred");
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectID}/webhooks")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(webHooksToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "deleteWebHooks")
    public void deleteProjectForDelete() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(WebHooks.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "createWebHook")
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

    @AfterMethod(onlyForGroups = "createWebHook")
    public void deleteProjectForCreate() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(WebHooks.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

}
