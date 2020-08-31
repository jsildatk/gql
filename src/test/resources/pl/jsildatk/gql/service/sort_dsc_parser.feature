Feature: Parsing with descending order (DESCENDING)

  Background:
    Given Data
      | title | publisher | developer | genre | year |
      | A     | Test      | Test      | Test  | 2010 |
      | As    | Test23    | Test      | Test  | 2013 |
      | D     | Test      | Test 1    | Test  | 2011 |
      | Bc    | Test24    | Test 3    | Test1 | 2013 |

  Scenario: Parsing with developer field looking for sorting by title
    Given I am looking for a "developer" field with "NOT IN" operator and "(Asdf)" value and "DESCENDING" sort by "title" field
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | D     | Test      | Test 1    | Test  | 2011 |
      | Bc    | Test24    | Test 3    | Test1 | 2013 |
      | As    | Test23    | Test      | Test  | 2013 |
      | A     | Test      | Test      | Test  | 2010 |


  Scenario: Parsing with publisher field looking for sorting by year
    Given I am looking for a "publisher" field with "NOT IN" operator and "(Asdf)" value and "DESCENDING" sort by "year" field
    When I am parsing query
    Then Result contains
      | title | publisher | developer | genre | year |
      | As    | Test23    | Test      | Test  | 2013 |
      | Bc    | Test24    | Test 3    | Test1 | 2013 |
      | D     | Test      | Test 1    | Test  | 2011 |
      | A     | Test      | Test      | Test  | 2010 |