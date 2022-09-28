import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDownHandle {

	static WebDriver driver;
	static Select selectDay;

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ac90181\\eclipse-workspace\\LC-ConversionAutomationTest\\src\\"
						+ "main\\resources\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");

		WebElement month = driver.findElement(By.id("month"));
		WebElement day = driver.findElement(By.id("day"));
		WebElement year = driver.findElement(By.id("year"));

		/*
		 * Select selectMonth = new Select(month);
		 * selectMonth.selectByVisibleText("Nov");
		 * 
		 * Select selectDay = new Select(day); selectDay.selectByVisibleText("24");
		 * 
		 * Select selectYear = new Select(year); selectYear.selectByVisibleText("1987");
		 */

		String dob = "11-24-1987";
		String dobArr[] = dob.split("-");

		selectValueFromDropDown(month, dobArr[0]);
		selectValueFromDropDown(day, dobArr[1]);
		selectValueFromDropDown(year, dobArr[2]);

		String month_Xpath = "//select[@id='month']//option";
		String yearh_Xpath = "//select[@id='year']//option";
		String day_Xpath = "//select[@id='day']//option";

		selectDropDownWithoutSelectClass(month_Xpath, "Nov");
		selectDropDownWithoutSelectClass(yearh_Xpath, "1987");
		selectDropDownWithoutSelectClass(day_Xpath, "24");
	}
	
	// *********************** Static dropdown ***********************

	public static void selectValueFromDropDown(WebElement elemnt, String value) {

		selectDay = new Select(elemnt);
		selectDay.selectByVisibleText(value);

		// selectDay.deselectAll();
		// selectDay.isMultiple();
		// selectDay.getFirstSelectedOption().getText();
	}

	public static void selectAllTheValueFromDropdownByUsingGetOoptionsMethod() {

		List<WebElement> listDays = selectDay.getOptions();
		// listDays.size();
		for (int i = 0; i < listDays.size() - 1; i++) {
			String dayVal = listDays.get(i).getText();
			System.out.println(dayVal);

			if (dayVal.equals("15")) {
				listDays.get(i).click();
				break;
			}
		}

	}

	public static void selectDropDownWithoutSelectClass(String xpath, String value) {

		List<WebElement> dropdownValue = driver.findElements(By.id(xpath));
		for (int i = 0; i < dropdownValue.size() - 1; i++) {
			String monthVal = dropdownValue.get(i).getText();
			System.out.println(monthVal);

			if (monthVal.equalsIgnoreCase(value)) {
				dropdownValue.get(i).click();
				break;
			}
		}
	}

// ************************* Dynamic Dropdown ***************************

	public static void dynamicDropdownSpiceJet() throws InterruptedException {

		// driver.get("http://spicejet.com"); //URL in the browser

		// //a[@value='MAA'] - Xpath for chennai

		// //a[@value='BLR']

		// From Destination
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		// Selecting Banglore
		driver.findElement(By.xpath("//a[@value='BLR']")).click();

		Thread.sleep(2000);

		// driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();
		// To Destination- Chennai
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']"))
				.click();

		driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active")).click();
	}

	public static void autoSuggestiveDropdown() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

		driver.findElement(By.id("autosuggest")).sendKeys("ind");

		Thread.sleep(3000);

		List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));

		for (WebElement option : options)

		{

			if (option.getText().equalsIgnoreCase("India"))

			{

				option.click();

				break;

			}
		}
	}
}
