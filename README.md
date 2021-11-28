To run locally:
1. Install Maven: https://maven.apache.org/install.html
2. Install and start Docker: https://docs.docker.com/get-docker
3. Clone this repository to your local machine
4. Build the server: `mvn clean package`
5. Navigate to the project directory: `cd hlsa-lab4`
6. Deploy all the services to Docker: `docker-compose up`

Usage example:
1. Execute POST request to `localhost:8080/book` with body: `{"name": "Designing Data-Intensive Applications", "author": "Martin Kleppmann", "content": "Book Content"}`
2. Copy the ID from the response
3. Open `localhost:8080/book/{id}` to retrieve the created book, where `{id}` is the copied ID