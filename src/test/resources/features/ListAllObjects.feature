@dev
Feature: List all objects

  Scenario: Verify all items can be retrieved
    When a request to retrieve all items is made
    Then a 200 response code is returned from the list endpoint
    And all items are listed