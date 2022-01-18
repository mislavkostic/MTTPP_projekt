# MTTPP projekt

## About
This project tests **accessibility** and **UI features** of  [OpenCart](https://demo.opencart.com/) trough 5 test cases.

###Setup

```
This project was built and tested using Google Chrome as the chosen Web browser. 
If anyone wants to use this test suite, it's expected of the user to use 
Google Chrome and it's respected tools.
```
- Download [ChromeDriver](https://chromedriver.chromium.org/) for your current Google Chrome version
- Import the [repository](https://github.com/mislavkostic/MTTPP_projekt.git) to your preferred IDE
- sync and update missing dependencies
- extract "<u>*chromedriver.exe*</u>" to the root directory where you imported the project
  - ***e.g. (C:\Users\USER\IdeaProjects\MTTPP_projekt)*** for Windows
- run [testing.xml](https://github.com/mislavkostic/MTTPP_projekt/blob/master/testing.xml) to start the test suite
  - In case of unexpected errors during runtime, read the error report given by [Selenium](https://www.selenium.dev/)


## Built With 🛠
- [IntelliJ](https://www.jetbrains.com/idea/) - IDE used to develop this project
- [Java](https://www.java.com/) - programming language for writing test cases
- [Maven](https://maven.apache.org/) - dependency building automation tool
- [Selenium WebDriver](https://www.selenium.dev/) - automated testing framework for web browsers 
  - *(in this case, **[ChromeDriver](https://chromedriver.chromium.org/)** was used)*
- [REST Assured](https://rest-assured.io/) - group of *Java* libraries which enable automation of Rest API testing

##Project structure

Source code contains comments to further help with understanding how the test suite works

###Classes

- [SeleniumTests](https://github.com/mislavkostic/MTTPP_projekt/blob/master/src/main/java/SeleniumTests.java)
  - the main source file
  - all test cases are inside this file

- [EmailGenerator](https://github.com/mislavkostic/MTTPP_projekt/blob/master/src/main/java/EmailGenerator.java)
    - helper class that generates email addresses

- [Person](https://github.com/mislavkostic/MTTPP_projekt/blob/master/src/main/java/Persona.java)
  - helper data class that stores newly created account credentials for each test run
  - it uses [EmailGenerator](https://github.com/mislavkostic/MTTPP_projekt/blob/master/src/main/java/EmailGenerator.java) to generate new email addresses for each test run
  

###Test cases
Before each test case, method [setupTests()](https://github.com/mislavkostic/MTTPP_projekt/blob/c20cba0dbb06a457a8ae9a2224b192f5c54ef26d/src/main/java/SeleniumTests.java#L16) is run, setting up [ChromeDriver](https://chromedriver.chromium.org/) in which the tests are run

- [googleResultTest()](https://github.com/mislavkostic/MTTPP_projekt/blob/c20cba0dbb06a457a8ae9a2224b192f5c54ef26d/src/main/java/SeleniumTests.java#L26)
  - tests if Google search returns a link to the [OpenCart](https://demo.opencart.com/)

- [homepageTest()](https://github.com/mislavkostic/MTTPP_projekt/blob/c20cba0dbb06a457a8ae9a2224b192f5c54ef26d/src/main/java/SeleniumTests.java#L38)
  - **WebDriver** navigates to [OpenCart's homepage](https://demo.opencart.com/) and tests if the page loaded

- [registerTest()](https://github.com/mislavkostic/MTTPP_projekt/blob/c20cba0dbb06a457a8ae9a2224b192f5c54ef26d/src/main/java/SeleniumTests.java#L49)
  - tests register function using randomized credentials
  - credentials are being [randomized](https://github.com/mislavkostic/MTTPP_projekt/blob/c20cba0dbb06a457a8ae9a2224b192f5c54ef26d/src/main/java/EmailGenerator.java#L16) for each test run
  - each test run stores newly created credentials in a [data class](https://github.com/mislavkostic/MTTPP_projekt/blob/master/src/main/java/Persona.java) for further use in other test cases
  - if **WebDriver** successfully logs out of the newly created account, test passes, otherwise it fails <u>(*logging out is impossible if the  account creation failed*)</u>

- [loginTest()](https://github.com/mislavkostic/MTTPP_projekt/blob/c20cba0dbb06a457a8ae9a2224b192f5c54ef26d/src/main/java/SeleniumTests.java#L73)
  - **WebDriver** navigates to **Login** button and enters stored credentials from [data class](https://github.com/mislavkostic/MTTPP_projekt/blob/master/src/main/java/Persona.java) and tests whether it logged in or not

- [addToCartTest()](https://github.com/mislavkostic/MTTPP_projekt/blob/c20cba0dbb06a457a8ae9a2224b192f5c54ef26d/src/main/java/SeleniumTests.java#L91)
  - **WebDriver** navigates through the shop menu and tries to add *iMac* to **cart**
  - if added item is inside the cart, the test passes.

After each test case, method [teardownTest()](https://github.com/mislavkostic/MTTPP_projekt/blob/c20cba0dbb06a457a8ae9a2224b192f5c54ef26d/src/main/java/SeleniumTests.java#L114) is run, shutting down [ChromeDriver](https://chromedriver.chromium.org/)

##License
```
Copyright © 2022 Mislav Kostić
```