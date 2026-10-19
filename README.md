# BitXchange

BitXchange is an demo cryptocurrency trading dashboard built with Java, Spring Boot, Thymeleaf, and MongoDB. It includes market-data views, user sign-up/login flows, watchlists, asset summaries, buy/sell and P2P transaction screens, and crypto-news display.

> **Disclaimer:** This is a demo project, not a production exchange or financial service. Do not use it for real-money trading or store real financial credentials in it.

## Technology stack

- Java (the Maven project currently targets Java 24)
- Spring Boot 3.5.0
- Spring MVC / Spring Web
- Thymeleaf templates
- Spring Data MongoDB
- Maven
- Binance market-data endpoints and NewsAPI (API key required for news)

## Main features

- Dashboard and market pages
- Cryptocurrency market data and live-price lookups
- Watchlist operations
- Asset snapshots and asset-value calculations
- Buy-order and transaction-history demo flows
- P2P buy/sell merchant screens
- User registration and login screens
- Crypto news retrieval

Actual behavior depends on valid MongoDB/API configuration and the current state of external APIs.

## Project structure

```text
src/main/java/com/example/bitxchange/
├── config/        # MongoDB configuration
├── controller/    # Spring MVC routes and request handling
├── dto/           # Request DTOs
├── interfac/      # Service interface(s)
├── model/         # MongoDB document/domain models
├── repository/    # Spring Data repositories
└── service/       # Business logic and external data fetching

src/main/resources/
├── templates/     # Thymeleaf HTML pages
├── static/        # CSS, JavaScript, images
└── application.properties
```

## Requirements

- JDK 24 (or update `pom.xml` to a Java version installed on your machine)
- MongoDB database/Atlas cluster
- NewsAPI key for the news feature
- Internet connection for external market/news data

## Configuration

Credentials are intentionally not included in this repository. Set environment variables before running the application.

### Windows PowerShell

```powershell
$env:MONGODB_URI="mongodb+srv://<username>:<password>@<cluster>/<database>?retryWrites=true&w=majority"
$env:NEWSAPI_KEY="<your-newsapi-key>"
.\mvnw.cmd spring-boot:run
```

For local MongoDB, use a URI such as `mongodb://localhost:27017/bitxchange` if the database is running locally.

The application reads these values from `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=${MONGODB_URI}
newsapi.key=${NEWSAPI_KEY:}
```

Do not commit real keys, database URIs, passwords, or `.env` files.

## Run from IntelliJ IDEA

1. Install and configure JDK 24, or change the Maven `java.version` property to your installed JDK.
2. Open this folder as a Maven project in IntelliJ IDEA.
3. Configure `MONGODB_URI` and (optionally) `NEWSAPI_KEY` in the Run Configuration environment variables.
4. Run `BitXchangeApplication.java`.
5. Open `http://localhost:8080`.

You can also run with the Maven wrapper from the project root:

```powershell
.\mvnw.cmd spring-boot:run
```

## Important security and functionality notes

This codebase still needs work before any production use:

- The current login service compares stored passwords as plain text. Use a password encoder (for example, BCrypt) before any real deployment.
- Review session management, authorization, CSRF protection, validation, and rate limiting.
- Several trading and P2P screens are demo flows; a recorded transaction is not proof of a real payment or exchange execution.
- Validate positive quantities and amounts, enforce ownership/authorization for user-specific data, and avoid trusting prices/amounts submitted by the browser.
- Add tests for controllers, services, repository behavior, and error handling.
- External API availability and response formats can change.

## Tests

Run:

```powershell
.\mvnw.cmd test
```

The included test class should be expanded to cover application behavior and MongoDB/API dependencies.

## License

No license is included. Add a license file if you want to specify reuse terms.
