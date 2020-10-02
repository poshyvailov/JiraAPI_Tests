package utills;

import org.json.simple.JSONObject;



public class TestJiraJsonObject {

    public static String newIssueJson(){
        JSONObject newIssueJSON = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject issueType = new JSONObject();
        JSONObject project = new JSONObject();
        JSONObject reporter = new JSONObject();

        newIssueJSON.put("fields", fields);
        fields.put("summary" , "Test OP ticket");
        fields.put("issuetype", issueType);
        fields.put("project", project);
        fields.put("reporter", reporter);

        issueType.put("id", "10105");
        issueType.put("name", "test");

        project.put("id", "10508");

        reporter.put("name", "webinar5");

        return newIssueJSON.toJSONString();
    }


}


