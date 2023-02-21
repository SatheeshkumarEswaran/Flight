package com.flightDataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlightBooking {

	WebDriver driver;

	public String readCell(int rownum, int cellnum) throws IOException {

		String string = " ";
		System.out.println(rownum);
		System.out.println(cellnum);
		File file = new File("C:\\Users\\User\\eclipse-workspace\\FrameWorkClass\\Excel\\FlightBooking.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet("Flight");
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		CellType cellType = cell.getCellType();
		switch (cellType) {
		case STRING:
			string = cell.getStringCellValue();
			break;
		case NUMERIC:
			double numericCellValue = cell.getNumericCellValue();
			BigDecimal valueOf = BigDecimal.valueOf(numericCellValue);
			string = valueOf.toString();
			break;
		}
		return string;
	}

	public void selectCity() throws IOException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://blazedemo.com/");

		WebElement dDnDepCity = driver.findElement(By.name("fromPort"));
		Select select = new Select(dDnDepCity);
		select.selectByVisibleText(readCell(1, 0));

		WebElement dDnDesCity = driver.findElement(By.name("toPort"));
		Select select1 = new Select(dDnDesCity);
		select1.selectByVisibleText(readCell(1, 1));

		driver.findElement(By.xpath("//input[@type='submit']")).click();

	}

	public void chooseFlight() {

		driver.findElement(By.xpath("(//input[@type='submit'])[1]")).click();

	}

	public void reserveFlight() throws IOException {

		WebElement txtName = driver.findElement(By.xpath("//input[@name='inputName']"));
		txtName.sendKeys(readCell(1, 2));

		WebElement txtAddress = driver
				.findElement(By.xpath("//input[@name='inputName']//ancestor::div//div//input[@id='address']"));
		txtAddress.sendKeys(readCell(1, 3));

		WebElement txtCity = driver
				.findElement(By.xpath("//input[@name='inputName']//ancestor::div//div//input[@id='city']"));
		txtCity.sendKeys(readCell(1, 4));

		WebElement txtState = driver
				.findElement(By.xpath("//input[@name='inputName']//ancestor::div//div//input[@id='state']"));
		txtState.sendKeys(readCell(1, 5));

		WebElement txtZipCode = driver
				.findElement(By.xpath("//input[@name='inputName']//ancestor::div//div//input[@id='zipCode']"));
		txtZipCode.sendKeys(readCell(1, 6));

		WebElement dDnCardType = driver.findElement(By.xpath("//input[@name='inputName']//ancestor::div//div//select[@id='cardType']"));
		Select select = new Select(dDnCardType);
		select.selectByVisibleText(readCell(1, 7));

		WebElement txtCardNumber = driver
				.findElement(By.xpath("//input[@name='inputName']//ancestor::div//div//input[@id='creditCardNumber']"));
		txtCardNumber.sendKeys(readCell(1, 8));

		WebElement txtMonth = driver
				.findElement(By.xpath("//input[@name='inputName']//ancestor::div//div//input[@id='creditCardMonth']"));
		txtMonth.sendKeys(readCell(1, 9));

		WebElement txtYear = driver
				.findElement(By.xpath("//input[@name='inputName']//ancestor::div//div//input[@id='creditCardYear']"));
		txtYear.sendKeys(readCell(1, 10));

		WebElement txtNameOnCard = driver
				.findElement(By.xpath("//input[@name='inputName']//ancestor::div//div//input[@id='nameOnCard']"));
		txtNameOnCard.sendKeys(readCell(1, 11));

		driver.findElement(By.xpath("//input[@name='inputName']//ancestor::div//div//input[@id='rememberMe']")).click();

		driver.findElement(By.xpath("//input[@name='inputName']//ancestor::div//div//input[@type='submit']")).click();

	}

	public static void main(String[] args) throws IOException {
		FlightBooking objectFlightBooking = new FlightBooking();
		objectFlightBooking.selectCity();
		objectFlightBooking.chooseFlight();
		objectFlightBooking.reserveFlight();
	}

}
