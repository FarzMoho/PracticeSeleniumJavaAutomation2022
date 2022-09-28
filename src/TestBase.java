import java.time.Duration;
import java.util.Iterator;

import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class TestBase {

	public static void main(String[] args) throws InterruptedException {

		// Chrome Driver

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ac90181\\OneDrive - Lumen\\Documents\\Farzana\\Selenium-Java\\ChromeDriver\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		// Microsoft Edge Driver:

		/*
		 * System.setProperty("webdriver.edge.driver",
		 * "C:\\Users\\ac90181\\OneDrive - Lumen\\Documents\\Farzana\\Selenium-Java\\MicrosoftEdgeDriver\\edgedriver_win64\\msedgedriver.exe"
		 * ); WebDriver driver = new EdgeDriver();
		 */

		driver.manage().window().maximize();

		// Globally setting up the wait/implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Explicit wait:
		// @SuppressWarnings("deprecation")
		// WebDriverWait w =new WebDriverWait(driver,5);
		// w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));

		driver.get("https://eshop-test1.test.intranet/eshop/customerCare/dist/index.html");

		driver.findElement(By.id("i0116")).sendKeys("autoregr@lumen.com");
		driver.findElement(By.id("idSIButton9")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("i0118")).sendKeys("fCnvLbQdk2VgxXmfn1h5FkSB");

		Thread.sleep(1000);
		driver.findElement(By.cssSelector(("input[type='submit']"))).click();
		driver.findElement(By.cssSelector(("input[value='Yes']"))).click();

		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());

		// driver.close(); // close the current window.
		// driver.quit(); // close all the open browser which opened by the driver.

		// driver.findElement(By.id("address")).sendKeys("447312901");
		driver.findElement(By.linkText("Account or Order already exists")).click();
		driver.findElement(By.id("accountNumber")).sendKeys("314093402");
		driver.findElement(By.id("firstName")).sendKeys("James");
		driver.findElement(By.id("lastName")).sendKeys("Ahmer");
		driver.findElement(By.id("phoneNumber")).sendKeys("571-209-4378");
		driver.findElement(By.className("btn-letsGo")).click();

		driver.findElement(By.cssSelector("button.btn-make-changes")).click();
		// driver.findElement(By.className("btn-make-changes")).click();
		// driver.findElement(By.xpath("//button[@type='button']")).click();
		// driver.findElement(By.cssSelector("button[type='button']")).click();
		driver.findElement(By.className("btn-primary")).click();

		// Assert.assertEquals(false, false);

		// Dropdown with select tag:

		WebElement iwp = driver.findElement(By.xpath("//select[@class='ng-star-inserted']"));

		Select IWPdropdown = new Select(iwp);
		IWPdropdown.selectByIndex(1);
		IWPdropdown.getFirstSelectedOption().getText();
		IWPdropdown.selectByVisibleText("Yes");
		IWPdropdown.selectByValue("Yes");

		/*
		 * Get Data From Mongodb Database And Data Validation Using Mongoclient
		 * 
		 * https://automationscript.com/selenium-java/how-to-read-data-from-mongodb-in-
		 * java/
		 * 
		 * @BeforeTest public void connectDB() { mongoClient = new MongoClient(
		 * "localhost" , 27017); db = mongoClient.getDB("test"); dbCollection =
		 * db.getCollection("zips"); searchQuery = new BasicDBObject();
		 * searchQuery.put("state", "MA"); cursor = dbCollection.find(searchQuery);
		 * 
		 * @Test public void getresponse() { System.out.println("in test");
		 * 
		 * //Fetching the response String response = null; try { while(cursor.hasNext())
		 * { response = response.concat(cursor.next().toString()); } } catch(Exception
		 * e){ System.out.println(e.getMessage()); } //Asserting the fetched response
		 * with expected value Assert.assertTrue(response.contains("ExpectedValue")); }
		 * }
		 * 
		 * https://www.codejava.net/java-se/jdbc/java-connecting-to-mongodb-database-examples
		 */

		MongoClient mongoClient = new MongoClient("vlmddmong02.dev.intranet", 26000);
		@SuppressWarnings("deprecation")
		//DB db = mongoClient.getDB("test");
		MongoDatabase db = mongoClient.getDatabase("test");
		//DBCollection dbCollection = db.getCollection("zips");
		MongoCollection<Document> dbCollection = db.getCollection("customerOrder");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("state", "MA");
		//DBCursor cursor = dbCollection.find(searchQuery);
		MongoIterable<Document> cursor = dbCollection.find(searchQuery);
		// Fetching the response
		String response = null;
		try {
			while (((Iterator<DBObject>) cursor).hasNext()) {
				response = response.concat(((Iterator<DBObject>) cursor).next().toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Asserting the fetched response with expected value
		Assert.assertTrue(response.contains("ExpectedValue"));

	}

}
