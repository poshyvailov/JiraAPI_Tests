package utills;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class JiraApiSteps {

    public static String newIssue = TestJiraJsonObject.newIssueJson();


    public static Response createIssue (){
        Response response =
                given().
                        auth().preemptive().basic("webinar5", "webinar5").
                        contentType(ContentType.JSON).
                        body(newIssue).
                        when().
                        post("https://jira.hillel.it/rest/api/2/issue").
                        then().
                        contentType(ContentType.JSON).
                        statusCode(201).
                        extract().response();
        return  response;
    }

    public static Response getCreatedIssue (String ticketId){
        Response response =
                given().
                        auth().preemptive().basic("webinar5", "webinar5").
                        contentType(ContentType.JSON).
                        when().
                        get("https://jira.hillel.it/rest/api/2/issue/" + ticketId).
                        then().
                        contentType(ContentType.JSON).
                        statusCode(200).
                        extract().response();
        return response;
    }

    public static Response deleteIssue(String ticketId){
        Response response =
                given().
                        auth().preemptive().basic("webinar5", "webinar5").
                        contentType(ContentType.JSON).
                        when().
                        delete("https://jira.hillel.it/rest/api/2/issue/" + ticketId).
                        then().
                        statusCode(204).
                        extract().response();
        return response;
    }

    public static Response checkIfIssueDeleted(String ticketId){
        Response response =
                given().
                        auth().preemptive().basic("webinar5", "webinar5").
                        contentType(ContentType.JSON).
                        when().
                        get("https://jira.hillel.it/rest/api/2/issue/" + ticketId).
                        then().
                        statusCode(404).
                        extract().response();
        return response;
    }



}
