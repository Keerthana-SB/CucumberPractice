package steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Implement {
	public ChromeDriver driver;
	public WebDriverWait wait;
	public static	JavascriptExecutor js; 
	public static int lowKmSize;
	public static List<Integer> kmsList = new ArrayList<Integer> ();
	public static List<Integer> sortKm = new ArrayList<Integer>();
	@Given("Launch https://www.carwale.com/")
	public void launch_https_www_carwale_com() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
	    driver=new ChromeDriver(options);
		driver.get("https://www.carwale.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}

	@Given("Click on Used")
	public void click_on_Used() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElementByXPath("//li[@data-tabs='usedCars']").click();
	}

	@Given("Select the City as Chennai")
	public void select_the_City_as_Chennai() {
	    driver.findElementById("usedCarsList").sendKeys("Chennai");
	    driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']").click();
	}

	@Given("Select budget min and max and Click Search")
	public void select_budget_min_L_and_max_L_and_Click_Search() {
	    driver.findElementById("budgetBtn").click();
	    driver.findElementById("minInput").click();
	    driver.findElementByLinkText("8 Lakh").click();
	    driver.findElementById("maxInput").click();
	    driver.findElementByLinkText("12 Lakh").click();
	    driver.findElementById("btnFindCar").click();
	}

	@Given("Select Cars with Photos under Only Show Cars With")
	public void select_Cars_with_Photos_under_Only_Show_Cars_With() {
		driver.findElementByName("CarsWithPhotos").click();
	}

	@Given("Select Manufacturer as Hyundai --> Creta")
	public void select_Manufacturer_as_Creta() {
		wait = new WebDriverWait(driver,10);
	    wait.until(ExpectedConditions.visibilityOf (driver.findElementByXPath("//span[text()=' Hyundai ']"))); 
		driver.executeScript("arguments[0].click()",driver.findElementByXPath("//span[text()=' Hyundai ']"));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Creta']")));
		driver.executeScript("arguments[0].click();", driver.findElementByXPath("//span[text()='Creta']"));
	}

	@Given("Select Fuel Type as Petrol")
	public void select_Fuel_Type_as_Petrol() {
	    driver.findElementByXPath("//h3[text()[normalize-space()='Fuel Type']]").click();
	    driver.findElementByXPath("(//span[text()='Petrol'])[1]").click();
	}

	@Given("Select Best Match as KM: Low to High")
	public void select_Best_Match_as(String string) {
		WebElement match = driver.findElementById("sort");
        Select dd = new Select(match);
		dd.selectByVisibleText("KM: Low to High");
	}

	@Given("Validate the Cars are listed with KMs Low to High")
	public void validate_the_Cars_are_listed_with_KMs_Low_to_High() {
		 String kms ="";
		    List<WebElement> eleKmsLow = driver.findElementsByXPath("//span[@class=\"slkms vehicle-data__item\"]");
		     lowKmSize = eleKmsLow.size();
		    for(int i=0;i<lowKmSize;i++) {
		    	kms = eleKmsLow.get(i).getText();
		    	String reKms = kms.replaceAll("[^0-9]", "");
				int onlyKmsNos = Integer.parseInt(reKms);
				kmsList.add(onlyKmsNos);
		    }

		    sortKm.addAll(kmsList);
		Collections.sort(sortKm);
		if(kmsList.equals(sortKm)) {
			System.out.println("The cars are listed with KMs Low to High");
		}else {
				System.out.println("The cars are not listed  with Kms Low to High");
			}
	}

	@Given("Add the least KM ran car to Wishlist")
	public void add_the_least_KM_ran_car_to_Wishlist() throws InterruptedException {
		js = (JavascriptExecutor) driver;

		for (int j = 0; j < lowKmSize; j++) {
			if (kmsList.get(j).equals(sortKm.get(0))) {
				WebElement addToCart = driver
						.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])[" + (j + 1) + "]");
				js.executeScript("arguments[0].click()", addToCart);
				Thread.sleep(2000);
				break;
			}
		}
	}

	@Given("Go to Wishlist and Click on More Details")
	public void go_to_Wishlist_and_Click_on_More_Details() throws InterruptedException {
		js = (JavascriptExecutor) driver;

		WebElement wishList = driver.findElementByXPath("//li[@data-action='ShortList&CompareWindow_Click']");
		js.executeScript("arguments[0].click()", wishList);

		WebElement moreDetails = driver.findElementByXPath("//a[contains(text(),'More details')]");
		js.executeScript("arguments[0].click()", moreDetails);

		Thread.sleep(3000);
	}

	@When("Print all the details under Overview in the Same way as displayed in application?")
	public void print_all_the_details_under_Overview_in_the_Same_way_as_displayed_in_application() {
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allList = new ArrayList<String>(allWindows);
		driver.switchTo().window(allList.get(1));
		Map<String, String> details = new LinkedHashMap<String, String>();
		List<WebElement> overview = driver
				.findElementsByXPath("//div[@id='overview']//div[@class='equal-width text-light-grey']");
		List<WebElement> features = driver
				.findElementsByXPath("//div[@id='overview']//div[@class='equal-width dark-text']");
		int detailsSize = overview.size();
		for (int k = 0; k < detailsSize; k++) {
			String mapOverview = overview.get(k).getText();
			String mapFeatures = features.get(k).getText();
			details.put(mapOverview, mapFeatures);
		}

		for (Entry<String, String> each : details.entrySet()) {
			System.out.println(each.getKey() + " ---------- " + each.getValue());

		}

	}

	@Then("Close the browser")
	public void close_the_browser() {
		 driver.quit(); 
	}

}