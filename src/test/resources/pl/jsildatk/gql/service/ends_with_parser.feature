Feature: Parsing with ends with operator (ENDS WITH)

  Background:
    Given Data
      | title | publisher | developer | genre | year |
      | Test  | Test      | Test      | Test  | 2010 |
      | Test1 | Test      | Test 1    | Test  | 2011 |
      | Test2 | Test23    | Test      | Test  | 2013 |

  Scenario: Parsing with title field
    Given I am looking for a "genre" field with "ENDS WITH" operator and "st" value
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | Test  | Test      | Test      | Test  | 2010 |
      | Test1 | Test      | Test 1    | Test  | 2011 |
      | Test2 | Test23    | Test      | Test  | 2013 |

  Scenario: Parsing with year field
    Given I am looking for a "year" field with "ENDS WITH" operator and "13" value
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | Test2 | Test23    | Test      | Test  | 2013 |

  Scenario: Parsing with developer field with no match
    Given I am looking for a "developer" field with "ENDS WITH" operator and "Asd" value
    When I am parsing query
    Then Result is empty