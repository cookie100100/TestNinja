Feature: Login
      Scenario: login with valid credentials
          Given the user is on login page
          And the user enter "longyang.xu12@gmail.com" in the login field "email"
          And the user enter "123test" in the login field "password"
          And the user click the Login button
          Then the user expect the login to "success"
      Scenario: login with invalid credentials
          Given the user is on login page
          And the user enter "wrong@test.com" in the login field "email"
          And the user enter "wrongpass" in the login field "password"
          And the user click the Login button
          Then the user expect the login to "error"