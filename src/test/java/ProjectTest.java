import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import entities.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTest {
//    ApiRequest apiRequest = new ApiRequest();

    @Test
    public void getAllProjectTest() {

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setToken("b3b0d0a60d898c42c0bb3c1f7b7da0c2");
//        apiRequest.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
        apiRequest.setEndpoint("/projects");
        apiRequest.setMethod(ApiMethod.GET);

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void getProjectTest() {

        ApiRequest apiRequest = new ApiRequest();
//        apiRequest.setToken("b3b0d0a60d898c42c0bb3c1f7b7da0c2");
        apiRequest.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
        apiRequest.setEndpoint("/projects/{projectId}");
        apiRequest.addPathParam("projectId", "2504501");
        apiRequest.setMethod(ApiMethod.GET);

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Project project = apiResponse.getBody(Project.class);
        System.out.println("------------ " + project.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        apiResponse.validateBodySchema("schemas/project.json");
    }

}
