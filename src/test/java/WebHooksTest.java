import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.webhooks.WebHooks;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebHooksTest {

    @Test
    public void getAllWebHooksTest() {

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
    public void getWebHooksTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/webhooks/{webHookID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("webHookID", "261343")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        WebHooks webHooks = apiResponse.getBody(WebHooks.class);
        System.out.println("------------ " + webHooks.getWebhook_url());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        System.out.println("++++++++++++" + apiResponse.getStatusCode());
        apiResponse.validateBodySchema("schemas/webhooks.json");
    }

    @Test
    public void updateAWebHooksTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        WebHooks webHooksToSend = new WebHooks();
        webHooksToSend.setWebhook_url("https://pastebin.com/wilma");
        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/webhooks/{webHookID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("webHookID", "261343")
                .addBody(new ObjectMapper().writeValueAsString(webHooksToSend))
                .addMethod(ApiMethod.PUT)
                .build();

        System.out.println("+++++++++++++" + webHooksToSend.toString());
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        WebHooks webHooks = apiResponse.getBody(WebHooks.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(webHooks.getKind(), "webhook");
        apiResponse.validateBodySchema("schemas/webhooks.json");
    }

    @Test
    public void deleteAWebHooks() {
        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/webhooks/{webHookID}")
                .addPathParams("projectID", "2504472")
                .addPathParams("webHookID", "261343")
                .addMethod(ApiMethod.DELETE)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 204);
    }


    @Test
    public void createAWebHooksTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        WebHooks webHooksToSend = new WebHooks();
        webHooksToSend.setEnabled(true);
        webHooksToSend.setWebhook_url("https://pastebin.com/fred");

        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectID}/webhooks")
                .addPathParams("projectID", "2504472")
                .addBody(new ObjectMapper().writeValueAsString(webHooksToSend))
                .addMethod(ApiMethod.POST)
                .build();

        System.out.println("+++++++++++++" + webHooksToSend.toString());


        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        WebHooks webHooks = apiResponse.getBody(WebHooks.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(webHooks.getKind(), "webhook");
        apiResponse.validateBodySchema("schemas/webhooks.json");
    }

}
