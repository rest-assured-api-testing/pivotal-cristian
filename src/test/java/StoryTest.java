import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.story.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoryTest {

    @Test
    public void getAllStoryTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5/projects/2504472")
                .addEndpoint("/stories")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void getStoryTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/stories/{storyId}")
                .addPathParams("storyId", "178579944")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Story story = apiResponse.getBody(Story.class);
        System.out.println("------------ " + story.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        System.out.println("++++++++++++" + apiResponse.getStatusCode());
        apiResponse.validateBodySchema("schemas/story.json");
    }

    @Test
    public void updateAStoryTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        Story storyToSend = new Story();
        storyToSend.setName("second story update 2 Exhaust ports are ray shielded \uD83D\uDC79");
        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/stories/{storyId}")
                .addPathParams("storyId", "178579944")
                .addBody(new ObjectMapper().writeValueAsString(storyToSend))
                .addMethod(ApiMethod.PUT)
                .build();

        System.out.println("+++++++++++++" + storyToSend.toString());
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Story story = apiResponse.getBody(Story.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(story.getKind(), "story");
        apiResponse.validateBodySchema("schemas/story.json");
    }

    @Test
    public void deleteAStory() {
        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/stories/{storyId}")
                .addPathParams("storyId", "178579944")
                .addMethod(ApiMethod.DELETE)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 204);
    }

    @Test
    public void createAStoryTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        Story storyToSend = new Story();
        storyToSend.setName("second story Replace the lining in Darth Vader's helmet \uD83D\uDC79");

        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5/projects/2504472")
                .addEndpoint("/stories")
                .addBody(new ObjectMapper().writeValueAsString(storyToSend))
                .addMethod(ApiMethod.POST)
                .build();

        System.out.println("+++++++++++++" + storyToSend.toString());


        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Story story = apiResponse.getBody(Story.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(story.getKind(), "story");
        apiResponse.validateBodySchema("schemas/story.json");
    }
}
