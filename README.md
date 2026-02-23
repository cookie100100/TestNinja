Automation Framework — TutorialNinja
Overview

This project implements an end-to-end UI automation framework for Ninja demo site using Java, Selenium, Cucumber, and Gradle.
The framework validates user registration and login workflows including field-level validations and successful account creation.

Tech Stack

    Java 24
    
    Selenium WebDriver
    
    Cucumber (BDD)
    
    Gradle
    
    JUnit 4

How to run

    ./gradlew clean test

Test Coverage
Registration
  
    Successful account creation
    
    Field-level validation:
    
    First name length
    
    Last name length
    
    Email format
    
    Email already registered
    
    Telephone length
    
    Password length
    
    Password confirmation match
    
    Privacy policy requirement

Login

    Successful login with valid credentials
    
    Login failure with invalid credentials
