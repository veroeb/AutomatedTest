Feature: MercadoLibre

  Scenario: Searching for iPhone 14
    Given I access MercadoLibre website
    When I click on the Uruguayan flag
    Then I can see the Uruguayan version of the website
    When I search for "iphone 14" in the search bar
    And I navigate to the next search results page
    Then I can see all search results

  Scenario Outline: Ensure price consistency across the flow
    Given I access MercadoLibre website
    When I click on the Uruguayan flag
    Then I can see the Uruguayan version of the website
    When I search for "<product>" in the search bar
    And I navigate to the next search results page
    Then I can see all search results
    When I click on the first product card
    Then I can verify that the price remains unchanged
    When I click on Comprar Ahora
    Then I can see the login page

    Examples:
      | product       |
      | iphone 14     |
      | playstation 5 |
      | xbox series x |
