package webhooks;

import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.project.Project;
import entities.webhooks.WebHooks;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebHooksTest extends WebHooksBaseTest{

    @Test
    public void getAllWebHooksTest() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}/stories")
                .addPathParams("projectId", "2504472")
                .addMethod(ApiMethod.GET)
                .build();
        apiResponse = new ApiResponse(ApiManager.execute(requestBuilder.build()));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test(groups = "getWebHooks")
    public void getWebHooksTest() {
        requestBuilder
                .addEndpoint("/projects/{projectID}/webhooks/{webHookID}")
                .addPathParams("projectID", Integer.toString(apiResponse.getBody(WebHooks.class).getProject_id()))
                .addPathParams("webHookID", Integer.toString(apiResponse.getBody(WebHooks.class).getId()))
                .addMethod(ApiMethod.GET)
                .build();

        ApiManager.execute(requestBuilder.build());
        WebHooks webHooks = apiResponse.getBody(WebHooks.class);
        System.out.println("------------ " + webHooks.getWebhook_url());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        System.out.println("++++++++++++" + apiResponse.getStatusCode());
        apiResponse.validateBodySchema("schemas/webhooks.json");
    }

    @Test(groups = "updateWebHooks")
    public void updateAWebHooksTest() throws JsonProcessingException {
        webHooksToSend.setWebhook_url("https://pastebin.com/wilma");
        requestBuilder
                .addEndpoint("/projects/{projectID}/webhooks/{webHookID}")
                .addPathParams("projectID", Integer.toString(apiResponse.getBody(WebHooks.class).getProject_id()))
                .addPathParams("webHookID", Integer.toString(apiResponse.getBody(WebHooks.class).getId()))
                .addBody(new ObjectMapper().writeValueAsString(webHooksToSend))
                .addMethod(ApiMethod.PUT)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
        WebHooks webHooks = apiResponse.getBody(WebHooks.class);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(webHooks.getKind(), "webhook");
        apiResponse.validateBodySchema("schemas/webhooks.json");
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test(groups = "deleteWebHooks")
    public void deleteAWebHooks() {
        requestBuilder
                .addEndpoint("/projects/{projectID}/webhooks/{webHookID}")
                .addPathParams("projectID", Integer.toString(apiResponse.getBody(WebHooks.class).getProject_id()))
                .addPathParams("webHookID", Integer.toString(apiResponse.getBody(WebHooks.class).getId()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    @Test(groups = "createWebHook")
    public void createAWebHooksTest() throws JsonProcessingException {
        webHooksToSend.setEnabled(true);
        webHooksToSend.setWebhook_url("https://pastebin.com/fred");
        requestBuilder
                .addEndpoint("/projects/{projectID}/webhooks")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(webHooksToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
        WebHooks webHooks = apiResponse.getBody(WebHooks.class);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(webHooks.getKind(), "webhook");
        apiResponse.validateBodySchema("schemas/webhooks.json");
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
