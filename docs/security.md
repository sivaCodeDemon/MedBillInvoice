# Security and RBAC

The application uses Spring Security with stateless sessions and a demo JWT flow intended for production extension.

## Security controls

- Authentication with JWT and refresh tokens
- BCrypt-ready password storage model
- Role-based permissions enforcement
- CORS configuration for API endpoints
- Actuator health endpoints exposed without sensitive data
- API validation and exception handling for request safety
