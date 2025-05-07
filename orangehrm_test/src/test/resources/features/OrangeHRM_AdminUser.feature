Feature: OrangeHRM Admin User Management
  As an admin user
  I want to add and delete user accounts
  So that I can manage system users effectively

  Background:
    Given I am on OrangeHRM login page
    When I enter username as "Admin"
    And I enter password as "admin123"
    And I click on login button
    Then I should be logged in successfully

  Scenario: Add and Delete Admin User
    When I click on Admin tab
    Then I should be on Admin page
    And I get the initial number of records
    When I click on Add button
    And I fill the required user data
    And I click on Save button
    Then the number of records should increase by 1
    And I should find the newly added user
    When I delete the newly added user
    Then the number of records should decrease by 1
    