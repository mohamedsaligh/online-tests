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


### Due to time constraint
 I could not host the application in cloud.
 1) Create a database 'friendmgmt' and play the sql file (`database-scripts.sql`) in it.
 2) Start the application using main function 'com.saligh.friends.init.Applicatoin'.
 3) Submit the request to local host using post 8080
 -   a) Create Friend: http://localhost:8080/api/friend/create
 -   b) List of Friends: http://localhost:8080/api/friend/list
 -   c) Common Friends: http://localhost:8080/api/friend/common
 -   d) Subscribe: http://localhost:8080/api/friend/subscribe
 -   d) Block: http://localhost:8080/api/friend/block
 -   e) Get Updates: http://localhost:8080/api/friend/updatelist
 4) Use any Postman or SoapUI to test the services for now. This will be enhanced further.


