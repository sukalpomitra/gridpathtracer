Feature: GridPath
  Scenario: Input Parameters
    Given Invalid grid size is passed
    Then we should get an invalid grid size exception message
    Given Invalid number of blocked cells is passed
    Then we should get an invalid number of blocked cells exception message
    Given the blocked cells list passed does not match with the number of blocked cells or is at the first or lkast cell
    Then we should get invalid blocked cells exception message
    Given all inputs are correct
    Then for a two by two grid we get a path
    Given all inputs are correct with two blocked cells
    Then for a two by two grid we get one to four path available
    Given all inputs are correct but with blocked path
    Then for a three by three grid we get no path available