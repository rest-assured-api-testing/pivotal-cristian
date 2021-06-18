import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.label.Label;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LabelTest {

    @Test
    public void getAllLabelsTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
//        apiRequest.setToken("b3b0d0a60d898c42c0bb3c1f7b7da0c2");
//        apiRequest.addHeaders("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2");
//        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
//        apiRequest.setEndpoint("/projects");
//        apiRequest.setMethod(ApiMethod.GET);
        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/2504472/labels")
                .addPathParams("projectID", "2504472")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void createALabelTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        Label labelToSend = new Label();
        labelToSend.setName("label 4 created");

        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/labels")
                .addPathParams("projectID", "2504472")
                .addBody(new ObjectMapper().writeValueAsString(labelToSend))
                .addMethod(ApiMethod.POST)
                .build();

        System.out.println("+++++++++++++" + labelToSend.toString());


        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Label label = apiResponse.getBody(Label.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(label.getKind(), "label");
        apiResponse.validateBodySchema("schemas/label.json");
    }

    @Test
    public void getLabelTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/labels/{labelId}")
                .addPathParams("labelId", "23169744")
                .addPathParams("projectID", "2504472")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Label label = apiResponse.getBody(Label.class);
        System.out.println("------------ " + label.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        apiResponse.validateBodySchema("schemas/label.json");
    }

    @Test
    public void updateALabelTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        Label labelToSend = new Label();
        labelToSend.setName("label 2 updated uuuupdate");
        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/labels/{labelId}")
                .addPathParams("labelId", "23169744")
                .addPathParams("projectID", "2504472")
                .addBody(new ObjectMapper().writeValueAsString(labelToSend))
                .addMethod(ApiMethod.PUT)
                .build();

        System.out.println("+++++++++++++" + labelToSend.toString());
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Label label = apiResponse.getBody(Label.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(label.getKind(), "label");
        apiResponse.validateBodySchema("schemas/label.json");
    }

    @Test
    public void deleteAStory() {
        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/labels/{labelId}")
                .addPathParams("labelId", "23169744")
                .addPathParams("projectID", "2504472")
                .addMethod(ApiMethod.DELETE)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 204);
    }

}
