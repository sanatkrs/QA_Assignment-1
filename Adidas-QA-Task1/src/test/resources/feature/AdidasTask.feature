#Author: sanatkumarsingh@yahoo.co.in
Feature: Testing different requests on the Adidas API

  @SMOKE
  Scenario: Check if the Adidas API can be accessed by users
    When User sends a GET request to the list endpoint, they must get back a valid status code 200
	@APITest
  Scenario: Check if the response time is below 1 second
    When User sends a request to check the reponse time, Response time is below 1s
	@APITest
  Scenario: Check if all images are accessible
    When User gets all the components list
    Then all the images are accessible
	@APITest
  Scenario: Check if every component has at least analyAcs data “analytics_name” in it
    When User gets all the list of analyAcs data
    Then component has analyAcs data: “analytics_name” in it
