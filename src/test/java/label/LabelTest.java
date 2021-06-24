package label;

import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.label.Label;
import entities.project.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LabelTest extends LabelBaseTest{

    @Test
    public void getAllLabelsTest() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}/labels")
                .addPathParams("projectId", "2504472")
                .addMethod(ApiMethod.GET)
                .build();
        apiResponse = new ApiResponse(ApiManager.execute(requestBuilder.build()));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test(groups = "getLabel")
    public void getLabelTest() {
        requestBuilder
                .addEndpoint("/projects/{projectID}/labels/{labelID}")
                .addPathParams("projectID", Integer.toString(apiResponse.getBody(Label.class).getProject_id()))
                .addPathParams("labelID", Integer.toString(apiResponse.getBody(Label.class).getId()))
                .addMethod(ApiMethod.GET)
                .build();
        ApiManager.execute(requestBuilder.build());
        Label label = apiResponse.getBody(Label.class);
        System.out.println("------------ " + label.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        apiResponse.validateBodySchema("schemas/label.json");
    }

    @Test(groups = "updateLabel")
    public void updateALabelTest() throws JsonProcessingException {
        labelToSend.setName("label 2 updated uuuupdate");
        requestBuilder
                .addEndpoint("/projects/{projectID}/labels/{labelID}")
                .addPathParams("projectID", Integer.toString(apiResponse.getBody(Label.class).getProject_id()))
                .addPathParams("labelID", Integer.toString(apiResponse.getBody(Label.class).getId()))
                .addBody(new ObjectMapper().writeValueAsString(labelToSend))
                .addMethod(ApiMethod.PUT)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
        Label label = apiResponse.getBody(Label.class);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(label.getKind(), "label");
        apiResponse.validateBodySchema("schemas/label.json");
    }

    @Test(groups = "deleteLabel")
    public void deleteALabel() {
        requestBuilder
                .addEndpoint("/projects/{projectID}/labels/{labelID}")
                .addPathParams("projectID", Integer.toString(apiResponse.getBody(Label.class).getProject_id()))
                .addPathParams("labelID", Integer.toString(apiResponse.getBody(Label.class).getId()))
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test(groups = "createLabel")
    public void createALabelTest() throws JsonProcessingException {
        labelToSend.setName("label 4 created");
        requestBuilder
                .addEndpoint("/projects/{projectID}/labels")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(labelToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
        Label label = apiResponse.getBody(Label.class);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(label.getKind(), "label");
        apiResponse.validateBodySchema("schemas/label.json");
    }
}
