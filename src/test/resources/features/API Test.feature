Feature: Rest API functionality Scenarios
  As a developer
  I want to be sure
  That the Rest API is working as expected


  @Smoke @1 @2
  Scenario Outline: Verify status code returned is expected
    Given I do a get to the "<Endpoint>" endpoint
    Then the returned status code is: "<ExpectedCode>"

    Examples:
      | Endpoint | ExpectedCode |
      | users    | 200          |
      | todos    | 200          |


  @2 @3
  Scenario Outline: Verify amount of returned items is expected
    Given I do a get to the "<Endpoint>" endpoint
    Then the returned status code is: "200"
    And the response contains "<amount>" items

    Examples:
      | Endpoint | amount |
      | users    | 10     |


  @4 @failing
  Scenario: Verify status code returned is expected 2323
    Given I do a get to the "users" endpoint just to test with bad parameters
    Then the returned status code is: "205"