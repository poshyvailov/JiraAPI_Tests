import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utills.JiraApiSteps;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class JiraApiTests {

    private String ticketId;
    String issueName;

    @Feature(value = "create issue")
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

    @Feature(value = "addComment")
    @Test
    public void addAndRemoveCommentTestApiSteps() {
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
}


//Check response time -                   statusCode(200).and().time(lessThan(10L)).

// RegEx how to extract ticket number
//        final Matcher<String> matcher = new MatchesPattern(Pattern.compile("[A-Z]+\\-[0-9]+"));
//        assertTrue(matcher.matches("WEBINAR-9060"));