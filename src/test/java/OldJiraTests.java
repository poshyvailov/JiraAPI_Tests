/*
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utills.JiraApiSteps;
import utills.TestJiraJsonObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;

public class OldJiraTests {

    import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utills.JiraApiSteps;
import utills.TestJiraJsonObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;


    public class JiraApiTests {

        private String ticketId;
        String newIssue = TestJiraJsonObject.newIssueJson();

        @Test
        public void createIssue() {

            Response createNewIssueResponse = JiraApiSteps.createIssue(newIssue);
            ticketId = createNewIssueResponse.path("id");
            System.out.println(ticketId);

            //Get created issue
            //Получаем созданный тикет и проверяем его contentType, статус код, проверяем саммари и проверяем автора
            Response getCreatedIssueResponse = JiraApiSteps.getCreatedIssue(ticketId);
                given().
                        auth().preemptive().basic("webinar5", "webinar5").
                        contentType(ContentType.JSON).
                        when().
                        get("https://jira.hillel.it/rest/api/2/issue/" + ticketId).
                        then().
                        contentType(ContentType.JSON).
                        statusCode(200).
                        extract().response();
            getCreatedIssueResponse.print();
            assertEquals(getCreatedIssueResponse.path("fields.summary"), "Test OP ticket");
            assertEquals(getCreatedIssueResponse.path("fields.creator.name"), "webinar5");


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

        @Test
        public void addAndRemoveCommentTestApiSteps() {
            String testIssueId = "WEBINAR-12623";
            String commentId;

            //Получаем тестовый тикет и проверяем,что он не содержит ни одного коммента
            Response checkIfTicketDoesntContainCommentsResponse =
                    given().
                            auth().preemptive().basic("webinar5", "webinar5").
                            contentType(ContentType.JSON).
                            when().
                            get("http://jira.hillel.it/rest/api/2/issue/" + testIssueId).
                            then().
                            contentType(ContentType.JSON).
                            statusCode(200).
                            body("comments", equalTo(null)).
                            extract().response();

            //Добавляем новый комментарий для тестового тикета и проверяем респонс статус код
            Response addNewCommentToIssueResponse =
                    given().
                            auth().preemptive().basic("webinar5", "webinar5").
                            contentType(ContentType.JSON).
                            body("{\n" +
                                    "   \"update\": {\n" +
                                    "      \"comment\": [\n" +
                                    "         {\n" +
                                    "            \"add\": {\n" +
                                    "               \"body\": \"Test comment from OP\"\n" +
                                    "            }\n" +
                                    "         }\n" +
                                    "      ]\n" +
                                    "   }\n" +
                                    "}").
                            when().
                            put("https://jira.hillel.it/rest/api/2/issue/" + testIssueId).
                            then().
                            contentType(ContentType.JSON).
                            statusCode(204).
                            extract().response();

            //Получаем наш тикет с тестовым комментом и достаем и сохраняем айдишку нашего коммента
            Response getIssueWithCommentResponse =
                    given().
                            auth().preemptive().basic("webinar5", "webinar5").
                            contentType(ContentType.JSON).
                            when().
                            get("http://jira.hillel.it/rest/api/2/issue/" + testIssueId).
                            then().
                            contentType(ContentType.JSON).
                            statusCode(200).
                            extract().response();
            getIssueWithCommentResponse.print();
            commentId = getIssueWithCommentResponse.path("fields.comment.comments[0].id");
            System.out.println(commentId);

            //Удаляем созданный коммент из тикета и проверяем, какой вернулся статус код
            Response removeCommentFromTestTicketResponse =
                    given().
                            auth().preemptive().basic("webinar5", "webinar5").
                            contentType(ContentType.JSON).
                            when().
                            delete("https://jira.hillel.it/rest/api/2/issue/" + testIssueId + "/comment/" + commentId + "/").
                            then().
                            statusCode(204).
                            extract().response();

            //Запрашиваем наш тестовый т икет и проверяем,что его боди не содержить айдишку нашего коммента
            //Также проверяем,что ответ пришел в течении 1 секунды
            Response checkIfCommentDeletedResponse =
                    given().
                            auth().preemptive().basic("webinar5", "webinar5").
                            contentType(ContentType.JSON).
                            when().
                            get("http://jira.hillel.it/rest/api/2/issue/" + testIssueId).
                            then().
                            contentType(ContentType.JSON).
                            statusCode(200).
                            and().time(lessThan(1000L)).
                            body(commentId, equalTo(null)).
                            body("comments", equalTo(null)).
                            extract().response();
        }

    }


//Check response time -                   statusCode(200).and().time(lessThan(10L)).

// RegEx how to extract ticket number
//        final Matcher<String> matcher = new MatchesPattern(Pattern.compile("[A-Z]+\\-[0-9]+"));
//        assertTrue(matcher.matches("WEBINAR-9060"));
}
*/
