Feature: Registration
    Scenario: Successful Registration
        Given I navigate to the registration page
        When I register a fresh account with valid data
        Then the user expect the registration to "success"

    Scenario: Registration validations show correct errors
        Given I navigate to the registration page
        Then all registration validations should show correct error messages