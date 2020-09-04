## Simple query language written in Spring and MongoDB. <br>
### Fields that are supported: <br>
1) Developer (string)
2) Genre (string)
3) Publisher (string)
4) Title (unique string)
5) Year (integer)
### Operators that are supported: <br>
1) Asterix (*) - looking for all
2) Between (BETWEEN) - looking for values (only integers) in range (inclusive)
3) Ends with (ENDS WITH) - looking for values ends with provided value
4) Equals (=) - looking for exact value
5) Greater (>) - looking for value greater than value (only integers)
6) Greater equals (>=) - looking for value greater or equals value (only integers)
7) In (IN) - looking for exact values in list of values
8) Less (<) - looking for value less than value (only integers)
9) Less equals (<=) - looking for value less or eqauls value (only integers)
10) Not equals (!=) - looking for value that are not provided value
11) Not in (NOT IN) - looking for not provided values in list of values
12) Regex (REGEX) - looking for values that match provided regular expression
13) Starts with (STARTS WITH) - looking for values that starts with provided value
### Sorting by field and order: <br>
1) Ascending
2) Descending
