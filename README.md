# EFPM - Externally Funded Project Management

Full-stack web application for managing externally funded faculty/project records.

## Features

- User registration and login
- JWT-secured backend API
- Add funded project records with details such as title, principal investigator, academic year, funding agency, amount, dates, and summary
- Upload supporting files for projects and status updates
- Dashboard for browsing and filtering project records
- Status update workflow for project progress
- Download/export endpoint for filtered project data
- Scheduled notification service scaffold

## Tech Stack

- Backend: Java, Spring Boot, Spring Security, JWT, MongoDB, Maven
- Frontend: Vue, Vue Router, Pinia, Axios

## Project Structure

```text
efpm-backend1/    Spring Boot API
efpm-frontend/    Vue frontend
```

## Run Backend

```bash
cd efpm-backend1
./mvnw spring-boot:run
```

On Windows:

```bash
cd efpm-backend1
mvnw.cmd spring-boot:run
```

## Run Frontend

```bash
cd efpm-frontend
npm install
npm run serve
```

## GitHub Notes

Generated build folders, dependency folders, uploaded files, and local secrets are ignored.
