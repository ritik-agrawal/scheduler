# Car Appointment Service

## Steps to setup the code and run

- For the database PostgreSql has been used. For setting up database [Database setup](#database-setup)
- After the docker is up. Please open IDE of your choice and set up the code. The code uses:
  - Java 17
  - Maven 3.1.2
- After the backend is running, import the openApi yml file to execute the requests by following points in [Importing OpenApi Documentation](#importing-openapi-documentation)
- After the document is successfully imported, right-click on the collection, now in the drop down find `Documentation.`
- The `Documentation` will describe the project.

## Database Setup

- In the project, find the folder `docker`.
- Use the command `docker-compose up` or `sudo docker-compose up` to setup the containerized postgresql database.

*Note*: If docker not installed in the system. Please download it.

## Importing OpenApi Documentation

- The `openapi.yaml` file is present in the folder `OpenApi Documentation` in the root folder.
- Import the file in postman.
- While importing please follow the below steps:
  - Click on the `import` button present in the postman.
  - In `Choose how to import the file` select the `OpenApi 3.0` option
  - In the left bottom cornor, find the 'view import settings' and click it.
  - In the opened window
    - Choose `URL` for Naming Requests
    - Choose `schema` for Parameter generation
    - Choose `Tags` for Folder organization
    - After choosing all the above click on the left arrow icon in the top left cornor of the window.
  - Now click on the `import` botton.
