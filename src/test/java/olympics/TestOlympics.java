package olympics;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestOlympics {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();

	}

	@Test
	public void sortTest() {
		ArrayList<Integer> obtainedList = new ArrayList<>();

		// Step1

		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");

		// Step 2
		List<WebElement> ranks = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));

		for (int i = 0; i < 10; i++) {
			obtainedList.add(Integer.parseInt(ranks.get(i).getText()));
		}
		// System.out.println(obtainedList);

		ArrayList<Integer> sortedList = new ArrayList<>();
		for (Integer s : obtainedList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		// System.out.println(sortedList);
		assertTrue(sortedList.equals(obtainedList));

		// Step 3
		driver.findElement(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[2]"))
				.click();

		// Step 4
		ArrayList<String> obtainedCountries = new ArrayList<>();
		List<WebElement> countries = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th[1]"));

		for (int i = 0; i < countries.size(); i++) {
			obtainedCountries.add(countries.get(i).getText());
		}
		// System.out.println(obtainedList);

		ArrayList<String> sortedCountries = new ArrayList<>();

		for (String country : obtainedCountries) {
			sortedCountries.add(country);
		}
		Collections.sort(sortedCountries);
		// System.out.println(sortedList);
		assertTrue(sortedCountries.equals(obtainedCountries));

		// Step 5

		List<WebElement> ranksAgain = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));

		for (int i = 0; i < 10; i++) {
			obtainedList.add(Integer.parseInt(ranks.get(i).getText()));
		}
		// System.out.println(obtainedList);

		ArrayList<Integer> sortedListAgain = new ArrayList<>();
		for (Integer s : obtainedList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		// System.out.println(sortedList);
		assertFalse(sortedList.equals(obtainedList));
	}

	// @Test
	// public void theMost() throws InterruptedException {
	//
	// driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
	//
	// // Task 2 Golden
	//
	// driver.findElement(
	// By.xpath("//table[@class='wikitable sortable plainrowheaders
	// jquery-tablesorter']/thead/tr/th[3]"))
	// .click();
	// Thread.sleep(3000);
	//
	// driver.findElement(
	// By.xpath("//table[@class='wikitable sortable plainrowheaders
	// jquery-tablesorter']/thead/tr/th[3]"))
	// .click();
	//
	// WebElement countryGolden = driver.findElement(
	// By.xpath("//table[@class='wikitable sortable plainrowheaders
	// jquery-tablesorter']/tbody/tr/th[1]"));
	// System.out.println("The country with the most number of gold medals is" +
	// countryGolden.getText());
	//
	// // Task 3 Silver
	//
	// driver.findElement(
	// By.xpath("//table[@class='wikitable sortable plainrowheaders
	// jquery-tablesorter']/thead/tr/th[4]"))
	// .click();
	// Thread.sleep(3000);
	// driver.findElement(
	// By.xpath("//table[@class='wikitable sortable plainrowheaders
	// jquery-tablesorter']/thead/tr/th[4]"))
	// .click();
	//
	// WebElement countrySilver = driver.findElement(
	// By.xpath("//table[@class='wikitable sortable plainrowheaders
	// jquery-tablesorter']/tbody/tr/th[1]"));
	// System.out.println("The country with the most number of silver medals is" +
	// countrySilver.getText());
	//
	//
	// // Task 4 Bronze
	//
	// driver.findElement(By.xpath("//table[@class='wikitable sortable
	// plainrowheaders jquery-tablesorter']/thead/tr/th[5]")).click();
	// Thread.sleep(3000);
	// driver.findElement(By.xpath("//table[@class='wikitable sortable
	// plainrowheaders jquery-tablesorter']/thead/tr/th[5]")).click();
	//
	// WebElement countryBronze = driver.findElement(
	// By.xpath("//table[@class='wikitable sortable plainrowheaders
	// jquery-tablesorter']/tbody/tr/th[1]"));
	// System.out.println("The country with the most number of bronze medals is" +
	// countryBronze.getText());
	//
	//
	// // Task 5 all medals
	//
	// driver.findElement(By.xpath("//table[@class='wikitable sortable
	// plainrowheaders jquery-tablesorter']/thead/tr/th[6]")).click();
	// Thread.sleep(3000);
	// driver.findElement(By.xpath("//table[@class='wikitable sortable
	// plainrowheaders jquery-tablesorter']/thead/tr/th[6]")).click();
	//
	// WebElement countryAll = driver.findElement(
	// By.xpath("//table[@class='wikitable sortable plainrowheaders
	// jquery-tablesorter']/tbody/tr/th[1]"));
	// System.out.println("The country with the most number of all medals is" +
	// countrySilver.getText());
	//
	// Assert.assertEquals(countryGolden.getText(), " United States (USA)");
	// Assert.assertEquals(countrySilver.getText(), " United States (USA)");
	// Assert.assertEquals(countryBronze.getText(), " United States (USA)");
	// }

	@Test
	public void countryByMedal() {

		String expected = "ChinaFrance";
		String actual = "";

		// Step1
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		driver.findElement(By.xpath(
				"//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tr[@class='sortbottom'][1]"))
				.click();

		List<WebElement> allSilver = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr"));
		List<String> silver18 = new ArrayList<>();

		for (int i = 0; i < allSilver.size(); i++) {

			String countryXpath = "(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr)["
					+ (i + 1) + "]//a";
			String silverXpath = "(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr)["
					+ (i + 1) + "]/td[3]";

			String s = "18";
			if (driver.findElement(By.xpath(silverXpath)).getText().equals(s)) {
				silver18.add(driver.findElement(By.xpath(countryXpath)).getText());
				actual = actual + driver.findElement(By.xpath(countryXpath)).getText();
			}

		}
		Assert.assertEquals(actual, expected);

	}
	
	@Test 
	
	public void getIndex() {
		
			String str = japanTest("Japan");
			String[] arr = str.split(",");
			int col = Integer.valueOf(arr[0]);
			int row = Integer.valueOf(arr[1]);
			assertEquals(col,6);
			assertEquals(row,2);

		}

		public String japanTest(String countryName) {
			driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
			List<WebElement> countryNames = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr"));
			int count = 0;
			int col = 0;
			ArrayList<String> country = new ArrayList<String>();


			for (int i = 0; i < countryNames.size() - 1; i++) {
				count++;

				String str = countryNames.get(i).getText();
				country.add(str);
				if (str.contains(countryName)) {
					col = count;
				}
				System.out.println(str);
			}

			int row = 0;
			int countRow = 0;
			List<WebElement> column = driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th"));

			for (int i = 0; i < column.size(); i++) {
				String columnOrder = column.get(i).getText();
//				System.out.println(columnOrder);
				countRow++;
				if (columnOrder.contains("NOC")) {
					row = countRow;
				}
			}

//			System.out.println(row);

			return col + "," + row;

		}
		public void testCase5() {
			driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
			List<WebElement> countryLst = driver
					.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/th/a"));
			List<WebElement> bronzeLst = driver
					.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/td[4]"));
			// remove the last row of bronzeList
			bronzeLst.remove(bronzeLst.size() - 1);
			
			List<String> result = new ArrayList<>();
			Map<String, Integer> map = getMap(countryLst, bronzeLst);

			Set<Entry<String, Integer>> entries = map.entrySet();

			for (Entry<String, Integer> entry1 : entries) {
				for (Entry<String, Integer> entry2 : entries) {
					if(!entry1.getKey().equals(entry2.getKey()) && !result.contains(entry1.getKey()) && entry1.getValue() + entry2.getValue() == 18) {
						result.add(entry1.getKey());
						result.add(entry2.getKey());
					}
				}
			}
			Assert.assertEquals(result, Arrays.asList("Italy","Australia"));
		}
		
		public Map<String, Integer> getMap(List<WebElement> lst1, List<WebElement> lst2) {
			Map<String, Integer> map = new HashMap<>();

			for (int i = 0; i < lst1.size(); i++) {
				map.put(lst1.get(i).getText(), Integer.valueOf(lst2.get(i).getText()));
			}

			return map;
		}

	// @AfterClass
	// public void tearDown() {
	// driver.close();
}
//}
