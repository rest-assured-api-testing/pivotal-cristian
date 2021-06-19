package tblockers;

import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.blockers.Blockers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BlockersTest {

    @Test
    public void getAllBlockersTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/webhooks")
                .addPathParams("projectID", "2504472")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void getBlockersTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/stories/{storyID}/blockers/{blockerID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("storyID", "178591051")
                .addPathParams("blockerID", "2894755")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Blockers blockers = apiResponse.getBody(Blockers.class);
        System.out.println("------------ " + blockers.getDescription());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        System.out.println("++++++++++++" + apiResponse.getStatusCode());
        apiResponse.validateBodySchema("schemas/blockers.json");
    }

    @Test
    public void updateABlockersTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        Blockers blockersToSend = new Blockers();
        blockersToSend.setDescription("Transport shuttle crashed on Endor.");
        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/stories/{storyID}/blockers/{blockerID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("storyID", "178591051")
                .addPathParams("blockerID", "2894755")
                .addBody(new ObjectMapper().writeValueAsString(blockersToSend))
                .addMethod(ApiMethod.PUT)
                .build();

        System.out.println("+++++++++++++" + blockersToSend.toString());
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Blockers blockers = apiResponse.getBody(Blockers.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(blockers.getKind(), "blocker");
        apiResponse.validateBodySchema("schemas/blockers.json");
    }

    @Test
    public void deleteABlockers() {
        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/stories/{storyID}/blockers/{blockerID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("storyID", "178591051")
                .addPathParams("blockerID", "2894755")
                .addMethod(ApiMethod.DELETE)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 204);
    }


    @Test
    public void createABlockersTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        Blockers blockersToSend = new Blockers();
        blockersToSend.setDescription("Waiting on transport shuttle to arrive.");
        blockersToSend.setResolved(true);

        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/stories/{storyID}/blockers")
                .addPathParams("projectID", "2504472")
                .addPathParams("storyID", "178591051")
                .addBody(new ObjectMapper().writeValueAsString(blockersToSend))
                .addMethod(ApiMethod.POST)
                .build();

        System.out.println("+++++++++++++" + blockersToSend.toString());


        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Blockers blockers = apiResponse.getBody(Blockers.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(blockers.getKind(), "blocker");
        apiResponse.validateBodySchema("schemas/blockers.json");
    }

}
