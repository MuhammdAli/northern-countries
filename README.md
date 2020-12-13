# northern-countries

## Overview

	REST service that takes IP addresses, checks which countries they are from and returns list
	of countries from the northern hemisphere. 

	* In order to get information about IP addresses, API uses IP IP-API: https://ip-api.com/docs.
		
	* Uses latitude value to decide if IP address comes from a country from the northern hemisphere.

## Requirements for the service API:

	* Service exposes one endpoint for a GET request
	* The endpoint should accept list of IP addresses passed as request parameters
	* The endpoint should accept at least 1 and maximum 5 ip addresses
	* If among the passed IP addresses there are addresses from countries from the northern hemisphere, service should return these country names.
	* Response contains list of unique names (no repetitions of names) sorted alphabetically

### Request:
	
	curl "http://localhost:8888/northcountries?ip=8.8.8.8&ip=8.8.0.0&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0"

### Response: 

	{  
	   "northcountries":[  
	      "Colombia",
	      "Japan",
	      "United States"
	   ]
	}


## Some Points

	* The API can be tested via Swagger.
	* The Test Case Coverage is About 60% With Integration Tests.
	* Service is ready to be deployed on Azure through Azure Devops
	* Service can be started using using docker
	* Service can be started using ` java -jar target\finder-0.0.1-SNAPSHOT.jar from project root (Please make sure you run ` mvn clean package -DskipTests`)`.
	
