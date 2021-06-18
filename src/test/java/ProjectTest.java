import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.project.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTest {
//    ApiRequest apiRequest = new ApiRequest();

    @Test
    public void getAllProjectTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void getProjectTest() {

        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", "2505289")
                .addMethod(ApiMethod.GET)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Project project = apiResponse.getBody(Project.class);
        System.out.println("------------ " + project.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        apiResponse.validateBodySchema("schemas/project.json");
    }

    @Test
    public void createAProjectTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        Project projectToSend = new Project();
        projectToSend.setName("Project created from  java 2");

        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects")
                .addBody(new ObjectMapper().writeValueAsString(projectToSend))
                .addMethod(ApiMethod.POST)
                .build();

        System.out.println("+++++++++++++"+projectToSend.toString());


        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Project project = apiResponse.getBody(Project.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(project.getKind(), "project");
        apiResponse.validateBodySchema("schemas/project.json");
    }

    @Test
    public void deleteAProject() {
        ApiRequest apiRequest = new ApiRequest();
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", "2505289")
                .addMethod(ApiMethod.DELETE)
                .build();

        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 204);
    }

    @Test
    public void updateAProjectTest() throws JsonProcessingException {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        Project projectToSend = new Project();
        projectToSend.setName("Project created from  java 2 updated");
        ApiRequest apiRequest = new ApiRequest();

        apiRequest = requestBuilder.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2")
                .addBaseUri("https://www.pivotaltracker.com/services/v5")
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", "2505289")
                .addBody(new ObjectMapper().writeValueAsString(projectToSend))
                .addMethod(ApiMethod.PUT)
                .build();

        System.out.println("+++++++++++++"+projectToSend.toString());
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        Project project = apiResponse.getBody(Project.class);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(project.getKind(), "project");
        apiResponse.validateBodySchema("schemas/project.json");
    }

}
