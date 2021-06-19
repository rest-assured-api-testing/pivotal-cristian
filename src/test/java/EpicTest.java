import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.epic.Epic;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EpicTest {

    @Test
    public void getAllEpicTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/epics")
                .addPathParams("projectID", "2504472")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void getEpicTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/epics/{epicID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("epicID", "4791704")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Epic epic = apiResponse.getBody(Epic.class);
        System.out.println("------------ " + epic.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        System.out.println("++++++++++++" + apiResponse.getStatusCode());
        apiResponse.validateBodySchema("schemas/epic.json");
    }

    @Test
    public void updateAEpicTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        Epic epicToSend = new Epic();
        epicToSend.setName("tractor beams updated update");
        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/epics/{epicID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("epicID", "4791704")
                .addBody(new ObjectMapper().writeValueAsString(epicToSend))
                .addMethod(ApiMethod.PUT)
                .build();

        System.out.println("+++++++++++++" + epicToSend.toString());
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Epic epic = apiResponse.getBody(Epic.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(epic.getKind(), "epic");
        apiResponse.validateBodySchema("schemas/epic.json");
    }

    @Test
    public void deleteAEpic() {
        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/epics/{epicID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("epicID", "4791704")
                .addMethod(ApiMethod.DELETE)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 204);
    }


    @Test
    public void createAEpicTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        Epic epicToSend = new Epic();
        epicToSend.setName("Tractor Beams");

        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/epics")
                .addPathParams("projectID", "2504472")
                .addBody(new ObjectMapper().writeValueAsString(epicToSend))
                .addMethod(ApiMethod.POST)
                .build();

        System.out.println("+++++++++++++" + epicToSend.toString());


        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Epic epic = apiResponse.getBody(Epic.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(epic.getKind(), "epic");
        apiResponse.validateBodySchema("schemas/epic.json");
    }




}
