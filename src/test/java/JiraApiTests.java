import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utills.JiraApiSteps;
import utills.JiraCommentJsonObject;
import utills.TestJiraJsonObject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class JiraApiTests {

    private String ticketId;
    String issueName;

    @Test
    public void createIssue() {

        //Создаем новый тикет
        Response createNewIssueResponse = JiraApiSteps.createIssue();
        ticketId = createNewIssueResponse.path("id");
        issueName = createNewIssueResponse.path("key");  //Вытягиваем имя, созданного, тикета в переменную
        assertTrue(issueName.contains("WEBINAR-"));  // Проверяем,что имя тикета содержить слово ""WEBINAR-

        //Получаем созданный тикет
        Response getCreatedIssueResponse = JiraApiSteps.getCreatedIssue(ticketId);
        getCreatedIssueResponse.print();
        assertEquals(getCreatedIssueResponse.path("fields.summary"), "Test OP ticket");
        assertEquals(getCreatedIssueResponse.path("fields.creator.name"), "webinar5");

        //Удаляем созданный тикет
        Response deleteIssueResponse = JiraApiSteps.deleteIssue(ticketId);
        deleteIssueResponse.print();

        //Пытаемся получить удаленный тикет
        Response checkIfIssueDeletedResponse = JiraApiSteps.checkIfIssueDeleted(ticketId);
    }


    @Test
    public void addAndRemoveCommentTestApiSteps(){
        Response checkIfTicketDoesntContainCommentsResponse = JiraApiSteps.checkIfTicketDoesntContainComments();

        Response addNewCommentToIssueResponse = JiraApiSteps.addNewCommentToIssue();

        Response getIssueWithCommentResponse = JiraApiSteps.getIssueWithComment();

        Response removeCommentFromTestTicketResponse = JiraApiSteps.removeCommentFromTestTicket();

        Response checkIfCommentDeletedResponse = JiraApiSteps.checkIfCommentDeleted();
        JiraApiSteps.checkIfCommentDeleted().
                then().
                and().time(lessThan(1000L)).
                body(JiraApiSteps.commentId, equalTo(null)).
                body("comments", equalTo(null));
    }








//    @Test
//    public void addAndRemoveCommentTestApiSteps() {
//        String testIssueId = "WEBINAR-12623";
//        String commentId;
//        String commentJson = JiraCommentJsonObject.commentJson();


        //Получаем тестовый тикет и проверяем,что он не содержит ни одного коммента
//        Response checkIfTicketDoesntContainCommentsResponse = JiraApiSteps.checkIfTicketDoesntContainComments();

//                given().
//                        auth().preemptive().basic("webinar5", "webinar5").
//                        contentType(ContentType.JSON).
//                        when().
//                        get("http://jira.hillel.it/rest/api/2/issue/" + testIssueId).
//                        then().
//                        contentType(ContentType.JSON).
//                        statusCode(200).
//                        body("comments", equalTo(null)).
//                        extract().response();

        //Добавляем новый комментарий для тестового тикета и проверяем респонс статус код
//        Response addNewCommentToIssueResponse = JiraApiSteps.addNewCommentToIssue();

//                given().
//                        auth().preemptive().basic("webinar5", "webinar5").
//                        contentType(ContentType.JSON).
//                        body("{\n" +
//                                "   \"update\": {\n" +
//                                "      \"comment\": [\n" +
//                                "         {\n" +
//                                "            \"add\": {\n" +
//                                "               \"body\": \"Test comment from OP\"\n" +
//                                "            }\n" +
//                                "         }\n" +
//                                "      ]\n" +
//                                "   }\n" +
//                                "}").
//                        when().
//                        put("https://jira.hillel.it/rest/api/2/issue/" + testIssueId).
//                        then().
//                        contentType(ContentType.JSON).
//                        statusCode(204).
//                        extract().response();

        //Получаем наш тикет с тестовым комментом и достаем и сохраняем айдишку нашего коммента
//        Response getIssueWithCommentResponse = JiraApiSteps.getIssueWithComment();

//                given().
//                        auth().preemptive().basic("webinar5", "webinar5").
//                        contentType(ContentType.JSON).
//                        when().
//                        get("http://jira.hillel.it/rest/api/2/issue/" + testIssueId).
//                        then().
//                        contentType(ContentType.JSON).
//                        statusCode(200).
//                        extract().response();
//        getIssueWithCommentResponse.print();
//        commentId = getIssueWithCommentResponse.path("fields.comment.comments[0].id");
//        System.out.println(commentId);

        //Удаляем созданный коммент из тикета и проверяем, какой вернулся статус код
//        Response removeCommentFromTestTicketResponse = JiraApiSteps.removeCommentFromTestTicket();

//                given().
//                        auth().preemptive().basic("webinar5", "webinar5").
//                        contentType(ContentType.JSON).
//                        when().
//                        delete("https://jira.hillel.it/rest/api/2/issue/" + testIssueId + "/comment/" + commentId + "/").
//                        then().
//                        statusCode(204).
//                        extract().response();

        //Запрашиваем наш тестовый т икет и проверяем,что его боди не содержить айдишку нашего коммента
        //Также проверяем,что ответ пришел в течении 1 секунды
//        Response checkIfCommentDeletedResponse = JiraApiSteps.checkIfCommentDeleted();

//                given().
//                        auth().preemptive().basic("webinar5", "webinar5").
//                        contentType(ContentType.JSON).
//                        when().
//                        get("http://jira.hillel.it/rest/api/2/issue/" + testIssueId).
//                        then().
//                        contentType(ContentType.JSON).
//                        statusCode(200).
//                        and().time(lessThan(1000L)).
//                        body(commentId, equalTo(null)).
//                        body("comments", equalTo(null)).
//                        extract().response();
//    }
//
}


//Check response time -                   statusCode(200).and().time(lessThan(10L)).

// RegEx how to extract ticket number
//        final Matcher<String> matcher = new MatchesPattern(Pattern.compile("[A-Z]+\\-[0-9]+"));
//        assertTrue(matcher.matches("WEBINAR-9060"));