# CucumberJavaRestAssured
API Automation using Rest Assured, Java, Cucumber and Gradle.
[![cucumber-test-runner](https://github.com/fifernandez/CucumberJavaRestAssured/actions/workflows/cucumber-test-runner.yml/badge.svg)](https://github.com/fifernandez/CucumberJavaRestAssured/actions/workflows/cucumber-test-runner.yml)

To run the tests:
-
Option1:
- Execute:  ./gradlew test
- Low console output, just the names of the scenarios and the result.

Option 2:
 - Execute: ./gradlew runTests
 - Will display all the scenarios in the console output while running along the errors.

Parameters:
- 
- Env:
  - You can choose which environment you are going to test.
  - Add to command line: -Denv=desired
  - Available: dev, qa, stage, prod
  - Default: qa

- Mode:
  - You can choose if you want to use public urls or private.
  - Add to command line: -Dmode=desired
  - Available: private, public
  - Default: public

- Tags:
  - You can choose which scenarios you want to run.
    - Add to command: -Dcucumber.filter.tags="desired"
    - Remember each scenario starts with @
    - You can use "and", "or" and "not" operators.
    - Example: -Dcucumber.filter.tags="@2 and not @3"
