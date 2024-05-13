# Dining Review API

## Overview
This project implements a RESTful API for managing dining reviews. It allows users to submit reviews for restaurants and to search for restaurants based on various criteria.
It also allows an administrator to approve or reject reviews

## Technologies Used
- Spring Boot
- Spring Data JPA
- H2 Database
- Lombok

## Installation
1. Clone this repository to your local machine.
2. Ensure you have Java and Maven installed on your system.
3. Open your terminal or command prompt and navigate to the project directory.
4. Build the project using Maven: `mvn clean install`.
5. Run the application by using the following command: `java -jar target/dining-review-api.jar`.

## API Endpoints
- `/users`: GET (Retrieve all users), POST (Create a new user)
- `/users/{displayName}`: GET (Retrieve a user by display name), PUT (Update a user by display name)
- `/restaurants`: GET (Retrieve all restaurants), POST (Create a new restaurant)
- `/restaurants/{id}`: GET (Retrieve a restaurant by ID)
- `/restaurants/search`: GET (Search restaurants by zip code)
- `/restaurants/search/allergy`: GET (Search restaurants by zip code and allergy score)
- `/dining-reviews`: GET (Retrieve all dining reviews), POST (Submit a new dining review)
- `/dining-reviews/{id}`: GET (Retrieve a dining review by ID)
- `/admin/pending-dining-reviews`: GET (Retrieve pending dining reviews for admin)
- `/dining-reviews/approved/{id}`: GET (Retrieve approved dining reviews for a restaurant)
- `/admin/dining-reviews/{id}/approve`: PUT (Approve a dining review)
- `/admin/dining-reviews/{id}/reject`: PUT (Reject a dining review)

## Usage
- To retrieve all restaurants: `GET /restaurants`
- To retrieve a restaurant by ID: `GET /restaurants/{id}`
- To search restaurants by zip code: `GET /restaurants/search?zipcode={zipcode}`
- To search restaurants by zip code and allergy score: `GET /restaurants/search/allergy?zipcode={zipcode}`
- To submit a new dining review: `POST /dining-reviews`
- To retrieve all dining reviews: `GET /dining-reviews`
- For more endpoints, refer to the API Endpoints section.
