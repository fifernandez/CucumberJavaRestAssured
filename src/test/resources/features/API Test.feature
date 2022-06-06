Feature: Rest API functionality Scenarios
  As a developer
  I want to be sure
  That the Rest API is working as expected


  @1 @2
  Scenario Outline: Verify status code returned is expected
    Given get call to "<url>"
    Then the returned status code is: "<ExpectedCode>"

    Examples:
      | url    | ExpectedCode |
      | /users | 200          |
      | /todos | 200          |
      | /todos | 202          |


  @2 @3
  Scenario Outline: Verify amount of returned items is expected
    Given get call to "<url>"
    Then the returned status code is: "200"
    And the response contains "<amount>" items

    Examples:
      | url    | amount |
      | /users | 10     |
      | /users | 18     |
