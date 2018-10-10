# Campaign App

Campaign app is a full stack solution and designed for presenting predefined campaigns to the users.    

Its backend is developed in Java using Spring Boot framework. The reason behind Spring boot choice is, it is lightweight, and its production ready features let developers progress faster and easier. It also includes many principal libraries in starter package as well.

When app starts, it saves given JSON data to MongoDB. Mongo is used because of the complex and semi-structured data in given JSON file.  

Backend application runs in docker with a linked docker mongoDB container.

Backend application developed with tdd approach including unit tests for all classes and integration tests for required ones. For integration test an embedded mongoDB is configured in order to spare persistence layer with production code and tests.

Front-end is developed in React.js. React's component approach makes rendering easy for complex data structure such in the given data.json file. Material-UI components used in overview and detailed presentation views.

##Installation

To install app, Docker and node should be installed beforehand. Backend and front-end repositories separated so installation will be 2 steps.

###Backend
Once docker is installed, only command is required to run backend app provided below.

```docker-compose -f docker-compose.yml up --build ```

###Front-end
Front-end project created using [create-react-app](https://github.com/facebook/create-react-app), which provides simple way to start React projects without any external configuration.

Once npm installed, below command should be run in project directory, react-app:

```npm start```

This runs app in development mode and opens landing page at [http://localhost:3000](http://localhost:3000/) in browser.

##Technologies used in project
- Java 8
- Spring Boot
- Docker
- MongoDB
- Lombok
- Mockito
- JUnit
- React.js
- MaterialUI
- Axios

##Features

###Overview Listing of Campaigns
In landing page users able to see overview campaign list in a table.

To do this,
- Client sends an HTTP GET request to API at [http://localhost:8080/campaign/all](http://localhost:3000/campaign/all) 
- REST service endpoint at server, accepts request and returns JSON data which stored in mongoDB.
- Client app gets JSON objects and renders in browser.

###Detailed View of Campaigns
Each row has DETAILS button at the end column in table.
When user clicks on button, a modal opens and lists all campaign details for selected campaign.
The presentation designed as, each list item represents an inner object in JSON.