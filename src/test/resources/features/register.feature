Feature: Registration
    Scenario: As someone who wants to sign up
        Given the user is on registration page
        And the user enter "Laura" in the register field "firstName"
        And the user enter "Xu" in the register field "lastName"
        And the user enter "longyang.xu12@gmail.com" in the register field "email"
        And the user enter "4126166973" in the register field "telephone"
        And the user enter "123test" in the register field "password"
        And the user enter "123test" in the register field "confirmPassword"
        And the user agree to the terms and conditions
        And the user click the Continue button
        Then the user expect the registration to "success"
