 🌦️ Weather Info REST API (Spring Boot)

This is a simple RESTful Spring Boot application that retrieves current weather data for an Indian pincode on a specific date. It uses the OpenWeatherMap API to fetch live weather conditions and stores results in a relational database to optimize future queries.

> 📌 Note: Since the current weather API only provides live data, this app assumes the date provided is "today".

---

 🧰 Features

- Accepts `pincode` and `date` via REST API
- Converts `pincode` to latitude/longitude using OpenWeather Geocoding API
- Fetches weather data using OpenWeather Current Weather API
- Stores location and weather info in a relational DB (H2 or other)
- Avoids duplicate API calls by caching results
- Testable via Postman or Swagger
- Includes sample test cases (TDD)

---

 🚀 Setup Instructions

 1. Clone the Repository

```bash
git clone https://github.com/your-username/weather-api.git
cd weather-api
```

 2. Add OpenWeather API Key

Add your key to `src/main/resources/application.properties`:

```properties
openweather.api.key=YOUR_API_KEY
```

Get your key from [OpenWeather](https://openweathermap.org/api).

 3. Run the Application

Use Maven or your IDE:

```bash
./mvnw spring-boot:run
```

App runs at: `http://localhost:8080`

---

 📡 Sample Usage (via Postman or Browser)

 Endpoint:
```
GET /weather?pincode=411014&date=2025-07-11
```

 Request:
```
http://localhost:8080/weather?pincode=411014&date=2025-07-11
```

 Response:

```json
{
  "id": 1,
  "pincode": "411014",
  "date": "2025-07-11",
  "description": "clear sky",
  "temperature": 29.5
}
```

---

 🧪 Run Tests

```bash
./mvnw test
```

Includes test cases to verify service behavior and DB caching logic.

---

 🏗️ Project Structure

```
├── controller/
├── service/
├── model/
├── repository/
├── WeatherApiApplication.java
└── test/
```

---

 📎 License

MIT — Free to use, modify, and distribute.

