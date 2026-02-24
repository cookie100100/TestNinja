Feature: Login
    Scenario: login with valid credentials
        Given I navigate to the registration page
        When I register a fresh account with valid data
        And I log out
        And I navigate to the login page
        When I log in with valid credentials
        Then I expect the login to "success"

    Scenario: login with invalid credentials
        Given I navigate to the login page
        When I log in with invalid credentials
        Then I expect the login to "error"

