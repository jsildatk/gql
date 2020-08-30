Feature: Parsing with not in operator (NOT IN)

  Background:
    Given Data
      | title | publisher | developer | genre | year |
      | Test  | Test      | Test      | Test  | 2010 |
      | Test1 | Test      | Test 1    | Test  | 2011 |
      | Test2 | Test23    | Test      | Test  | 2013 |
      | Test3 | Test24    | Test 3    | Test1 | 2013 |

  Scenario: Parsing with developer field
    Given I am looking for a "developer" field with "NOT IN" operator and "(Test, Test 1)" value
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | Test3 | Test24    | Test 3    | Test1 | 2013 |

  Scenario: Parsing with year field
    Given I am looking for a "year" field with "NOT IN" operator and "(2010, 2013)" value
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | Test1 | Test      | Test 1    | Test  | 2011 |
