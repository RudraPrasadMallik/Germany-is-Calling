package com.germany_is_calling.login;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Login_Test {

    private WebDriver driver;
    
    // Data
    String url ="https://app.germanyiscalling.com/common/login/"
            + "?next=https%3A%2F%2Fapp.germanyiscalling.com%2Fcv%2Fhome%2F";
    
    // Sign Up Credentials
    private By signUpButton = By.xpath("//a[contains(text(),'New to Germany Is Calling?')]");
    private By nameInSignUp = By.id("first_name");
    private By emailInSignUp = By.id("username");
    private By passwordInSignUp = By.id("password");
    private By clickSignUp  = By.xpath("//button[@type='submit']");
    
    String name ="Rudra";
    String mail="rudraprasad123@yopmail.com";
    String password="Rudra@1234";
    
    // Sign in credentials
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit']");

    
    
    
    
    
    @BeforeMethod
    public void setUp() {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver(co);
        
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    
     // Sign up
    @Test(priority = 1)
    public void clickSignUp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement signUpElement = wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
            signUpElement.click();
        } catch (Exception e) {
            System.out.println("Sign Up button not found or not clickable: " + e.getMessage());
        }
    }
    
    @Test(priority = 2)
    public void signUpSuccessful() {
        // Instantiate SoftAssert
        SoftAssert softAssert = new SoftAssert();
        
        try {
            // Perform actions
            driver.findElement(nameInSignUp).sendKeys("name");
            driver.findElement(emailInSignUp).sendKeys("mail");
            driver.findElement(passwordInSignUp).sendKeys("password");
            driver.findElement(clickSignUp).click();
            
            // Perform assertions
            // For example, you can check if a certain element is visible after sign-up
            WebElement successMessage = driver.findElement(By.xpath("//div[contains(text(), 'Success')]"));
            softAssert.assertTrue(successMessage.isDisplayed(), "Success message not displayed.");
            
            // Add more assertions as needed
            // e.g., check for expected URL, title, or any other element on the next page
            String currentUrl = driver.getCurrentUrl();
            softAssert.assertEquals(currentUrl, "expected-success-url", "URL did not match the expected URL.");
            
        } catch (Exception e) {
            // Report exception with soft assertion
            softAssert.fail("Exception occurred: " + e.getMessage()):
        }
    
        
        
    @Test(priority = 3)
    public void testSuccessfulSignin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(usernameField));
            username.sendKeys("name");
            
            WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(emailInSignUp));
            email.sendKeys("mail");
            
            WebElement password = driver.findElement(passwordField);
            password.sendKeys("password");
            
            WebElement login = driver.findElement(loginButton);
            login.click();
        } catch (Exception e) {
            System.out.println("Login field not found or not clickable: " + e.getMessage());
        }
    }
    
    
    
    
    
    

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
