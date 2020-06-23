# localities-api
Microservice that consumes IBGE APIs to generate and download files with locality data.

**To run this application:** 

    $ mvn clean install
    $ java -jar target/evoluum-challenge-1.0.0.jar

**Application endpoints:**: 

    http://localhost:8000/api/file/csv
    http://localhost:8000/api/file/json
    http://localhost:8000/api/cities/{cityName}

                        

