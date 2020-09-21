package utills;

import java.security.PublicKey;

public interface APIPathes {
    String baseUrl = "https://jira.hillel.it/";
    String issue = baseUrl + "rest/api/2/issue/";
    String testIssueId = issue + "WEBINAR-12623";
    String comment = "%s/comment/";

}
