package com.flightTestng;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hpsf.Decimal;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {
	private static final TimeUnit Seconds = null;
	static WebDriver driver ;
	//Read Value
	public String readCellData(String sheetName, int rownum, int cellnum) throws IOException {
		String res = " ";
		File file = new File("C:\\Users\\User\\eclipse-workspace\\FrameWorkClass\\Excel\\D4Adactin.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		CellType type = cell.getCellType();
		switch (type) {
		case STRING:
			res = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				res = dateFormat.format(dateCellValue);
			}
			else {
				double numericCellValue = cell.getNumericCellValue();
				BigDecimal valueOf = BigDecimal.valueOf(numericCellValue);
				res = valueOf.toString();
				break;
			}
		default:
			break;
		}
		return res;
	}

	// Update Value
	public void updateCellData(String sheetName, int rownum, int cellnum, String olddata, 
			String newData) throws IOException {

		File file = new File("C:\\Users\\User\\eclipse-workspace\\FrameWorkClass\\Excel\\D4Adactin.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		String string = cell.getStringCellValue();
		if(string.equals(olddata)) {
			cell.setCellValue(newData);
		}
		FileOutputStream stream2 = new FileOutputStream(file);
		workbook.write(stream2);
	}
	
	//Write Cell Data
	public void writeCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {
		
		File file = new File("C:\\Users\\User\\eclipse-workspace\\FrameWorkClass\\Excel\\D4Adactin.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.createCell(cellnum);
		cell.setCellValue(data);
		FileOutputStream stream2 = new FileOutputStream(file);
		workbook.write(stream2);
	}
	
	//1.Enter URL
	public static void enterAppInUrl(String url) {
		driver.get(url);
	
	}
	//2.Maximize Window
	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}
	//3.Insert value in TextBox
	public void elementSendkeys(WebElement element, String data) {
		element.sendKeys(data);
	}
	//4.Click Button
	public void click(WebElement element) {
		element.click();
	}
	//5.Alert OK
	public void alertOk() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	//6.Alert Cancel
	public void alertCancel() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	//7.Text from WebPage
	public String elementGetText(WebElement element) {
		String text = element.getText();
		return text;
	}
	//8.Inserted value from TextBox----->99%
	public String elementGetAttributeValue(WebElement element) {
		String attribute = element.getAttribute("value");
		return attribute;
	}
	//9.Inserted value from TextBox----->1%
	public String elementGetAttributeValue(WebElement element, String attribute) {
		String attribute1 = element.getAttribute(attribute);
		return attribute1;
	}
	//10.Close All Window
	public static void quitWindow() {
		driver.quit();
	}
	//11.Close Window
	public void closeWindow() {
		driver.close();
	}
	//12.Get the Title
	public String getAppInTitle() {
		String title = driver.getTitle();
		return title;
	}
	//14.Select dropdown option by Text
	public void selectOptionByText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	//15.Select dropdown option by Attribute
	public void selectOptionByAttribute(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	//16.Select dropdown option by Index
	public void selectOptionByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	//17.Insert value in TextBox by JS
	public JavascriptExecutor elementSendkeysJs(WebElement element, String data) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Object executeScript = executor.executeScript("arguments[0].setAttribute('value'," +data+ ")", element);
		return (JavascriptExecutor) executeScript;
	}
	//18.Click Button by JS
	public void clickJs(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
	}
	//19.Lanch Chrome Browser
	public static void getDriver() {
		WebDriverManager.chromedriver().setup();
		WebDriver drivers = new ChromeDriver();
	driver = drivers;
	}
	//20.Switc to Child Window
	public void childWindow() {
		String windowHandle = driver.getWindowHandle();
		Set<String> allWindow = driver.getWindowHandles();
		for (String x : allWindow) {
			if(!windowHandle.equals(x));
			driver.switchTo().window(x);
		}
	}
	//21.Switch to Frame by Index
	public void switchToIndex(int index) {
		driver.switchTo().frame(index);
	}
	//22.Switch to Frame by FrameId
	public void switchToFrameId(String name) {
		driver.switchTo().frame(name);
	}
	//23.Find Loctor By Id
	public WebElement findElementId(String id) {
		WebElement element = driver.findElement(By.id(id));
		return element;
	}
	//24.Find Locator By Name
	public WebElement findElementByName(String name) {
		WebElement element = driver.findElement(By.name(name));
		return element;
	}
	//25.Find Locator By ClassName
	public WebElement findElementByClassName(String Value) {
		WebElement element = driver.findElement(By.className(Value));
		return element;
	}
	//26.Find Locator By xpath
	public WebElement findElementByXpath(String xpathExpression) {
		WebElement element = driver.findElement(By.xpath(xpathExpression));
		return element;
	}
	//27.get All option from dropdown as Text
	public void getText(WebElement element) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (int i = 0; i < options.size(); i++) {
			WebElement webElement = options.get(i);
			webElement.getText();
		}
	}
	//28.get All option from dropdown as Attribute Value
	public String getAttribute(WebElement element) {
		String attribute = element.getAttribute("value");
		return attribute;
	}
	//29.get All option from dropdown as FirstSelectOption
	public void firstSelectoption(WebElement element) {
		Select select = new Select(element);
		select.getFirstSelectedOption();
	}
	//30.dropdown isMultiple select option
	public boolean isMultiple(WebElement element) {
		Select select = new Select(element);
		boolean multiple = select.isMultiple();
		return multiple;
	}
	//31.Implicity Wait
	public void implicityWait(int time) {
		driver.manage().timeouts().implicitlyWait(time, Seconds);
	}
	//32.Explicit Wait
	public WebDriverWait explicitWait(Duration timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
	return wait;
	}
	//33.Fulent Wait
	public void fulentwait(String input) {
		WebDriver wait = (WebDriver) new FluentWait(input);
	}
	//34.Verify isDisplayed
	public boolean elementisDisplyed(WebElement element) {
		boolean displayed = element.isDisplayed();
		return displayed;
	}
	//35.verify isEnabled
	public boolean elementisEnabled(WebElement element) {
		boolean enabled = element.isEnabled();
		return enabled;
	}
	//36.verify isSelected
	public boolean elementisSelected(WebElement element) {
		boolean selected = element.isSelected();
		return selected;
	}
	//37.Deselect by Index
	public void deselectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.deselectByIndex(index);
	}
	//38.Deselect by Attribute
	public void deselectByAttribute(WebElement element, String value) {
		Select select = new Select(element);
		select.deselectByValue(value);		
	}
	//39.Deselect by Text
	public void deselectByVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.deselectByVisibleText(text);
	}
	//40.Deselect by All
	public void deselectByAll(WebElement element) {
		Select select = new Select(element);
		select.deselectAll();
	}
	//41.get the Parent Window
	public String parentWindow() {
		String windowHandle = driver.getWindowHandle();
		return windowHandle;
	}
	//42.get All Window
	public Set<String> allWindow() {
		Set<String> windowHandles = driver.getWindowHandles();
		return windowHandles;
	}
	//43.Clear Textbox
	public void clear(WebElement element) {
		element.clear();
	}
	//44.Take Screenshot
	public File takeScreenshot() {
		TakesScreenshot ss = (TakesScreenshot) driver;
		File screenshotAs = ss.getScreenshotAs(OutputType.FILE);
		return screenshotAs;
	}
	//45.Take Screenshot for Element
	public File takeScreenshotElement(WebElement element) {
		File screenshotAs = element.getScreenshotAs(OutputType.FILE);
		return screenshotAs;
	}
	//46.Mouseover action for Single Option
	public Actions mouseoverActionSingleOption(WebElement element) {
		Actions actions = new Actions(driver);
		Actions moveToElement = actions.moveToElement(element);
		return moveToElement;
	}
	//47.DragAndDrop
	public void dragAndDrop(WebElement source, WebElement target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target);
	}
	//48.Right Click
	public Actions rightClick() {
		Actions actions = new Actions(driver);
		Actions contextClick = actions.contextClick();
		return contextClick;
	}
	//49.Double Click
	public Actions doubleClick() {
		Actions actions = new Actions(driver);
		Actions doubleClick = actions.doubleClick();
		return doubleClick;
	}
	//51.Refresh Page
	public void refreshPage() {
		driver.navigate().refresh();
	}
	//52.Forward to Webpage
	public void forward() {
		driver.navigate().forward();
	}
	//53.Back to Webpage
	public void back() {
		driver.navigate().back();
	}
	//54.Robot using Down
	public void robotKeyPress() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
	}
	//55.ScrollDown Using JS
	public void scrollDownJs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	//56.ScrollUp Using JS
	public void scrollUpJs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", element);
	}
	//57.Thread Sleep
	public void threadSleep(long seconds) throws InterruptedException {
		Thread.sleep(seconds);
	}
	//58.Text from Alert
	public String alertText() {
		String text = driver.switchTo().alert().getText();
		return text;
	}
	//59.Dropdown All Select Option
	public List<WebElement> allSelect(WebElement element) {
		Select select = new Select(element);
		List<WebElement> allSelectedOptions = select.getAllSelectedOptions();
		return allSelectedOptions;
	}
	//60.get Webpage Source
	public Object webpageSource() {
		String pageSource = driver.getPageSource();
		return pageSource;
		
	}
	//61.create Excel
	public void createExcel(String pathname) {
		File file = new File(pathname);
	}
	public String date(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		String format = dateFormat.format(date);
		return format;
	}
}
