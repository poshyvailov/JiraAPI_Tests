package utills;

import org.json.simple.JSONObject;

public class JiraCommentJsonObject {

    public static String commentJson(){
        JSONObject commentJson = new JSONObject();

        JSONObject update = new JSONObject();
        JSONObject comment = new JSONObject();
        JSONObject add = new JSONObject();

        commentJson.put("update", update);
        update.put("comment", comment);
        comment.put("add", add);
        add.put("body", "Test comment from OP");


        return commentJson.toJSONString();
    }
}
