import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.integration.Integration;
import entities.integration.IntegrationCreate;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IntegrationTest {

    @Test
    public void getAllIntegrationTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/integrations")
                .addPathParams("projectID", "2504472")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void getIntegrationTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/integrations/{integrationID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("integrationID", "52458")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Integration integration = apiResponse.getBody(Integration.class);
        System.out.println("------------ " + integration.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        System.out.println("++++++++++++" + apiResponse.getStatusCode());
        apiResponse.validateBodySchema("schemas/integration.json");
    }

    @Test
    public void updateAIntegrationTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        Integration integrationToSend = new Integration();
        integrationToSend.setName("something6 updated");
        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/integrations/{integrationID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("integrationID", "52458")
                .addBody(new ObjectMapper().writeValueAsString(integrationToSend))
                .addMethod(ApiMethod.PUT)
                .build();

        System.out.println("+++++++++++++" + integrationToSend.toString());
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Integration integration = apiResponse.getBody(Integration.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(integration.getKind(), "other_integration");
        apiResponse.validateBodySchema("schemas/integration.json");
    }

    @Test
    public void deleteAIntegration() {
        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/integrations/{integrationID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("integrationID", "52458")
                .addMethod(ApiMethod.DELETE)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 204);
    }


    @Test
    public void createAIntegrationTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        IntegrationCreate integrationToSend = new IntegrationCreate();
        integrationToSend.setName("something6");
        integrationToSend.setActive(true);
        integrationToSend.setBase_url("https://come.thing/g");
        integrationToSend.setType("other");

        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/integrations?type=other")
                .addPathParams("projectID", "2504472")
                .addBody(new ObjectMapper().writeValueAsString(integrationToSend))
                .addMethod(ApiMethod.POST)
                .build();

        System.out.println("+++++++++++++" + integrationToSend.toString());


        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Integration integration = apiResponse.getBody(Integration.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(integration.getKind(), "other_integration");
        apiResponse.validateBodySchema("schemas/integration.json");
    }


}
