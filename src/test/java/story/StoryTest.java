package story;

import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.project.Project;
import entities.story.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoryTest extends StoryBaseTest{

    @Test
    public void getAllStoryTest() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}/stories")
                .addPathParams("projectId", "2504472")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(requestBuilder.build()));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test(groups = "getStory")
    public void getStoryTest() {
        requestBuilder
                .addEndpoint("/projects/{projectId}/stories/{storyId}")
                .addPathParams("storyId", Integer.toString(apiResponse.getBody(Story.class).getId()))
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Story.class).getProject_id()))
                .addMethod(ApiMethod.GET)
                .build();
        ApiManager.execute(requestBuilder.build());
        Story story = apiResponse.getBody(Story.class);
        System.out.println("------------ " + story.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(story.getKind(), "story");
        apiResponse.validateBodySchema("schemas/story.json");
    }

    @Test(groups = "updateStory")
    public void updateAStoryTest() throws JsonProcessingException {
        storyToSend.setName("second story update updated 2 Exhaust ports are ray shielded \uD83D\uDC79");
        requestBuilder
                .addEndpoint("/projects/{projectId}/stories/{storyId}")
                .addPathParams("storyId", Integer.toString(apiResponse.getBody(Story.class).getId()))
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Story.class).getProject_id()))
                .addBody(new ObjectMapper().writeValueAsString(storyToSend))
                .addMethod(ApiMethod.PUT)
                .build();
        ApiManager.executeWithBody(requestBuilder.build());
        Story story = apiResponse.getBody(Story.class);
        System.out.println("-------------" + story.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(story.getKind(), "story");
        apiResponse.validateBodySchema("schemas/story.json");
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test(groups = "deleteStory")
    public void deleteAStory() {
        requestBuilder
                .addEndpoint("/projects/{projectId}/stories/{storyId}")
                .addPathParams("storyId", Integer.toString(apiResponse.getBody(Story.class).getId()))
                .addPathParams("projectId", Integer.toString(apiResponse.getBody(Story.class).getProject_id()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(requestBuilder.build()));
        Assert.assertEquals(apiResponse.getStatusCode(), 204);
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test(groups = "createStory")
    public void createAStoryTest() throws JsonProcessingException {
        storyToSend.setName("second story Replace the lining in Darth Vader's helmet \uD83D\uDC79");
        requestBuilder
                .addEndpoint("/projects/{projectId}/stories")
                .addPathParams("projectId", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(storyToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
        Story story = apiResponse.getBody(Story.class);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(story.getKind(), "story");
        apiResponse.validateBodySchema("schemas/story.json");
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


//
//
//    @Test
//    public void getStoryTestWithID() {
//
//        ApiRequest apiRequest = new ApiRequest();
//        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
//
//        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
//                .addBaseUri("https://www.pivotaltracker.com/services/v5")
//                .addEndpoint("/stories/{storyId}")
//                .addPathParams("storyId", "178579944")
//                .addMethod(ApiMethod.GET)
//                .build();
//
//        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
//        Story story = apiResponse.getBody(Story.class);
//        System.out.println("------------ " + story.getName());
//        Assert.assertEquals(apiResponse.getStatusCode(), 200);
//        System.out.println("++++++++++++" + apiResponse.getStatusCode());
//        apiResponse.validateBodySchema("schemas/story.json");
//    }
//
//    @Test
//    public void updateAStoryTestWithID() throws JsonProcessingException {
//        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
//        Story storyToSend = new Story();
//        storyToSend.setName("second story update 2 Exhaust ports are ray shielded \uD83D\uDC79");
//        ApiRequest apiRequest = new ApiRequest();
//
//        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
//                .addBaseUri("https://www.pivotaltracker.com/services/v5")
//                .addEndpoint("/stories/{storyId}")
//                .addPathParams("storyId", "178579944")
//                .addBody(new ObjectMapper().writeValueAsString(storyToSend))
//                .addMethod(ApiMethod.PUT)
//                .build();
//
//        System.out.println("+++++++++++++" + storyToSend.toString());
//        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
//        Story story = apiResponse.getBody(Story.class);
//
//        Assert.assertEquals(apiResponse.getStatusCode(), 200);
//        Assert.assertEquals(story.getKind(), "story");
//        apiResponse.validateBodySchema("schemas/story.json");
//    }
//
//    @Test
//    public void deleteAStoryWithID() {
//        ApiRequest apiRequest = new ApiRequest();
//        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
//
//        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
//                .addBaseUri("https://www.pivotaltracker.com/services/v5")
//                .addEndpoint("/stories/{storyId}")
//                .addPathParams("storyId", "178580181")
//                .addMethod(ApiMethod.DELETE)
//                .build();
//
//        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
//        Assert.assertEquals(apiResponse.getStatusCode(), 204);
//    }
}
