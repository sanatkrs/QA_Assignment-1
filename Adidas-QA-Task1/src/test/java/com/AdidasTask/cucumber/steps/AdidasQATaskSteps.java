package com.AdidasTask.cucumber.steps;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;

import com.AdidasTask.cucumber.serenity.AdidasTaskSerenitySteps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

public class AdidasQATaskSteps {

	@Steps
	AdidasTaskSerenitySteps steps;
	
	@When("^User sends a GET request to the list endpoint, they must get back a valid status code 200$")
	public void verify_status_code_200_for_listendpoint(){
		steps.Verify_status_Code();
		
	}
	
	@When("^User sends a request to check the reponse time, Response time is below 1s$")
	public void user_sends_a_request_to_check_the_reponse_time() throws Throwable {
		steps.testResponsTime();
	}


	@When("^User gets all the components list$")
	public void user_gets_all_the_components_list() throws Throwable {
		steps.GetUrls();
	   
	}

	@Then("^all the images are accessible$")
	public void all_the_images_are_accessible() throws Throwable {
		steps.Check_Response_Code();
	}

	@When("^User gets all the list of analyAcs data$")
	public void user_gets_all_the_list_of_analyAcs_data() throws Throwable {
		steps.GetAllComponents();
	}

	@Then("^component has analyAcs data: “analytics_name” in it$")
	public void component_has_analyAcs_data_analytics_name_in_it() throws Throwable {
		steps.Check_Analytics_Data();
	}


}
