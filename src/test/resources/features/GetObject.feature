@dev
Feature: Get object

  Scenario: Verify a created object can be retrieved by the id
    Given a request to add an item is made
    Then the item is created
    And a 200 response code is returned from the POST endpoint
    When a request to retrieve the item is made
    Then a 200 response code is returned from the GET endpoint


  Scenario Outline: Verify an object can be retrieved by id
    When a request is made to add item "<item_name>" with CPU model as "<cpu_model>" and of price <price>
    Then a 200 response code is returned from the POST endpoint
    And item "<item_name>" with CPU model as "<cpu_model>" and of price <price> is created
    When a request to retrieve the item is made
    Then a 200 response code is returned from the GET endpoint
    And the details "<item_name>", CPU model "<cpu_model>" and price <price> match
    Examples:
      | item_name            | cpu_model     | price   |
      | Apple MacBook Pro 16 | Intel Core i9 | 1849.99 |

  # The last test step in this scenario will fail due to typo and formatting issues in the actual JSON response
  Scenario: Verify the error message for an invalid id retrieval
    When a request to retrieve an invalid item is made
    Then a 404 response code is returned from the GET endpoint
    And an error message "Object with id # was not found." is returned from the GET endpoint
