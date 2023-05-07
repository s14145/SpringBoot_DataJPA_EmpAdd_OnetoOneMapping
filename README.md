# Spring Boot Data JPA Employee Address One-to-One Mapping Project

This application starts at port 6060.

Entities are:
1. Employee
2. Address

**Application APIs are:**

1. GET Employee: http://localhost:6060/api/v1/employees/1

2. GET Employees: http://localhost:6060/api/v1/employees/

3. POST Employees: http://localhost:6060/api/v1/employees/

4. GET Addresses: http://localhost:6060/api/v1/addresses

H2 Database URI: http://localhost:6060/h2-console/


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
