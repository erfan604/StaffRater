I used @SpringBootTest for all my tests so the whole app runs during testing, including the database. I also used @AutoConfigureMockMvc so I can test the web routes without needing a browser. (from YouTube Tutorial)

Validation Test
- Checks that a bad email like "not-an-email" gets rejected, this is very simple checks however, something like .commm still passes unfortunately.
- Checks that scores outside 1-10 get rejected (like 0 or 11)
- Checks that an empty name gets rejected
- Checks that a comment over 300 characters gets rejected
- Checks that a valid rating with all correct fields passes

Controller Tests
- Makes sure the home page loads and has the ratings list
- Makes sure the create form page loads
- Makes sure submitting a valid rating redirects back to home
- Makes sure submitting bad data shows the form again with errors

Persistence Tests
- Saves a rating to the database and then finds it by ID to make sure it actually saved
- Deletes a rating and checks that it's actually gone from the database

How to run tests
- run ./mvnw test
