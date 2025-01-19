# job_posting_backend

This application was generated using JHipster 8.7.1, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v8.7.1](https://www.jhipster.tech/documentation-archive/v8.7.1).

## API Documentation

### Authentication

#### Login

**Endpoint:** `POST /api/authenticate`

**Headers:**

```
Content-Type: application/json
```

**Request Body:**

```json
{
  "username": "admin",
  "password": "admin",
  "rememberMe": true
}
```

**Response (200 OK):**

```json
{
  "id_token": "eyJhbGciOiJIUzUxMiJ9..."
}
```

### Job Postings API

#### 1. Create New Job Posting

**Endpoint:** `POST /api/job-postings`

**Headers:**

```
Authorization: Bearer {jwt_token}
Content-Type: application/json
```

**Request Body:**

```json
{
  "title": "Senior Java Developer",
  "description": "We are looking for an experienced Java developer...",
  "requirements": "- 5+ years Java experience\n- Spring Boot knowledge\n- PostgreSQL experience",
  "companyName": "Tech Company Ltd",
  "location": "İstanbul, Turkey",
  "salaryRange": "30000-45000 TL",
  "expiryDate": "2024-03-19T00:00:00Z"
}
```

**Response (201 Created):**

```json
{
  "id": 1,
  "title": "Senior Java Developer",
  "description": "We are looking for an experienced Java developer...",
  "requirements": "- 5+ years Java experience\n- Spring Boot knowledge\n- PostgreSQL experience",
  "companyName": "Tech Company Ltd",
  "location": "İstanbul, Turkey",
  "salaryRange": "30000-45000 TL",
  "createdDate": "2024-01-19T19:45:23Z",
  "expiryDate": "2024-03-19T00:00:00Z",
  "status": "ACTIVE",
  "user": {
    "id": "1",
    "login": "admin"
  }
}
```

#### 2. List All Active Job Postings

**Endpoint:** `GET /api/job-postings`

**Headers:**

```
Accept: application/json
```

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "title": "Senior Java Developer",
    "description": "We are looking for an experienced Java developer...",
    "requirements": "- 5+ years Java experience\n- Spring Boot knowledge\n- PostgreSQL experience",
    "companyName": "Tech Company Ltd",
    "location": "İstanbul, Turkey",
    "salaryRange": "30000-45000 TL",
    "createdDate": "2024-01-19T19:45:23Z",
    "expiryDate": "2024-03-19T00:00:00Z",
    "status": "ACTIVE",
    "user": {
      "id": "1",
      "login": "admin"
    }
  }
]
```

#### 3. List User's Own Job Postings

**Endpoint:** `GET /api/job-postings/my-postings`

**Headers:**

```
Authorization: Bearer {jwt_token}
Accept: application/json
```

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "title": "Senior Java Developer"
    // ... other fields
  }
]
```

#### 4. Get Job Posting Details

**Endpoint:** `GET /api/job-postings/{id}`

**Headers:**

```
Accept: application/json
```

**Response (200 OK):**

```json
{
  "id": 1,
  "title": "Senior Java Developer"
  // ... other fields
}
```

### Error Responses

#### 401 Unauthorized

```json
{
  "type": "https://www.jhipster.tech/problem/problem-with-message",
  "title": "Unauthorized",
  "status": 401,
  "detail": "Authentication failed",
  "path": "/api/job-postings/my-postings",
  "message": "error.http.401"
}
```

#### 404 Not Found

```json
{
  "type": "https://www.jhipster.tech/problem/problem-with-message",
  "title": "Not Found",
  "status": 404,
  "detail": "Job posting not found",
  "path": "/api/job-postings/999",
  "message": "error.http.404"
}
```

#### 400 Bad Request

```json
{
  "type": "https://www.jhipster.tech/problem/constraint-violation",
  "title": "Method argument not valid",
  "status": 400,
  "detail": "Input validation failed",
  "fieldErrors": [
    {
      "objectName": "jobPosting",
      "field": "title",
      "message": "Title is required"
    }
  ]
}
```

## Project Structure

Node is required for generation and recommended for development. `package.json` is always generated for a better development experience with prettier, commit hooks, scripts and so on.

In the project root, JHipster generates configuration files for tools like git, prettier, eslint, husky, and others that are well known and you can find references in the web.

`/src/*` structure follows default Java structure.

- `.yo-rc.json` - Yeoman configuration file
  JHipster configuration is stored in this file at `generator-jhipster` key. You may find `generator-jhipster-*` for specific blueprints configuration.
- `.yo-resolve` (optional) - Yeoman conflict resolver
  Allows to use a specific action when conflicts are found skipping prompts for files that matches a pattern. Each line should match `[pattern] [action]` with pattern been a [Minimatch](https://github.com/isaacs/minimatch#minimatch) pattern and action been one of skip (default if omitted) or force. Lines starting with `#` are considered comments and are ignored.
- `.jhipster/*.json` - JHipster entity configuration files
- `/src/main/docker` - Docker configurations for the application and services that the application depends on

## Development

To start your application in the dev profile, run:

```
./mvnw
```

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

## Building for production

### Packaging as jar

To build the final jar and optimize the myapplication application for production, run:

```
./mvnw -Pprod clean verify
```

To ensure everything worked, run:

```
java -jar target/*.jar
```

Refer to [Using JHipster in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

```
./mvnw -Pprod,war clean verify
```

### JHipster Control Center

JHipster Control Center can help you manage and control your application(s). You can start a local control center server (accessible on http://localhost:7419) with:

```
docker compose -f src/main/docker/jhipster-control-center.yml up
```

## Testing

### Spring Boot tests

To launch your application's tests, run:

```
./mvnw verify
```

## Others

### Code quality using Sonar

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker compose -f src/main/docker/sonar.yml up -d
```

Note: we have turned off forced authentication redirect for UI in [src/main/docker/sonar.yml](src/main/docker/sonar.yml) for out of the box experience while trying out SonarQube, for real use cases turn it back on.

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven plugin.

Then, run a Sonar analysis:

```
./mvnw -Pprod clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=admin
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar -Dsonar.login=admin -Dsonar.password=admin
```

Additionally, Instead of passing `sonar.password` and `sonar.login` as CLI arguments, these parameters can be configured from [sonar-project.properties](sonar-project.properties) as shown below:

```
sonar.login=admin
sonar.password=admin
```

For more information, refer to the [Code quality page][].

### Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a postgresql database in a docker container, run:

```
docker compose -f src/main/docker/postgresql.yml up -d
```

To stop it and remove the container, run:

```
docker compose -f src/main/docker/postgresql.yml down
```

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

```
npm run java:docker
```

Or build a arm64 docker image when using an arm64 processor os like MacOS with M1 processor family running:

```
npm run java:docker:arm64
```

Then run:

```
docker compose -f src/main/docker/app.yml up -d
```

When running Docker Desktop on MacOS Big Sur or later, consider enabling experimental `Use the new Virtualization framework` for better processing performance ([disk access performance is worse](https://github.com/docker/roadmap/issues/7)).

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[JHipster Homepage and latest documentation]: https://www.jhipster.tech
[JHipster 8.7.1 archive]: https://www.jhipster.tech/documentation-archive/v8.7.1
[Using JHipster in development]: https://www.jhipster.tech/documentation-archive/v8.7.1/development/
[Using Docker and Docker-Compose]: https://www.jhipster.tech/documentation-archive/v8.7.1/docker-compose
[Using JHipster in production]: https://www.jhipster.tech/documentation-archive/v8.7.1/production/
[Running tests page]: https://www.jhipster.tech/documentation-archive/v8.7.1/running-tests/
[Code quality page]: https://www.jhipster.tech/documentation-archive/v8.7.1/code-quality/
[Setting up Continuous Integration]: https://www.jhipster.tech/documentation-archive/v8.7.1/setting-up-ci/
[Node.js]: https://nodejs.org/
[NPM]: https://www.npmjs.com/
