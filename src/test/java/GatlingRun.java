import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class GatlingRun extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("https://example.com");

    ScenarioBuilder scn = scenario("Java DSL Simulation").exec(http("Request_1").get("/"));

    {
        setUp(scn.injectOpen(atOnceUsers(19))).protocols(httpProtocol);
    }
}


