Feature: Login ımdb
  @imdb_login
  Scenario: Login imdb
    Given user launch browser and open the main page
    When user logged in IMDB
    Then user logged in successfully
