@api-test
Feature: Create a new user for JPetStore via API

  Scenario: As a tester I need to create a new user for JPetStore via API
    When I post to Actions api with valid user details
    #Then user should get created