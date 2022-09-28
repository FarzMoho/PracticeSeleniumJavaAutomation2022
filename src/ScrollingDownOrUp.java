import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScrollingDownOrUp {
	static WebDriver driver;

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ac90181\\eclipse-workspace\\LC-ConversionAutomationTest\\src\\"
						+ "main\\resources\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("driver.get(\"http://www.cricbuzz.com/live-cricket-scorecard/18970/pak-vs-sl-2nd-t20i-pakistan-v-sri-lanka-in-uae-2017\");");
	}
	
	public static void scrollDownPageByPixel(By by) throws InterruptedException {

		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollBy(0,200)");
		Thread.sleep(3000);

	}
	
	public static void scrollTopOnHTML_DOM(By by) throws InterruptedException {

		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");
		Thread.sleep(3000);

	}

}
