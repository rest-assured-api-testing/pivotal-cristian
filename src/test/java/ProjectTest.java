import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.project.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTest extends ProjectBaseTest{
    @Test(groups = "getAllProject")
    public void getAllProjectTest() {
        requestBuilder
                .clearPathParams()
                .addEndpoint("/projects")
                .addMethod(ApiMethod.GET)
                .build();
        apiResponse = new ApiResponse(ApiManager.execute(requestBuilder.build()));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test(groups = "getProject")
    public void getProjectTest() {
        requestBuilder
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", apiResponse.getBody(Project.class).getId().toString())
                .addMethod(ApiMethod.GET)
                .build();
        ApiManager.execute(requestBuilder.build());
        Project project = apiResponse.getBody(Project.class);
        System.out.println("-------------" + project.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(project.getKind(), "project");
        apiResponse.validateBodySchema("schemas/project.json");
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test(groups = "updateProject")
    public void updateAProjectTest() throws JsonProcessingException {
        projectToSend.setName("projectc 100002 update");
        requestBuilder
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", apiResponse.getBody(Project.class).getId().toString())
                .addBody(new ObjectMapper().writeValueAsString(projectToSend))
                .addMethod(ApiMethod.PUT)
                .build();
        ApiManager.executeWithBody(requestBuilder.build());
        Project project = apiResponse.getBody(Project.class);
        System.out.println("-------------" + project.getName());
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(project.getKind(), "project");
        apiResponse.validateBodySchema("schemas/project.json");
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test(groups = "deleteProject")
    public void deleteAProject() {
        requestBuilder
                .addEndpoint("/projects/{projectId}")
                .addPathParams("projectId", apiResponse.getBody(Project.class).getId().toString())
                .addMethod(ApiMethod.DELETE)
                .build();
        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(requestBuilder.build()));
        Assert.assertEquals(apiResponse.getStatusCode(), 204);
    }

    @Test(groups = "createProject")
    public void createAProjectTest() throws JsonProcessingException {
        projectToSend.setName("project 100002");
        requestBuilder
                .addEndpoint("/projects")
                .addBody(new ObjectMapper().writeValueAsString(projectToSend))
                .addMethod(ApiMethod.POST)
                .build();
        apiResponse = ApiManager.executeWithBody(requestBuilder.build());
        Project project = apiResponse.getBody(Project.class);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(project.getKind(), "project");
        apiResponse.validateBodySchema("schemas/project.json");
    }
}
