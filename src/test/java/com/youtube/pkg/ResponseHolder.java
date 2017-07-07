package com.youtube.pkg;
import io.restassured.http.Headers;
import io.restassured.response.Response;
/**
 * Created by nisum on 7/6/17.
 */
public class ResponseHolder {

    public static Response response;
    public static int responseCode;
    public static String responseBody;
    public static Headers responseHeaders;

    public static void setResponse(Response response){
        ResponseHolder.response=response;
    }

    public static Response getResponse(){
        return response;
    }

    public static String getResonseBody(){

        responseBody=response.asString();

        return responseBody;
    }

    public static int getResponseCode(){
        responseCode=response.getStatusCode();
        return responseCode;
    }

    public static Headers getResponseHeaders(){
        responseHeaders=response.getHeaders();
        return responseHeaders;
    }

}
