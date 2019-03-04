#  Wolf Coding Test
This application extends to the Google Apis and the Open Weather Api to gather information given a zip code.
The information this application returns is the city associated with the zip code, the elevation for the zip code, the time zone
for the zip code, and current temperature for the provided zipcode.


## Run the application
This project uses Maven.
The application can be run either with a script or by running the jar.

#### To use the script:
run the following command in the terminal `./mvnw spring-boot:run`

#### Build the jar file
 Build the jar file with the following command `./mvnw clean package`
 To run the application, from a terminal execute the following command `java -jar target/WolfCodingTest-0.0.1-SNAPSHOT.jar`
 
### Make request
Once the application is running, one is able to obtain information by curling the endpoint, or using a tool such as Postman 
if that is a preferred tool. 
There are two endpoints.  One endpoint returns a sentence containing information about the queried zip code.  The other
endpoint returns a json structured format containing the current temp, elevation, time zone, and city name.

The endpoint for the sentence is obtained with the following sample curl command `curl http://localhost:8080/getMessage?zipcode=97702
`

The endpoint for the json structure is obtained with the following sample curl command `curl http://localhost:8080/getInfo?zipcode=97702
`
 
 
 ### Run the application tests
 To run the tests of this application from the command line issue the following command `mvn test`
 
 
