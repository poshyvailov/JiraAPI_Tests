# JiraAPI_Tests
The aim of this project is to test basic functions of Jira (Create ticket, detele ticket, add comment, delete comment) API for test Hillel Jira --- [Test Jira URL](https://jira.hillel.it/secure/Dashboard.jspa)
<hr></hr>

This project uses rest assured library for Java 
<hr></hr>
The project consists of:
<br>
1. Interface "APIPathes" - contains basic URL's for tests
<br>
2. Interface "Credentials"
<br>
3. "JiraCommentJsonObject" - class with Json object for "add comment" test
<br>
4. "TestJiraJsonObject" - class with Json object for "create issue" test
<br>
5. "JiraApiSteps" - class with steps for Jira API tests
<br>
6. "JiraApiTests" - class with tests for Jira API
<hr></hr>
How to import and run project:
<br>
( Example for IntelliJ IDEA )
<br>
<br>
1. Open your IDE 
<br>
2. From the main menu, select VCS | Get from Version Control, or, if no project is currently opened, click Get from Version Control on the Welcome screen.
<br>
3. In the Get from Version Control dialog, specify the URL of the remote repository you want to clone
<br>
4. Click Clone.
<br>
5. Then open "JiraApiTests" class in IDE
<br>
6. And run required tests by clicking on the @Test annotations by right button of the mouse and selecting "Run" option
<hr></hr>

As the result of running tests from the "JiraApiTests" class should be two successfully passed tests
<br>
<a href="https://freeimage.host/"><img src="https://iili.io/216dl4.png" alt="216dl4.png" border="0"></a>
