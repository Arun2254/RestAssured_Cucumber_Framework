Feature: Check status code and verifying Response

  @Scenario1
  Scenario: Simply checking status code
    Given the apis are up and running for "http://services.groupkt.com/country/get/iso2code/cn"
    When a user performs a get request to "http://services.groupkt.com/country/get/iso2code/in"
    And and perform the request
    Then the response code should be 200
    #And I should see json response with pairs on the filtered "result" node
    #  |name       |India|
    #  |alpha2_code|IN   |
    #  |alpha3_code|IND  |




