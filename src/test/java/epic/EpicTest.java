package epic;

import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.epic.Epic;
import entities.project.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EpicTest extends EpicBaseTest{

    @Test
    public void getAllEpicTest() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects/{projectId}/labels")
                .addPathParams("projectId", "2504472")
                .addMethod(ApiMethod.GET)
                .build();
        apiResponse = new ApiResponse(ApiManager.execute(requestBuilder.build()));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test(groups = "getEpic")
    public void getEpicTest() {
        requestBuilder
                .addEndpoint("/projects/{projectID}/epics/{epicID}")
                .addPathParams("projectID", apiResponse.getBody(Epic.class).getProject_id().toString())
                .addPathParams("epicID", apiResponse.getBody(Epic.class).getId().toString())
                .addMethod(ApiMethod.GET)
                .build();
        ApiManager.execute(requestBuilder.build());
        Epic epic = apiResponse.getBody(Epic.class);
        System.out.println("------------ " + epic.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        apiResponse.validateBodySchema("schemas/epic.json");
    }

    @Test(groups = "updateEpic")
    public void updateAEpicTest() throws JsonProcessingException {
        epicToSend.setName("tractor beams updated update");
        requestBuilder
                .addEndpoint("/projects/{projectID}/epics/{epicID}")
                .addPathParams("projectID", apiResponse.getBody(Epic.class).getProject_id().toString())
                .addPathParams("epicID", apiResponse.getBody(Epic.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(epicToSend))
                .addMethod(ApiMethod.PUT)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
        Epic epic = apiResponse.getBody(Epic.class);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(epic.getKind(), "epic");
        apiResponse.validateBodySchema("schemas/epic.json");
    }

    @Test(groups = "deleteEpic")
    public void deleteAEpic() {
        requestBuilder
                .addEndpoint("/projects/{projectID}/epics/{epicID}")
                .addPathParams("projectID", apiResponse.getBody(Epic.class).getProject_id().toString())
                .addPathParams("epicID", apiResponse.getBody(Epic.class).getId().toString())
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiManager.execute(requestBuilder.build());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }


    @Test(groups = "createEpic")
    public void createAEpicTest() throws JsonProcessingException {
        epicToSend.setName("Tractor Beams");
        requestBuilder
                .addEndpoint("/projects/{projectID}/epics")
                .addPathParams("projectID", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(epicToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
        Epic epic = apiResponse.getBody(Epic.class);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(epic.getKind(), "epic");
        apiResponse.validateBodySchema("schemas/epic.json");
    }
}
