import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GetExistingTestIssue {
    @Test
    public void getExistingIssue() {

        Response response =
                given().
                        auth().preemptive().basic("webinar5", "webinar5").
                        contentType(ContentType.JSON).
                        when().
                        get("http://jira.hillel.it/rest/api/2/issue/WEBINAR-9060").
                        then().
                        contentType(ContentType.JSON).
                        extract().response();

        assertEquals(response.statusCode(), 200);
        assertEquals("WEBINAR-9060", response.path("key"));
        response.print();
    }
}
