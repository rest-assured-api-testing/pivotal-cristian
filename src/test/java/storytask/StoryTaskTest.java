package storytask;

import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.storytask.StoryTask;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoryTaskTest {
    @Test
    public void getAllStoryTaskTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
//        apiRequest.setToken("b3b0d0a60d898c42c0bb3c1f7b7da0c2");
//        apiRequest.addHeaders("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2");
//        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
//        apiRequest.setEndpoint("/projects");
//        apiRequest.setMethod(ApiMethod.GET);
        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/stories/{storyID}/tasks")
                .addPathParams("projectID", "2504472")
                .addPathParams("storyID", "178591234")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void createAStoryTaskTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        StoryTask storyTaskToSend = new StoryTask();
        storyTaskToSend.setDescription("port 270");

        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/stories/{storyID}/tasks")
                .addPathParams("projectID", "2504472")
                .addPathParams("storyID", "178591234")
                .addBody(new ObjectMapper().writeValueAsString(storyTaskToSend))
                .addMethod(ApiMethod.POST)
                .build();

        System.out.println("+++++++++++++" + storyTaskToSend.toString());


        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        StoryTask storyTask = apiResponse.getBody(StoryTask.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(storyTask.getKind(), "task");
        apiResponse.validateBodySchema("schemas/storytask.json");
    }

    @Test
    public void getStoryTaskTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/stories/{storyID}/tasks/{storyTaskID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("storyID", "178591234")
                .addPathParams("storyTaskID", "73033447")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        StoryTask storyTask = apiResponse.getBody(StoryTask.class);
        System.out.println("------------ " + storyTask.getDescription());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        System.out.println("++++++++++++" + apiResponse.getStatusCode());
        apiResponse.validateBodySchema("schemas/storytask.json");
    }

    @Test
    public void updateAStoryTaskTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        StoryTask storyTaskToSend = new StoryTask();
        storyTaskToSend.setDescription("port 270 update");
        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/stories/{storyID}/tasks/{storyTaskID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("storyID", "178591234")
                .addPathParams("storyTaskID", "73033447")
                .addBody(new ObjectMapper().writeValueAsString(storyTaskToSend))
                .addMethod(ApiMethod.PUT)
                .build();

        System.out.println("+++++++++++++" + storyTaskToSend.toString());
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        StoryTask storyTask = apiResponse.getBody(StoryTask.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(storyTask.getKind(), "task");
        apiResponse.validateBodySchema("schemas/storytask.json");
    }

    @Test
    public void deleteAStoryTask() {
        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/stories/{storyID}/tasks/{storyTaskID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("storyID", "178591234")
                .addPathParams("storyTaskID", "73033447")
                .addMethod(ApiMethod.DELETE)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 204);
    }
}
