Feature: Search film
  @imdb_search
  Scenario: Searching film
    Given user launch browser and open the ımdb main page
    When user search  a film
    Then user should see the film review
