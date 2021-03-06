Feature: Cart Item Validation.

  Scenario Outline: Remove lowest price item from cart and Validate cart items
    Given I add <noOfItem> random items to my cart
    When I view my cart
    Then I found total <noOfItem> items in my cart
    Then I serach for lowest price item
    And I am able to remove the lowest price item from my cart
    Then I am able to verify <remainigItem> items in my cart

    Examples: 
      | noOfItem | remainigItem |
      |        4 |            3 |
