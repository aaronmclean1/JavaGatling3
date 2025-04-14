//package java;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class GroupTest extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://yourapi.example.com")  // 🔁 Replace with your real API base URL
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    // Define the scenario with grouped HTTP calls
    ScenarioBuilder scn = scenario("Grouped Test")
            .group("Login API").on(
                    exec(
                            http("Login")
                                    .post("/login")
                                    .body(StringBody("{ \"username\": \"test\", \"password\": \"test\" }"))
                                    .check(status().is(200))
                    )
            )
            .group("User Data").on(
                    exec(
                            http("Get User")
                                    .get("/user")
                                    .check(status().is(200))
                    )
            );

    {
        // Configure simulation injection and assertions
        setUp(
                scn.injectOpen(
                        nothingFor(Duration.ofSeconds(2)),
                        atOnceUsers(50) // spike of 50 users
                )
        )
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().mean().lt(500),
                        global().successfulRequests().percent().gt(95.0),
                        details("Login").responseTime().mean().lt(400),
                        details("Get User").responseTime().mean().lt(400)
                );
    }
}
