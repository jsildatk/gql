Feature: Parsing with between operator (BETWEEN)

  Background:
    Given Data
      | title | publisher | developer | genre | year |
      | Test  | Test      | Test      | Test  | 2010 |
      | Test1 | Test      | Test 1    | Test  | 2011 |
      | Test2 | Test23    | Test      | Test  | 2013 |
      | Test3 | Test24    | Test 3    | Test1 | 2013 |

  Scenario: Parsing with year field and looking for all data
    Given I am looking for a "year" field with "BETWEEN" operator and "2010, 2013" value
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | Test  | Test      | Test      | Test  | 2010 |
      | Test1 | Test      | Test 1    | Test  | 2011 |
      | Test2 | Test23    | Test      | Test  | 2013 |
      | Test3 | Test24    | Test 3    | Test1 | 2013 |

  Scenario: Parsing with year field and looking for data with same range
    Given I am looking for a "year" field with "BETWEEN" operator and "2011, 2011" value
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | Test1 | Test      | Test 1    | Test  | 2011 |

  Scenario: Parsing with year field and looking for not existing range
    Given I am looking for a "year" field with "BETWEEN" operator and "2015, 2018" value
    When I am parsing query
    Then Result is empty