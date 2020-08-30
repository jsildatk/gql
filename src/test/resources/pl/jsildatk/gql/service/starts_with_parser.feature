Feature: Parsing with starts with operator (STARTS WITH)

  Background:
    Given Data
      | title | publisher | developer | genre | year |
      | Test  | Test      | Test      | Test  | 2010 |
      | Test1 | Test      | Test 1    | Test  | 2011 |
      | Test2 | Test23    | Test      | Test  | 2013 |

  Scenario: Parsing with title field
    Given I am looking for a "title" field with "STARTS WITH" operator and "Te" value
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | Test  | Test      | Test      | Test  | 2010 |
      | Test1 | Test      | Test 1    | Test  | 2011 |
      | Test2 | Test23    | Test      | Test  | 2013 |

  Scenario: Parsing with year field
    Given I am looking for a "year" field with "STARTS WITH" operator and "201" value
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | Test  | Test      | Test      | Test  | 2010 |
      | Test1 | Test      | Test 1    | Test  | 2011 |
      | Test2 | Test23    | Test      | Test  | 2013 |

  Scenario: Parsing with developer field with no match
    Given I am looking for a "developer" field with "STARTS WITH" operator and "Asd" value
    When I am parsing query
    Then Result is empty