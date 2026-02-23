Feature: Registration
    Scenario: Registration validation correctly
        Given the user is on registration page
        And the user enter "adminfirstname" in the register field "firstName"
        And the user enter "adminlastname" in the register field "lastName"
        And the user enter "AUTO_EMAIL" in the register field "email"
        And the user enter "123456" in the register field "telephone"
        And the user enter "123test" in the register field "password"
        And the user enter "123test" in the register field "confirmPassword"
        And the user agree to the terms and conditions
        And the user click the Continue button
        Then the user expect the registration to "success"

    Scenario: Registration validations show correct errors
        Given the user is on registration page
        Then all registration validations should show correct error messages