Feature: login feature


    Scenario: Login with valid username and password
        Given user is on the login page
        When user input valid username "radityastanjung@gmail.com"
        And user input valid password "yourpassword"
        And user click login button
        Then User will redirect to the home page


    Scenario: Login invalid username and password
        Given user is on the login page
        When user input valid username "radittanjung@gmail.com"
        And user input valid password "wrongpassword"
        And user click login button
        Then User will see an error message


    Scenario: Searching for an existing product
        Given user is on the home page
        When user input product name "Macbook Pro"
        And user click search button
        Then User will see the product list

    