@dev
Feature: Create object

  #test steps without parameterisation (JSON payload in the src/test/resources/requestpayloads folder is used to send request)
  Scenario: Verify an item can be created
    When a request to add an item is made
    Then the item is created
    And a 200 response code is returned from the POST endpoint

  #test steps with parameterisation to send custom values (to override the ones in the JSON payload) in the request
  Scenario Outline: Verify an item can be created
    When a request is made to add item "<item_name>" with CPU model as "<cpu_model>" and of price <price>
    Then a 200 response code is returned from the POST endpoint
    And item "<item_name>" with CPU model as "<cpu_model>" and of price <price> is created
    Examples:
      | item_name            | cpu_model     | price   |
      | Apple MacBook Pro 16 | Intel Core i9 | 1849.99 |


  Scenario Outline: Verify an item can be created by setting no values for some of the fields
    When a request is made to add item "<item_name>" with CPU model as "<cpu_model>" and of price <price>
    Then a 200 response code is returned from the POST endpoint
    And item "<item_name>" with CPU model as "<cpu_model>" and of price <price> is created
    Examples:
      | item_name       | cpu_model     | price   |
      |                 | Intel Core i9 | 1849.99 |
      | Apple iPhone 11 |               | 389.99  |


  Scenario: Verify the same item can be added twice
    Given a request to add an item is made
    Then the item is created
    And a 200 response code is returned from the POST endpoint
    When a request to add an item is made
    Then the item is created
    And a 200 response code is returned from the POST endpoint