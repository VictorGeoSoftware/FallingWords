Feature: First start
  Load app for first time, and feed daba base with json file information

  Scenario: Load app, retrieve json file information , and feedback data base
    Given the splash view is shown
    When json file is ready
    And data base is feeded with all json file info
    Then the main activity is launched


