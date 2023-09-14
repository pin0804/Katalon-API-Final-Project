@BookingIds

Feature: Get Top 10 Booking Ids

As a user, I want to get the top 10 booking IDs.



@Valid

Scenario: Get Top 10 Booking Ids

Given I have a valid authentication token

When I send a GET request to the "/https://restful-booker.herokuapp.com/booking" endpoint

Then the response status code should be 200

And the response body should contain a list of the top 10 booking IDs