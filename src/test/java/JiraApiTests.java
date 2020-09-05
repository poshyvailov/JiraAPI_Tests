import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class JiraApiTests {
    private String ticketId;

    //Пробный тест на получение тикета
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

//Создаем новый тикет
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
                          ticketId = response.path("id");
                          System.out.println(ticketId);

        //Get created issue
        //Получаем созданный тикет и проверяем его contentType, статус код, проверяем саммари и проверчем автора
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
                  assertEquals(response2.path("fields.summary"), "Test OP ticket");
                  assertEquals(response2.path("fields.creator.name"), "webinar5");

        //Delete created issue
        //Удаляем созданный тикет и проверяем,что нам вернулся 204 статус код
        Response deleteIssueResponse =
                        given().
                          auth().preemptive().basic("webinar5", "webinar5").
                          contentType(ContentType.JSON).
                        when().
                          delete("https://jira.hillel.it/rest/api/2/issue/" + ticketId).
                        then().
                          statusCode(204).
                          extract().response();
                          deleteIssueResponse.print();

        //Get deleted issue
        //Запрошиваем наш тестовый, удаленный тикет и проверяем, что нам вернулся 404 статус код
        Response checkIfIssueDeletedResponse =
                        given().
                          auth().preemptive().basic("webinar5", "webinar5").
                          contentType(ContentType.JSON).
                        when().
                          get("https://jira.hillel.it/rest/api/2/issue/" + ticketId).
                        then().
                          statusCode(404).
                          extract().response();
      }
    }





// RegEx how to extract ticket number
//        final Matcher<String> matcher = new MatchesPattern(Pattern.compile("[A-Z]+\\-[0-9]+"));
//        assertTrue(matcher.matches("WEBINAR-9060"));