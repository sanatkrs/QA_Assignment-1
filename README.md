
<h1>QA Assignment-1: API-Tests for the adidas homepage CMS response </h1>

This project is the first task mentioned in the doc. I have used here Serenity Bdd Framework with cucumber. A run configuration will be required to run this project, although we can also run this project using cucumber runner class, however for generating the Serenity BDD report we have to set the run configuration, please find below the detail instruction for setting up the run configuration.

Go to run >> run configuration >> select the project in 'Base Directory'  >> Goals: clean verify serenity:aggregate >> Apply >> Run


Report Location: Adidas-QA-Task1\Adidas-QA-Task1\target\site\serenity\index.html


<Strong>About Task</Strong>:

1. Response time should be below 1s<br>
Test Case Status: Pass
2. Images should be accessible (no 404/401s)<br>
Test Case Status: Pass
3. Every component has at least analyAcs data “analytics_name” in it<br>
Test Case status: Fail : analytics_name is not present in all of the Componenets
