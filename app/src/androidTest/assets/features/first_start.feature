Feature: First start
  Load app for first time, and feed data base with json file information

  @test1
  Scenario: Load app, retrieve json file information , and feedback data base
    Given the splash view is shown
    When all data is prepared
    Then the main activity is launched

  @test2
  Scenario: Load app, show up MainActivity and start the game
    Given app is launched
    When a shown key word is shown
    And right translation word is shown
    And user press right button
    Then score label shows current score
