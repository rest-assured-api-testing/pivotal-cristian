import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTest {
    ApiRequest apiRequest = new ApiRequest();

    @Test
    public void getAllProjectTest() {

//        apiRequest = new ApiRequest();
//        apiRequest.addHeader("X-TrackerToken", "b3b0d0a60d898c42c0bb3c1f7b7da0c2");
//        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
//        apiRequest.setEndpoint("/projects");
//        apiRequest.setMethod(ApiMethod.GET);
//
//        Response response = ApiManager.execute(apiRequest);
//        response.then().assertThat().statusCode(200);
        apiRequest = new ApiRequest();
        apiRequest.setToken("b3b0d0a60d898c42c0bb3c1f7b7da0c2");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
        apiRequest.setEndpoint("/projects");
        apiRequest.setMethod(ApiMethod.GET);

        Response response = ApiManager.execute(apiRequest);
        response.then().assertThat().statusCode(200);
    }

}
