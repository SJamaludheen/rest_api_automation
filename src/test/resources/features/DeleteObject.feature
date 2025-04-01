@dev

Feature: Delete object

  # The last test step in this scenario will fail due to typo and formatting issues in the actual JSON response
  Scenario: Verify a created item can be deleted
    Given a request to add an item is made
    And a 200 response code is returned from the POST endpoint
    When a request to retrieve the item is made
    Then a 200 response code is returned from the GET endpoint
    When a request to delete the item is made
    Then a 200 response code is returned from the DELETE endpoint
    And a message "Object with id # has been deleted." is returned from the DELETE endpoint
    When a request to retrieve the item is made
    Then a 404 response code is returned from the GET endpoint
    And an error message "Object with id # was not found." is returned from the GET endpoint


  Scenario: Verify the error message for an invalid item deletion
    When a request to delete an invalid item is made
    Then a 404 response code is returned from the DELETE endpoint
    And an error message "Object with id # doesn't exist." is returned from the DELETE endpoint