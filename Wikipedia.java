
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Wikipedia {
	public static void main(String[] args) {
		// Instantiate Chrome driver
		System.setProperty("webdriver.chrome.driver", "insert your path to downloaded Chrome Driver here");
		WebDriver driver = new ChromeDriver();

		// Visit Wikipedia using Chrome driver
		driver.get("http://www.wikipedia.org");

		// Find the text input element by its name
		WebElement element = driver.findElement(By.name("search"));

		// Enter something to search for
		element.sendKeys("Selenium"); // put the string you want to search here. In this example, it automatically
										// searches for "Selenium" here
		long end = System.currentTimeMillis() + 30000;
		while (System.currentTimeMillis() < end) {
			ArrayList<WebElement> resultsDiv = (ArrayList<WebElement>) driver
					.findElements(By.className("suggestions-dropdown"));
			if (resultsDiv.size() > 0) {
				break;
			}
		}
		String someURL = "";
		List<WebElement> suggestLinks = driver.findElements(By.className("suggestion-link"));
		for (WebElement we : suggestLinks) { // get the hrefs of each of the suggestion links
			someURL = we.getAttribute("href");
			break;
		}
		driver.get(someURL);
		List<WebElement> k = driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/p"));
		if (k.get(0).getText().length() != 0) { // print the intro of the first paragraph to the console
			System.out.println(k.get(0).getText());
		} else if (k.get(1).getText().length() != 0) {
			System.out.println(k.get(1).getText());
		} else if (k.get(2).getText().length() != 0) {
			System.out.println(k.get(2).getText());
		}
		driver.quit();

	}
}
