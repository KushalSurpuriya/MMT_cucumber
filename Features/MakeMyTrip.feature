Feature: MakeMyTrip

  @Sanity
  Scenario: OutStation Cab booking
    Given The User is on the MakeMyTrip homepage
    When User clicks on cab
    And User enters from and to locations
    And User enters date and time and click on search
    And User selects car type as SUV
    Then User prints the lowest car price

  Scenario: Gift card select
    When User clicks on more tab and selects Giftcard option
    And User selects wedding gift card and selects Email option
    And Scrolll to that field and enter invalid email
    And click on buy now and capture the error message
    Then User prints the error message

  Scenario: Hotel scenario
    When User clicks on Hotels tab
    And User clicks on guest button and then on adults
    Then get the list of number of adults and print it
