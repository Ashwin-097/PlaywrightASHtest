@apitest
Feature: API Token Authentication

  Scenario Outline: Generate token with valid credentials
    Given I have a valid login payload
    When I send a POST request to the auth endpoint
    Then I should receive a token in the response 