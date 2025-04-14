//package simulations;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class DynamicJsonPayloadSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://your-api.example.com")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    // CSV Feeder
    FeederBuilder.Batchable<String> feeder = csv("data/users.csv").circular();

    String jsonTemplate = """
    {
        "username": "${username}",
        "password": "${password}"
    }
    """;

    ScenarioBuilder scn = scenario("Dynamic JSON Post")
            .feed(feeder)
            .exec(
                    http("Dynamic Login Request")
                            .post("/api/login")
                            .body(StringBody(jsonTemplate))
                            .check(status().is(200))
            );

    {
        setUp(
                scn.injectOpen(atOnceUsers(3))
        ).protocols(httpProtocol);
    }
}
