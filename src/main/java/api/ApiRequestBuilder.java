package api;

import io.restassured.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class ApiRequestBuilder implements IBuilder{

    private String baseUri;
    private String endpoint;
    private String body;
    private String token;
    private Enum<ApiMethod> method;
    private List<Header> headers;
    private Map<String, String> queryParams;
    private Map<String, String> pathParms;

    public ApiRequestBuilder() {
        this.headers = new ArrayList<>();
        this.queryParams = new HashMap<>();
        this.pathParms = new HashMap<>();
    }

    public ApiRequestBuilder addBaseUri(String baseUri) {
        this.baseUri = baseUri;
        return this;
    }

    public ApiRequestBuilder addEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public ApiRequestBuilder addBody(String body) {
        this.body = body;
        return this;
    }

    public ApiRequestBuilder addToken(String token) {
        this.token = token;
        return this;
    }

    public ApiRequestBuilder addMethod(Enum<ApiMethod> method) {
        this.method = method;
        return this;
    }

    public ApiRequestBuilder addHeader(final String header, final String value) {
        headers.add(new Header(header, value));
        return this;
    }

    public ApiRequestBuilder addQueryParams(final String param, final String value) {
        queryParams.put(param, value);
        return this;
    }

    public ApiRequestBuilder addPathParams(final String param, final String value) {
        pathParms.put(param, value);
        return this;
    }

    public ApiRequestBuilder clearPathParams() {
        pathParms.clear();
        return this;
    }

    @Override
    public ApiRequest build() {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setBaseUri(this.baseUri);
        apiRequest.setEndpoint(this.endpoint);
        apiRequest.setBody(this.body);
        apiRequest.setToken(this.token);
        apiRequest.setMethod(this.method);
        apiRequest.addHeaders(this.headers);
        apiRequest.addQueryParams(this.queryParams);
        apiRequest.addPathParams(this.pathParms);
        return apiRequest;
    }

}
