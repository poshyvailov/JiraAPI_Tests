import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;


public class JiraApiTests {
    private String ticketId;

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


    @Test
    public void createIssue() {
        Response response =
                given().
                        auth().preemptive().basic("webinar5", "webinar5").
                        contentType(ContentType.JSON).
                        body("{\n" +
                                "   \"fields\":{\n" +
                                "      \"summary\":\"Test OP ticket\",\n" +
                                "      \"issuetype\":{\n" +
                                "         \"id\":\"10105\",\n" +
                                "         \"name\":\"test\"\n" +
                                "      },\n" +
                                "      \"project\":{\n" +
                                "         \"id\":\"10508\"\n" +
                                "      },\n" +
                                "   \"reporter\": {\n" +
                                "      \"name\": \"webinar5\"\n" +
                                "    }\n" +
                                "   }\n" +
                                "}").
                        when().
                        post("https://jira.hillel.it/rest/api/2/issue").
                        then().
                        contentType(ContentType.JSON).
                        statusCode(201).
                        extract().response();
        response.print();
        ticketId = response.path("id");
        System.out.println(ticketId);

        //Get created issue
        Response response2 =
               given().
                  auth().preemptive().basic("webinar5", "webinar5").
                  contentType(ContentType.JSON).
                when().
                  get("https://jira.hillel.it/rest/api/2/issue/" + ticketId).
                then().
                  contentType(ContentType.JSON).
                  statusCode(200).
                  extract().response();
                  response2.print();
                  assertEquals("Test OP ticket", response2.path("summary"));
                  assertEquals("webinar5", response2.path("name"));
    }

    @Test
    public void getIssueApiTest() {
        Response response =
                given().
                        auth().preemptive().basic("webinar5", "webinar5").
                        contentType(ContentType.JSON).
                        when().
                        get("https://jira.hillel.it/rest/api/2/issue/78948").
                        then().
                        contentType(ContentType.JSON).
                        statusCode(200).
                        extract().response();
                        response.print();
                        assertEquals("Test OP ticket", response.path("summary"));
                        response.then().assertThat().body("summary", equalTo("Test OP ticket"));





//        assertEquals(response.statusCode(), 200);
//        assertEquals("Main order flow broken", response.path("summary"));
//        assertEquals("webinar5", response.path("name"));

    }


}


// RegEx how to extract ticket number
//        final Matcher<String> matcher = new MatchesPattern(Pattern.compile("[A-Z]+\\-[0-9]+"));
//        assertTrue(matcher.matches("WEBINAR-9060"));