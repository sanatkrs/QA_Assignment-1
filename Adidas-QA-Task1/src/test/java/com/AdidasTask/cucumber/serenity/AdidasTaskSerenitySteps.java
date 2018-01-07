package com.AdidasTask.cucumber.serenity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.AdidasTask.model.AdidasTaskClass;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class AdidasTaskSerenitySteps {
	Logger logger = LoggerFactory.getLogger(AdidasTaskSerenitySteps.class);
	AdidasTaskClass httpClient;
	
		@Step("Verifying the status")
		public void Verify_status_Code(){
			
			SerenityRest.rest()
			.spec(com.AdidasTask.utils.ReuseableSpecifications.getGenericRequestSpec())
			.given()
			.when()
			.get("?url=index.html")
			.then()
			.statusCode(200)
			.log()
			.all();
			logger.info("Smoke Test Passed, Status Code found 200");
			}
	
	
		@Step("Checking the Response Time")
		public void testResponsTime(){
		
		Response response = SerenityRest.rest()
			.given()
			.when()
			.get("?url=index.html");		
		int time = (int) response.then().extract().time();
		logger.info("response time is " + time + " miliseconds");				
		assertThat ("The Response Time",time, Matchers.lessThan(1000));
		
		}

		@Step("Getting the List of Urls")
		public List<String> GetUrls() {
		Response response = SerenityRest.rest()
			.given()
			.when()
			.get("?url=index.html");
		
		List<List<String>> deskTopImageUrl = 
			response
			.then()
			.extract()
			.path("component_presentations.component.content_fields.items.background_media.desktop_image.findAll{it.url=~/.*jpg/}.url");
		List<String> Actual_DesktopUrls = deskTopImageUrl.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
		
		logger.info("Desktop Image url: " + Actual_DesktopUrls);
		
		
		List<List<String>> deskTopImageUrl2 = 
			response
			.then()
			.extract()
			.path("component_presentations.component.content_fields.items.media_items.desktop_image.findAll{it.url=~/.*jpg/}.url");
		
		List<String> Actual_DesktopUrls2 = deskTopImageUrl2.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
		System.out.println("Desktop Image url-2: " + Actual_DesktopUrls2);
		
		List<List<String>> tabletImageUrl = 
			response
			.then()
			.extract()
			.path("component_presentations.component.content_fields.items.background_media.tablet_image.findAll{it.url=~/.*jpg/}.url");
		
		List<String> Actual_tabletImageUrl = tabletImageUrl.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
		logger.info("TabletImageURL: " + Actual_tabletImageUrl);
		
		
		List<List<String>> tabletImageUrl2 = 
			response
			.then()
			.extract()
			.path("component_presentations.component.content_fields.items.media_items.tablet_image.findAll{it.url=~/.*jpg/}.url");
		
		List<String> Actual_tabletImageUrl2 = tabletImageUrl2.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
		
		logger.info("TabletImageURL-2: " + tabletImageUrl2);
		
		List<List<String>> mobileImageUrl = 
			response
			.then()
			.extract()
			.path("component_presentations.component.content_fields.items.background_media.mobile_image.findAll{it.url=~/.*jpg/}.url");
		
		List<String> Actual_mobileImageUrl = mobileImageUrl.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
		
		logger.info("MobileImageURL: " + mobileImageUrl);
		
		
		List<List<String>> mobileImageUrl2 = 
			response
			.then()
			.extract()
			.path("component_presentations.component.content_fields.items.media_items.mobile_image.findAll{it.url=~/.*jpg/}.url");
		
		List<String> Actual_mobileImageUrl2 = mobileImageUrl2.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
		
		logger.info("MobileImageURL-2: " + Actual_mobileImageUrl2);
		
		
		List<List<String>> AllUrls = new ArrayList<List<String>>();
		
		AllUrls.add(Actual_DesktopUrls); 
		AllUrls.add(Actual_DesktopUrls2); 
		AllUrls.add(Actual_tabletImageUrl);
		AllUrls.add(Actual_tabletImageUrl2);
		AllUrls.add(Actual_mobileImageUrl); 
		AllUrls.add(Actual_mobileImageUrl2);
		System.out.println(AllUrls);
		System.out.println(AllUrls.size());
		
		
		List<String> Actual_Urls = AllUrls.stream()
            .flatMap(list -> list.stream())
            .collect(Collectors.toList());
		
			
		logger.info("Actal Urls found" + Actual_Urls.size());
				
				
			return Actual_Urls;
				
	}
	
	@Step("Verifying All Urls Response Code")
	public void Check_Response_Code() throws IOException{
		httpClient = new AdidasTaskClass();
		 List<String> ImageUrls = GetUrls();
		for(int i=0; i < ImageUrls.size(); i++ ){
			
			String Urls = ImageUrls.get(i);
			int response =	httpClient.HttpUrlConnect(Urls);
			logger.info("----------> " + response);
			assertThat ("The image loads correctly",response, Matchers.is(200));
	}
	}
	
	
	
	@Step("Getting List of Components")
	public List<Map<String, String>> GetAllComponents(){
		Response response = SerenityRest.rest().
				given()
				.when()
				.get("?url=index.html");
		List<Map<String, String>> ComponentsList = 
				response
				.then()
				.extract()
				.path("component_presentations.component");
        assertThat ("There are components in the api",ComponentsList.size(),greaterThan(0));
        logger.info("Total no of Component found" + ComponentsList);
		return ComponentsList;
		
		
	}
		
	@Step("Checking for 'analytics_name' in the list")
	public void Check_Analytics_Data(){
		int count = 0;
		for(int i = 0; i < GetAllComponents().size(); i++ ){
			boolean Data = GetAllComponents().get(i).toString().contains("analytics_name=");	
			if(Data==true){
				logger.info(i+"th " + "Component has analytics data");
				count++;
			}
			else{
				logger.info(i+"th " + "Component has no analytics data");
			}
		}
		logger.info("Total presence of analytics_name ---> " + count );	
		logger.info("Total components ---> " + GetAllComponents().size() );
		assertThat ("analytics_name occurence in each component should be 1 ",count, Matchers.is(GetAllComponents().size()));
		
		}
	
			
			
			}