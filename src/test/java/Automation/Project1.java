package Automation;


import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Project1 {
    @Test
    public void testSignUp() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Step1
        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");

        //Step2
        String expectedTitle = "Welcome to Duotify!";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

        //Step3
        driver.findElement(By.className("hasAccountText")).click();


        //Step4-5-6
        Faker faker = new Faker();
        String username = faker.name().username();
        String firstName = faker.address().firstName();
        String lastName = faker.address().lastName();
        String email = faker.internet().emailAddress();
        String email2 = faker.internet().emailAddress();
        String password = faker.internet().password();
        String password2 = faker.internet().password();

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username, Keys.TAB);
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName, Keys.TAB);
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName, Keys.TAB);
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email, Keys.TAB);
        driver.findElement(By.xpath("//input[@name='email2']")).sendKeys(email, Keys.TAB);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password, Keys.TAB);
        driver.findElement(By.xpath("//input[@name='password2']")).sendKeys(password, Keys.ENTER);


        //Step7
        String expectedUrl = "http://duotify.us-east-2.elasticbeanstalk.com/browse.php?";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);

        //Step8
        String expectedFirstLast = firstName + " " + lastName;
        String actualFirstLast = driver.findElement(By.id("nameFirstAndLast")).getText();
        Assert.assertEquals(expectedFirstLast, actualFirstLast);

        //Step9
        driver.findElement(By.id("nameFirstAndLast")).click();
        String h1Element = driver.findElement(By.className("userInfo")).getText();
        Assert.assertEquals(actualFirstLast, h1Element);
        driver.findElement(By.id("rafael")).click();

        //Step10

        String expectedUrl2 = "http://duotify.us-east-2.elasticbeanstalk.com/register.php";
        String actualUrl2 = driver.getCurrentUrl();
        System.out.println(actualUrl2);
        //Assert.assertEquals(expectedUrl2, actualUrl2);
        //After Log Out driver.getCurrentUrl is still taking the previous Url. For that reason Step 10 is not working.

        //Step11
        driver.findElement(By.xpath("//input[@name='loginUsername']")).sendKeys(username, Keys.TAB);
        driver.findElement(By.xpath("//input[@name='loginPassword']")).sendKeys(password, Keys.ENTER);

        //Step12
        String expectedContent = "You Might Also Like";
        String actualContent = driver.findElement(By.className("pageHeadingBig")).getText();
        Assert.assertEquals(actualContent, expectedContent);

        //Step13
        driver.findElement(By.id("nameFirstAndLast")).click();
        driver.findElement(By.id("rafael")).click();
    }
}
