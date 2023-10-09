package com.github.curriculeon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.DriverManager;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DemoApplicationTests {

    @Test
    public void test() {
        // Use WebDriverManager to manage ChromeDriver
        WebDriverManager.firefoxdriver().setup();

        // Initialize the WebDriver
        WebDriver driver = new FirefoxDriver();

        try {

            // Navigate to the web page
            driver.get("http://localhost:8080/");

            // Find and interact with the input fields
            WebElement firstNameInput = driver.findElement(By.id("fname"));
            WebElement lastNameInput = driver.findElement(By.id("lname"));

            firstNameInput.sendKeys("John");
            lastNameInput.sendKeys("Doe");

            // Click the "Create Person" button
            WebElement createButton = driver.findElement(By.xpath("//button[contains(text(), 'Create Person')]"));
            createButton.click();

            // Wait for the response to load (you may need to add explicit waits)
            Thread.sleep(2000);

            // Verify the output in the "output" div
            WebElement outputDiv = driver.findElement(By.id("output"));
            String outputText = outputDiv.getText();

            if (outputText.contains("Successfully created")) {
                System.out.println("Test passed: Person created successfully.");
            } else {
                System.out.println("Test failed: Person creation failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver
            driver.quit();
        }

    }
}
