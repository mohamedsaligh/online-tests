# Friends Management

> Draft Version

## Completed
 1. Initial Setup
 2. Database Connection (testing)
 3. Add services (including validations)
 4. Database table creations (in memory db optional)

## TODO
 5. Migrate to Heroku
 6. Unit Testing and Documentation


## Completed Service is available at: https://online-test-friends.herokuapp.com/


### Due to time constraint
 I could not host the application in cloud.
 1) Create a postgres database 'friendmgmt' in localhost and play the sql file (`database-scripts.sql`) in it.
 2) Start the application using main function 'com.saligh.friends.init.Applicatoin'.
 3) Submit the request to local host using post 8080
 -   Create Friend: http://localhost:8080/api/friend/create
 -   List of Friends: http://localhost:8080/api/friend/list
 -   Common Friends: http://localhost:8080/api/friend/common
 -   Subscribe: http://localhost:8080/api/friend/subscribe
 -   Block: http://localhost:8080/api/friend/block
 -   Get Updates: http://localhost:8080/api/friend/updatelist
 4) Use any Postman or SoapUI to test the services for now. This will be enhanced further.


