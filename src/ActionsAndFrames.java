import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsAndFrames {
	static WebDriver driver;

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ac90181\\eclipse-workspace\\LC-ConversionAutomationTest\\src\\"
						+ "main\\resources\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://jqueryui.com/droppable/");
		System.out.println(driver.findElement(By.tagName("iframe")).getSize());
		driver.switchTo().frame(0);
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		//driver.findElement(By.id("draggble")).click();
		
		Actions ac = new Actions(driver);
		WebElement draggableSource = driver.findElement(By.id("draggble"));
		WebElement droppableTarget = driver.findElement(By.id("droppable"));
		ac.dragAndDrop(draggableSource, droppableTarget).build().perform();
		driver.switchTo().defaultContent();
		
	}

}
