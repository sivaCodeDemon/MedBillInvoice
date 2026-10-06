# Setup and local run

## Requirements

- Java 17
- Maven 3.9+
- Node.js 20+
- PostgreSQL 15+

## Backend

1. Create a local PostgreSQL database named `medbill_pro`.
2. Update credentials in `backend/src/main/resources/application.yml` if needed.
3. Run:

```bash
cd backend
mvn test
mvn spring-boot:run
```

## Frontend

```bash
cd frontend
npm install
npm run dev
```

## Default app URLs

- Frontend: `http://localhost:5173`
- API: `http://localhost:8080/api/v1`
- Swagger: `http://localhost:8080/swagger-ui.html`
- Actuator health: `http://localhost:8080/actuator/health`
