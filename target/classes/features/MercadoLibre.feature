Feature: MercadoLibre

  Scenario: Verify that the price is the same and there's at least one iPhone available
    Given I access MercadoLibre webpage
    When I click on the Uruguay flag
    Then I can see the Uruguayan website displayed
    When I search for "iPhone 14" on the search bar
    And I click on the next search results page
    Then I can see all results
    When I click on the second card
    Then I can verify the price is the same
    And I can verify that there's at least one item available
    When I click on Comprar Ahora
    Then I can see the login page
