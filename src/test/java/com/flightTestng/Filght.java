package com.flightTestng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Filght {
	WebDriver driver;
	@Parameters({"departureCity","destinationCity"})
	@Test(priority=1)
	public void selectCity(String departureCity, String destinationCity) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://blazedemo.com/");
		driver.manage().window().maximize();
		
		WebElement message = driver.findElement(By.xpath("//h1[contains(text(),'Welcome to the Simple Travel Agency!')]"));
		String actMessage = message.getText();
		Assert.assertEquals(actMessage, "Welcome to the Simple Travel Agency!","Verify Welcome to the Simple Travel Agency!");
		
		WebElement depCity = driver.findElement(By.xpath("//select[@name='fromPort']"));
		Select select = new Select(depCity);
		select.selectByIndex(2);
		
		WebElement desCity = driver.findElement(By.xpath("//select[@name='fromPort']/ancestor::div//select[@name='toPort']"));
		Select select2 = new Select(desCity);
		select2.selectByVisibleText(destinationCity);
		driver.findElement(By.xpath("//select[@name='fromPort']/ancestor::div//div/input")).click();
		
	}
	@Test(priority=2)
	public void selectFilght() {
		
		WebElement message = driver.findElement(By.xpath("//h3[contains(text(),'Flights from Boston to London: ')]"));
		String actMessage = message.getText();
		Assert.assertEquals(actMessage, "Flights from Boston to London:","Verify Flights from Boston to London: ");
		
		driver.findElement(By.xpath("(//input[@type='submit'])[1]")).click();

	}
	@Parameters({"name","address","city","state","zipCode","cardType","cCNumber","cCM","cCY","nameOnCard"})
	@Test(priority=3)
	public void reservedFight() {
		
		WebElement message = driver.findElement(By.xpath("//h2[contains(text(),'Your flight from TLV to SFO has been reserved.')]"));
		String actMessage = message.getText();
		Assert.assertEquals(actMessage, "Your flight from TLV to SFO has been reserved.","Verify Your flight from TLV to SFO has been reserved.");
		
		driver.findElement(By.xpath("//input[@id='inputName']")).sendKeys("Satheeshkumar");
		
		driver.findElement(By.xpath("//input[@id='inputName']/ancestor::div//div//input[@name='address']")).sendKeys("Medavakkam");
		
		driver.findElement(By.xpath("//input[@id='inputName']/ancestor::div//div//input[@name='city']")).sendKeys("Chennai");
		
		driver.findElement(By.xpath("//input[@id='inputName']/ancestor::div//div//input[@name='state']")).sendKeys("Tamil Nadu");
		
		driver.findElement(By.xpath("//input[@id='inputName']/ancestor::div//div//input[@name='zipCode']")).sendKeys("600100");
		
		WebElement cardType = driver.findElement(By.xpath("//input[@id='inputName']/ancestor::div//div//select[@name='cardType']"));
		Select select = new Select(cardType);
		select.selectByVisibleText("American Express");
		
		driver.findElement(By.xpath("//input[@id='inputName']/ancestor::div//div//input[@name='creditCardNumber']")).sendKeys("1234567890567890");
		
		driver.findElement(By.xpath("//input[@id='inputName']/ancestor::div//div//input[@name='creditCardMonth']")).sendKeys("May");
		
		driver.findElement(By.xpath("//input[@id='inputName']/ancestor::div//div//input[@name='creditCardYear']")).sendKeys("2024");
		
		driver.findElement(By.xpath("//input[@id='inputName']/ancestor::div//div//input[@name='nameOnCard']")).sendKeys("SatheeshkumarEswaran");
		
		driver.findElement(By.xpath("//input[@id='inputName']/ancestor::div//div//input[@name='rememberMe']")).click();
		
		driver.findElement(By.xpath("//input[@id='inputName']/ancestor::div//div//input[@type='submit']")).click();
	}
	
	@Test(priority=4)
	
	public void purchaseConfirm() {
		WebElement message = driver.findElement(By.xpath("//h1[contains(text(),'Thank you for your purchase today!')]"));
		String actMessage = message.getText();
		Assert.assertEquals(actMessage, "Thank you for your purchase today!","Verify Thank you for your purchase today!");
		
		driver.quit();
	}

	
	

}
