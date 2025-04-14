import io.gatling.core.*;
import static io.gatling.core.CoreDsl.*;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;
import static io.gatling.javaapi.http.HttpDsl.*;

public class GatlingSim extends Simulation {
    HttpProtocolBuilder httpProtocol = http.baseUrl("https://example.com");
    ScenarioBuilder scn = scenario("java sim").exec(http("Request_1").get("/"));
    {
        setUp(scn.injectOpen(atOnceUsers(19))).protocols(httpProtocol);

    }
}
