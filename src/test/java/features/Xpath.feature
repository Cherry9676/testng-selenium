Feature: Validating Dynamic Controls on the User Interface for Functional Accuracy
#Regression Type
#Correct Values = true
#Incorrect Values = false
#Illegal Values = false
#Invalid Values = false
#Boundary Values = false
#Edge Cases Values = false

@Test_Scenario_1
@uida1339186252
@set21
@test001
Scenario Outline: Verifying the Handling of Dynamic Controls for Interactive Elements
Given I have access to application
When I checked Checkbox1 in the internet
And I wait in seconds wait in the internet as '2'
Then verify checked Checkbox1 in the internet
When I unchecked Checkbox1 in the internet
And I wait in seconds wait in the internet as '2'
Then verify unchecked Checkbox1 in the internet
And verify text Add_Remove in the internet
And verify displayed Add_Remove in the internet
When I wait in seconds wait in the internet as '2'
Then verify disabled textbox in the internet
And verify if visible Enable_disable in the internet
When I clicked Enable_disable in the internet
And I wait in seconds wait in the internet as '<wait>'
Then verify enabled textbox in the internet
And '<page>' is displayed with '<content>'

Examples:
|SlNo.|wait|page|content|
|1|wait1|The Internet|NA|

#Total No. of Test Cases : 1

@Test_Scenario_2
@uida1339186252
@set21
@test002
Scenario Outline: Testing the Behavior of Dynamic UI Controls on a Web Page
Given I have access to application
When I checked Checkbox1 in the internet
Then verify checked Checkbox1 in the internet
When I unchecked Checkbox1 in the internet
Then verify unchecked Checkbox1 in the internet
And verify text Add_Remove in the internet
And verify displayed Add_Remove in the internet
And verify disabled textbox in the internet
And verify if visible Enable_disable in the internet
When I clicked Enable_disable in the internet
And I wait in seconds wait in the internet as '<wait>'
Then verify enabled textbox in the internet
And '<page>' is displayed with '<content>'

Examples:
|SlNo.|wait|page|content|
|1|wait1|The Internet|NA|

#Total No. of Test Cases : 2

@Test_Scenario_3
@uida1339186252
@set21
@test003
Scenario Outline: Validating the Functional Operations of Dynamic Elements in Real-Time
Given I have access to application
When I checked Checkbox1 in the internet
Then verify checked Checkbox1 in the internet
When I unchecked Checkbox1 in the internet
Then verify unchecked Checkbox1 in the internet
And verify text Add_Remove in the internet
And verify displayed Add_Remove in the internet
And verify disabled textbox in the internet
And verify if visible Enable_disable in the internet
When I clicked Enable_disable in the internet
And I wait in seconds wait in the internet as '<wait>'
Then verify enabled textbox in the internet
And '<page>' is displayed with '<content>'

Examples:
|SlNo.|wait|page|content|
|1|wait1|The Internet|NA|

#Total No. of Test Cases : 3

@Test_Scenario_4
@uida1339186252
@set21
@test004
Scenario Outline: Ensuring Proper Functionality of Dynamic Components under Various Conditions
Given I have access to application
When I checked Checkbox1 in the internet
Then verify checked Checkbox1 in the internet
When I unchecked Checkbox1 in the internet
Then verify unchecked Checkbox1 in the internet
And verify text Add_Remove in the internet
And verify displayed Add_Remove in the internet
And verify disabled textbox in the internet
And verify if visible Enable_disable in the internet
When I clicked Enable_disable in the internet
And I wait in seconds wait in the internet as '<wait>'
Then verify enabled textbox in the internet
And '<page>' is displayed with '<content>'

Examples:
|SlNo.|wait|page|content|
|1|wait1|The Internet|NA|

#Total No. of Test Cases : 4


