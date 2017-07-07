package com.youtube.pkg;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import io.restassured.response.Response;
import org.testng.Assert;


import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Created by nisum on 7/6/17.
 */
public class StepDefForAPI {

    String url;
    Response resp;
    ResponseHolder responseHolder;
    Map<String,Object> responsemap;
    Map<String,String> query;


    @Given("^the apis are up and running for \"([^\"]*)\"$")
    public void the_apis_are_up_and_running_for(String url)  {

        this.url=url;
        resp=given().get(url).then().extract().response();
        //Assert.assertEquals(200,resp.getStatusCode());
        if(resp.getStatusCode()==200){
            System.out.println("Response is OK(200): API is Up and Running");
            System.out.println("\n");
        }
        else
        {
            System.out.println("Response is not OK(200): API is not Running");
            System.out.println("\n");
        }


    }
    @When("^a user performs a get request to \"([^\"]*)\"$")
    public void a_user_performs_a_get_request_to(String url) throws Throwable {

        this.url=url;

    }

    @And("^and perform the request$")
    public void andPerformTheRequest() throws Throwable {
        System.out.println("User is performing get request for the url - "+this.url+query);
        System.out.println("\n");
        if(query==null){
            resp=given().when().get(this.url);
        }
        else{
            resp=given().queryParams(query).when().get(this.url);
        }
        ResponseHolder.setResponse(resp);
        System.out.println("Performed the Get Request and Response message is Stored");
        System.out.println("\n");
    }

    @Then("^the response code should be (\\d+)$")
    public void theResponseCodeShouldBe(int statcode) throws Throwable {
        //Assert.assertEquals(statcode,ResponseHolder.getResponseCode());
        if(statcode==ResponseHolder.getResponseCode()){
            System.out.println("Response is OK - "+ResponseHolder.getResonseBody());
            System.out.println("\n");
        }
        else
        {
            System.out.println("Response is Not OK - "+ResponseHolder.getResonseBody());
            System.out.println("\n");
        }

    }


    @And("^I should see json response with pairs on the filtered \"([^\"]*)\" node$")
    public void iShouldSeeJsonResponseWithPairsOnTheFilteredNode(String filter, DataTable dataTable) throws Throwable {
        Map<String,String> query= new LinkedHashMap<String, String>();
        for(DataTableRow row:dataTable.getGherkinRows()){
            query.put(row.getCells().get(0),row.getCells().get(1));
        }

        ObjectReader reader = new ObjectMapper().reader(Map.class);

        responsemap=reader.readValue(ResponseHolder.getResonseBody());
        System.out.println(responsemap);

        responsemap=(Map<String, Object>) responsemap.get(filter);

        for(String key:query.keySet()){
            Assert.assertTrue(responsemap.containsKey(key));
            Assert.assertEquals(query.get(key),responsemap.get(key).toString());
        }

    }
}
