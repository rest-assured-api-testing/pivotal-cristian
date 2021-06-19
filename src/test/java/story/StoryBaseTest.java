package story;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequestBuilder;
import api.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.project.Project;
import entities.story.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class StoryBaseTest {

    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiResponse apiResponse = new ApiResponse();
    Project projectToSend = new Project();
    Story storyToSend = new Story();

    @BeforeClass
    public void setUp() {
        requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5");
    }

    @BeforeMethod(onlyForGroups = "getStory")
    public void createProjectForGet() throws JsonProcessingException {
        projectToSend.setName("projectc 1000010");
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects")
                .addBody(new ObjectMapper().writeValueAsString(projectToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
    }

    @BeforeMethod(dependsOnMethods = "createProjectForGet", onlyForGroups = "getStory")
    public void createStoryForGet() throws JsonProcessingException {
        storyToSend.setName("second story Replace the lining in Darth Vader's helmet \uD83D\uDC79");
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}/stories")
                .addPathParams("projectId", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(storyToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
    }

    @AfterMethod(onlyForGroups = "getStory")
    public void deleteProjectForGet() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Story.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }


    @BeforeMethod(onlyForGroups = "updateStory")
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

    @BeforeMethod(dependsOnMethods = "createProjectForUpdate", onlyForGroups = "updateStory")
    public void createStoryForUpdate() throws JsonProcessingException {
        storyToSend.setName("second story Replace the lining in Darth Vader's helmet \uD83D\uDC79");
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}/stories")
                .addPathParams("projectId", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(storyToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "updateStory")
    public void deleteProjectForUpdate() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Story.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "deleteStory")
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

    @BeforeMethod(dependsOnMethods = "createProjectForDelete", onlyForGroups = "deleteStory")
    public void createStoryForDelete() throws JsonProcessingException {
        storyToSend.setName("second story Replace the lining in Darth Vader's helmet \uD83D\uDC79");
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}/stories")
                .addPathParams("projectId", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(storyToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @AfterMethod(onlyForGroups = "deleteStory")
    public void deleteProjectForDelete() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Story.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }

    @BeforeMethod(onlyForGroups = "createStory")
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

    @AfterMethod(onlyForGroups = "createStory")
    public void deleteProjectForCreate() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Story.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
    }
}
