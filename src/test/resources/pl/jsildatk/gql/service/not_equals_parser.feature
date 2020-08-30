Feature: Parsing with not equals operator (!=)

  Background:
    Given Data
      | title | publisher | developer | genre | year |
      | Test  | Test      | Test      | Test  | 2010 |
      | Test1 | Test      | Test 1    | Test  | 2011 |
      | Test2 | Test23    | Test      | Test  | 2013 |


  Scenario: Parsing with publisher field
    Given I am looking for a "publisher" field with "!=" operator and "Test23" value
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | Test  | Test      | Test      | Test  | 2010 |
      | Test1 | Test      | Test 1    | Test  | 2011 |

  Scenario: Parsing with year field
    Given I am looking for a "year" field with "!=" operator and "2010" value
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | Test1 | Test      | Test 1    | Test  | 2011 |
      | Test2 | Test23    | Test      | Test  | 2013 |
