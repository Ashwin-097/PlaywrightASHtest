package steps;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class LoginSteps {
    Playwright playwright; // Variable playwright type of Playwright object
    Browser browser; // Represents a specific browser instance (e.g.Chromium)
    Page page; // Represents a single tab or page within the browser.

    @Before //Hook - runs before each scenario
    public void setUp(Scenario scenario) {
        playwright = Playwright.create(); //Initializes Playwright engine
        String featurePath = scenario.getUri().toString();
        System.out.println("Running feature: " + featurePath);
        if (featurePath.contains("api.feature")) {
            // API feature → skip browser launch
            System.out.println("Skipping browser launch for API feature");
            return;
        } else {
            // UI feature → launch browser
            browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false) // set true if you want headless
            );
            page = browser.newPage();
        }
        
        
        
        page = browser.newPage(); //opens a new Browser Tab   
    }

    
    
    
    
    
    
    
    @Given("I open the login page")
    public void openLoginPage() {
        page.navigate("https://www.saucedemo.com/"); //Navigates to Log In page
    }

    @When("I enter username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        page.fill("#user-name", username); //Fills in username
        page.fill("#password", password); //Fills password
    }

    @When("I click the login button")
    public void clickLogin() {
        page.click("#login-button"); //Clicks login button
    }

    @Then("I should see the products page")
    public void verifyProductsPage() {
        assertTrue(page.isVisible(".inventory_list")); //Checks of inventory list is visible
    }

    @After
    public void tearDown(Scenario scenario) {
        String featurePath = scenario.getUri().toString();

        if (!featurePath.contains("api.feature")) {
            // Close browser only if it was opened
            browser.close();
        }
        playwright.close();
    }
}