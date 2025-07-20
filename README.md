# Spring Boot Data JPA Employee Address One-to-One Mapping Project

This application starts at port 6060.

The technologies used in this application are:

1. Spring Boot Web

2. Spring Boot Data JPA

3. H2 Database

4. Lombok

5. Spring Boot Devtools

6. Hikari Connection Pool

7. Spring Boot Actuator

8. JWT for Token

9. TestContainers for Integration Testing (Make sure Docker Desktop is running in the machine)

10. Validator For Model/Entity

11. Prometheus for monitoring


**Entities are:**
1. Employee
2. Address
3. Privilege
4. Users

**Application APIs are:**

1. GET Employee: http://localhost:6060/project/api/v1/employees/1

2. GET Employees: http://localhost:6060/project/api/v1/employees/

3. POST Employees: http://localhost:6060/project/api/v1/employees/

4. GET Addresses: http://localhost:6060/project/api/v1/addresses

H2 Database URI: http://localhost:6060/project/h2-console/

Actuator URI: http://localhost:6060/project/actuator

**Sample POST request payload:**


[{

    "empName": "Ram",
    "empAge": 25,
    "address":  {
        "city": "New York",
        "addressType": "Permanent"
        }
        },{

    "empName": "Hari",
    "empAge": 40,
    "address":  {
        "city": "Miami",
        "addressType": "Permanent"
        }
        
}]


**Imp: Used docker image for Prometheus and Grafana**
1. Started Docker Desktop in local machine
2. Pulled docker image of both prometheus and grafana
   - docker pull prom/prometheus
   - docker pull grafana/grafana
3. Ran docker container of both prometheus and grafana (make sure prometheus.yml file is in your project's resource folder)
   - docker run -p 9090:9090 -v <<Project Path>>/src/main/resources/prometheus.yml prom/prometheus
   - docker run -d --name=grafana -p 3000:3000 grafana/grafana
4. Access Prometheus and Grafana
   - Prometheus: http://127.0.0.1:9090
   - Grafana: http://127.0.0.1:3000 (Default username and password is admin)
  
