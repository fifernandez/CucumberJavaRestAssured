Feature: Rest API functionality Scenarios
  As a developer
  I want to be sure
  That the Rest API is working as expected


  @TestCaseID:01 @smoke
    @1 @2 @prod
  Scenario Outline: Verify status code returned is expected
    Given I do a get to the "<Endpoint>" endpoint
    Then the returned status code is: "<ExpectedCode>"
    And the schema for the "<Endpoint>" endpoint with "<ExpectedCode>" response code is correct

    Examples:
      | Endpoint | ExpectedCode |
      | users    | 200          |
      | todos    | 200          |


  @TestCaseID:02
    @2 @3 @prod @Flaky
  Scenario Outline: Verify amount of returned items is expected
    Given I do a get to the "<Endpoint>" endpoint
    Then the returned status code is: "200"
    And the response contains "<amount>" items

    Examples:
      | Endpoint | amount |
      | users    | 11     |


  @TestCaseID:03
  @4 @disable @prod
  Scenario: Verify status code returned is expected 2323
    Given I do a get to the "users" endpoint just to test with bad parameters
    Then the returned status code is: "205"