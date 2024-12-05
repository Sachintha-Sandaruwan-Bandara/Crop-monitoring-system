<h1 align="center" color="Green">
  Green Shadow (Pvt) Ltd. Agricultural Management System - Backend
</h1>


## Description

The Green Shadow (Pvt) Ltd. Agricultural Management System - Frontend is the user interface for managing agricultural operations at Green Shadow. It allows users to interact with the system to manage fields, crops, staff, vehicles, equipment, and monitoring logs. The frontend communicates with the backend API, which is built with Spring Boot, to perform CRUD operations on various agricultural entities.


## Features

1. User Login: Provides role-based authentication (MANAGER, ADMINISTRATIVE, SCIENTIST).

2. Field Management: Allows users to view, add, update, and delete field details.

3. Crop Management: Manage crop types, their growth stages, and field allocations.

4. Staff Management: Manage human resources, including their assignments to fields and vehicles.

5. Vehicle Management: Assign and manage vehicles for staff operations.

6. Equipment Management: Manage agricultural equipment, including assignments to fields and staff.

6. Monitoring Logs: Record observations and activities related to crops and fields.

## Technologies Used

1. Spring Boot
2. Spring Data JPA
3. Spring Web MVC
4. Spring Security
5. Spring Validation
6. JWT Authentication
7. Lombok (for boilerplate code reduction)
8. Model Mapper (for entity DTO mapping)
9. Jackson (for JSON processing)

## Database:

MySQL (RDBMS)

##   Security Setup

1. Manager: Full access to all CRUD operations.
2. Administrative: Limited access (cannot edit crop or field data).
3. Scientist: Limited access (cannot modify staff, vehicle, or equipment data)


## Architecture Overview

1. *Entities:* Representations for Crop , Email , Equipment , Field , Staff , User , Vehicle and Monitoring Log
2. *Data Transfer Objects (DTOs):* Includes CropDTO, EmailDTO, EquipmentDTO , FieldDTO , StaffDTO , UserDTO , VehicleDTO and MoniterLogDTO
3. *Repositories:* Interfaces for database operations.
4. *Services:* Business logic for manage.
5. *Controllers:* API endpoints for handle Requests.
6. *Utilities:* Helper classes for tasks.
7. *Exceptions:* Custom error handling mechanisms for specific scenarios
8. *Configuration:* Application setup classes like application.properties,application-dev.properties


## Validation
Data validation is enforced through Hibernate Validator annotations within the DTO classes, ensuring both data integrity and accuracy.

## Logging
Logging is set up with Logback, capturing logs both in the console and in a dedicated file.


## API Documentation

*POST /login:* Authenticate a user.
*GET /fields:* Get all fields.
*POST /crops:* Add a new crop.
*GET /vehicles:* Get all vehicles.
*PUT /staff/{id}:* Update staff details.
*DELETE /equipment/{id}:* Delete an equipment.

To view this project API Documentation

Refer to the [ Postman API Documentation](https://documenter.getpostman.com/view/34708061/2sAYBbco8D) for detailed API endpoints and usage instructions.


## License

This project is licensed under the MIT License - see the [ MIT License](https://github.com/Sachintha-Sandaruwan-Bandara/Crop-monitoring-system?tab=MIT-1-ov-file) file for details.

<p align="center">
  &copy; 2024 Sachintha Sandaruwan
</p>
