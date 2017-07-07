package com.youtube.pkg;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by nisum on 7/6/17.
 */
public class StepDefinitions {

    @Given("^sample feature file is ready$")
    public void sample_feature_file_is_ready()  {
       System.out.println("This is Given Statement");
    }

    @When("^I run the feature file$")
    public void i_run_the_feature_file() {
        System.out.println("This is When Statement");
    }

    @Then("^run should be successful$")
    public void run_should_be_successful() throws Throwable {
        System.out.println("Hello Arun, Welcome to Cucumber");
    }
}
