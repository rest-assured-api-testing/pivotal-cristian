import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    public void createAProjectTest() throws JsonProcessingException {
        Project projectToSend = new Project();
        projectToSend.setName("Project created from  java");

        ApiRequest apiRequest = new ApiRequest();
//        apiRequest.setToken("b3b0d0a60d898c42c0bb3c1f7b7da0c2");
        apiRequest.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
        apiRequest.setEndpoint("/projects");
        apiRequest.setBody(new ObjectMapper().writeValueAsString(projectToSend));
        apiRequest.setMethod(ApiMethod.POST);

        System.out.println("+++++++++++++"+projectToSend.toString());

//        ApiResponse apiResponse = new ApiResponse(ApiManager.executeWithBody(apiRequest));
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Project project = apiResponse.getBody(Project.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(project.getKind(), "project");
        apiResponse.validateBodySchema("schemas/project.json");
    }

    @Test
    public void deleteAProject() {
        ApiRequest apiRequest = new ApiRequest();
//        apiRequest.setToken("b3b0d0a60d898c42c0bb3c1f7b7da0c2");
        apiRequest.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
        apiRequest.setEndpoint("/projects/{projectId}");
        apiRequest.addPathParam("projectId", "2505108");
        apiRequest.setMethod(ApiMethod.DELETE);

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 204);
    }

    @Test
    public void updateAProjectTest() throws JsonProcessingException {
        Project projectToSend = new Project();
        projectToSend.setName("Project created from  java updated");

        ApiRequest apiRequest = new ApiRequest();
//        apiRequest.setToken("b3b0d0a60d898c42c0bb3c1f7b7da0c2");2505110
        apiRequest.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
        apiRequest.setEndpoint("/projects/{projectId}");
        apiRequest.addPathParam("projectId", "2505110");
        apiRequest.setBody(new ObjectMapper().writeValueAsString(projectToSend));
        apiRequest.setMethod(ApiMethod.PUT);

        System.out.println("+++++++++++++"+projectToSend.toString());

//        ApiResponse apiResponse = new ApiResponse(ApiManager.executeWithBody(apiRequest));
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Project project = apiResponse.getBody(Project.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(project.getKind(), "project");
        apiResponse.validateBodySchema("schemas/project.json");
    }

}
