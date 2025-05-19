# Smart Home Appliance Control

This is a simple Spring Boot application written in Java 11 to control smart home appliances such as Light, Fan, and Air Conditioner.

## Features

- Control appliances with unique on/off logic:
    - Light: Turn off by toggling switch to "off"
    - Fan: Turn off by reducing speed to zero (0 = off, speeds 1 and 2 available)
    - Air Conditioner: Turn off by setting thermostat to "off" mode
- Annual automatic system update on January 1st, 1:00 AM, which turns off all appliances
- REST API endpoints to turn appliances on/off, adjust fan speed, and set AC mode
- Unit tests using JUnit 5 to verify appliance behavior

## Technologies Used

- Java 11
- Spring Boot
- Maven
- JUnit 5

## Running the Application

1. Clone the repository:

   git clone https://github.com/psneha1991/home-appliance-control-solution.git
   cd home-appliance-control-solution

2. Build and Run the Application
   ./mvnw spring-boot:run
3. Run the tests
   ./mvnw test

4. REST API to control the appliances.

- Turn Light ON/OFF
  curl -X POST http://localhost:8080/api/appliances/light/on
- Fan turn ON/OFF
  curl -X POST http://localhost:8080/api/appliances/fan/on
  curl -X POST http://localhost:8080/api/appliances/fan/off
  - Fan speed adjustment
    curl -X POST http://localhost:8080/api/appliances/fan/speed/2
    curl -X POST http://localhost:8080/api/appliances/fan/speed/1
- AC turn ON/OFF
  curl -X POST "http://localhost:8080/api/appliances/ac/mode?mode=off"
  curl -X POST "http://localhost:8080/api/appliances/ac/mode?mode=on"
  curl -X POST "http://localhost:8080/api/appliances/ac/mode?mode=cool"
- Status of various appliances
  curl -X POST http://localhost:8080/api/appliances/light/status
  curl -X POST http://localhost:8080/api/appliances/fan/status
  curl -X POST http://localhost:8080/api/appliances/ac/status
- To check the scheduler
  Quick testing done by modifying the below line:
  @Scheduled(cron = "*/10 * * * * *") // Every 10 seconds
  In the test case - created TurnOffAll.



