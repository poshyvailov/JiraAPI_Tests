package utills;

import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class JiraApiSteps {

    public static String newIssue = TestJiraJsonObject.newIssueJson();
    public static String commentJsonObj = JiraCommentJsonObject.commentJson();
    public static String commentId;


    @Feature(value = "asdfsdf")
    public static Response createIssue() {
        Response response =
                given().
                        auth().preemptive().basic(Credentials.username, Credentials.password).
                        contentType(ContentType.JSON).
                        body(newIssue).
                        when().
                        post(APIPathes.issue).
                        then().
                        contentType(ContentType.JSON).
                        statusCode(201).
                        extract().response();
        return response;
    }

    public static Response getCreatedIssue(String ticketId) {
        Response response =
                given().
                        auth().preemptive().basic(Credentials.username, Credentials.password).
                        contentType(ContentType.JSON).
                        when().
                        get(APIPathes.issue + ticketId).
                        then().
                        contentType(ContentType.JSON).
                        statusCode(200).
                        extract().response();
        return response;
    }

    public static Response deleteIssue(String ticketId) {
        Response response =
                given().
                        auth().preemptive().basic(Credentials.username, Credentials.password).
                        contentType(ContentType.JSON).
                        when().
                        delete(APIPathes.issue + ticketId).
                        then().
                        statusCode(204).
                        extract().response();
        return response;
    }

    public static Response checkIfIssueDeleted(String ticketId) {
        Response response =
                given().
                        auth().preemptive().basic(Credentials.username, Credentials.password).
                        contentType(ContentType.JSON).
                        when().
                        get(APIPathes.issue + ticketId).
                        then().
                        statusCode(404).
                        extract().response();
        return response;
    }





//Add/check/remove comment steps
    public static Response checkIfTicketDoesntContainComments() {
        Response response =
                given().
                        auth().preemptive().basic(Credentials.username, Credentials.password).
                        contentType(ContentType.JSON).
                        when().
                        get(APIPathes.testIssueId).
                        then().
                        contentType(ContentType.JSON).
                        statusCode(200).
                        body("comments", equalTo(null)).
                        extract().response();
        return response;
    }

    public static Response addNewCommentToIssue(){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.username, Credentials.password).
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
                        put(APIPathes.testIssueId).
                        then().
                        contentType(ContentType.JSON).
                        statusCode(204).
                        extract().response();
        return response;
    }

    public static Response getIssueWithComment(){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.username, Credentials.password).
                        contentType(ContentType.JSON).
                        when().
                        get(APIPathes.testIssueId).
                        then().
                        contentType(ContentType.JSON).
                        statusCode(200).
                        extract().response();
        commentId = response.path("fields.comment.comments[0].id");
//      commentId = getIssueWithComment.path("fields.comment.comments[0].id");
        System.out.println(commentId);
        return response;
    }

    public static Response removeCommentFromTestTicket(){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.username, Credentials.password).
                        contentType(ContentType.JSON).
                        when().
                        // Тут получилось ооочень натянутое применение стринг формат.Но я не нашел места, в моем тесте,
                        // где его стоило бы вообще применять (включительно это место). Раньше было лучше :)
                        delete(String.format(APIPathes.comment, APIPathes.testIssueId) + commentId + "/").
//                      delete(APIPathes.testIssueId + "/comment/" + commentId + "/").
                        then().
                        statusCode(204).
                        extract().response();
        return response;
    }

    public static Response checkIfCommentDeleted(){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.username, Credentials.password).
                        contentType(ContentType.JSON).
                        when().
                        get(APIPathes.testIssueId).
                        then().
                        contentType(ContentType.JSON).
                        statusCode(200).
                        extract().response();
        return response;
    }
}
