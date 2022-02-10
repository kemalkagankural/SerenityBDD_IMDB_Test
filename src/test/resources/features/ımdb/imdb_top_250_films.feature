Feature: IMDB Top 250 Films
  @imdb_films
  Scenario: Get IMDB Top 250 Films
    Given User open IMDB main Page
    And User click on Top Film link
    Then User should see IMDB Top films
    And User get the list of films on CSV file
