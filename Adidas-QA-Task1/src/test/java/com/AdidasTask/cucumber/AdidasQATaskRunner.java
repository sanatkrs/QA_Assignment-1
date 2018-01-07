package com.AdidasTask.cucumber;

import org.junit.runner.RunWith;

import com.AdidasTask.testbase.TestBase;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/feature/")
public class AdidasQATaskRunner extends TestBase  {
	
	

}
