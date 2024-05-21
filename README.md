# Booking Restaurant

## Installation

1. Download or clone 'booking restaurant' https://github.com/PalilloKun/booking-restaurant.git
2. Update Maven dependencies and reload the project
3. This project uses Java 17 
4. To test, please follow the Curl section

## Technologies
1. Spring Boot
2. Muserver
3. H2 Database
4. Java 17

## Curls

``` To set up total number of tables
curl -L -X POST 'http://localhost:8080/tables/' \
-H 'Content-Type: application/json' \
--data-raw '{

    "id": 1,
    "total": 50
}'
```

``` To set up a new client
curl -L -X POST 'http://localhost:8080/clients/' \
-H 'Content-Type: application/json' \
--data-raw '{
    
    "name":"Pedro",
    "surname": "Saul",
    "email": "pedro@saul.com"
}'
```
``` To create a new booking
curl -L -X POST 'http://localhost:8080/bookings/' \
-H 'Content-Type: application/json' \
--data-raw '{
"client":{
"id": 1
},
"customerName":"Pedro Saul",
"tableSize": 10,
"dateTime": "2024-05-28T10:34"
}'
```
