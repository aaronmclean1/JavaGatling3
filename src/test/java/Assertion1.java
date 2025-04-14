import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Assertion1 extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("https://example.com");

    ScenarioBuilder scn = scenario("Assertion1").exec(http("Request_1").get("/"));

    {
        setUp(scn.injectOpen(atOnceUsers(29)))
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().mean().lt(1000),
                        global().successfulRequests().percent().gt(99.0)
                );
    }
}


