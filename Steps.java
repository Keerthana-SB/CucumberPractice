package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {
	public static ChromeDriver driver;
	public static	JavascriptExecutor js; 
	@Given("Go to https://www.trivago.com/")
	public void go_to_https_www_trivago_com() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver=new ChromeDriver(options);
		driver.get("https://www.trivago.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.executeScript("window.scrollBy(0,150)", "");
	    Thread.sleep(1000);
	}

	@Given("Type Agra in Destination and select Agra, Uttar Pradesh")
	public void type_Agra_in_Destination_and_select_Agra_Uttar_Pradesh() {
	    driver.findElementByXPath("//input[@placeholder='Enter a hotel name or destination']").sendKeys("Agra");
	    driver.findElementByXPath("(//span[@class='ssg-title'])[1]").click();
	}

	@Given("Choose June 15 as check in and June 30 as check out")
	public void checkInandcheckOut() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		WebElement date15 = driver.findElementByXPath("//time[@datetime='2020-06-15']");
		js.executeScript("arguments[0].click()", date15);
		Thread.sleep(1000);
		WebElement date30 = driver.findElementByXPath("//time[@datetime='2020-06-30']");
		js.executeScript("arguments[0].click()", date30);
		Thread.sleep(3000);	
		}

	@Given("Select Room as Family Room")
	public void select_Room_as_Family_Room() {
	    driver.findElementByXPath("//span[text()='Family rooms']").click();
	}

	@Given("Choose Number of Adults 2, Childern 1 and set Child's Age as 4")
	public void choose_Number_of_Adults_Childern_and_set_Child_s_Age_as() throws InterruptedException {
		WebElement eleChildren = driver.findElementById("select-num-children-2");
		Select eleNos = new Select(eleChildren);
		eleNos.selectByVisibleText("1");
		Thread.sleep(3000);
   		WebElement eleChildrenAge = driver.findElementById("select-ages-children-2-3");
		Select eleAge = new Select(eleChildrenAge);
		eleAge.selectByVisibleText("4");
		Thread.sleep(3000);
	}

	@Given("Click Confirm button and click Search")
	public void click_Confirm_button_and_click_Search() throws InterruptedException {
		driver.findElementByXPath("//span[text()='Confirm']").click();
		Thread.sleep(5000);
	}

	@Given("Select Accommodation type as Hotels only and choose 4 stars")
	public void select_Accommodation_type_as_Hotels_only_and_choose_stars() throws InterruptedException {
		js = (JavascriptExecutor) driver;
	    WebElement Accommodation = driver.findElementByXPath("//strong[text()='Accommodation']");
	    Actions builder = new Actions(driver);
		builder.moveToElement(Accommodation).perform();
	    driver.findElementById("acc-type-filter-1").click();
	    Thread.sleep(1000);
	    WebElement stars = driver.findElementByXPath("//label[text()='Hotels only']/following::span[text()='4-star hotels']");
	   js.executeScript("arguments[0].click()", stars);
       Thread.sleep(1000);
	   WebElement done = driver.findElementById("filter-popover-done-button");
	   js.executeScript("arguments[0].click()", done);
       Thread.sleep(5000);
	}

	@Given("Select Guest rating as Very Good")
	public void select_Guest_rating_as_Very_Good() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		WebElement guestRating = driver.findElementByXPath("//strong[text()='Guest rating']");
	    Actions builder1 = new Actions(driver);
	    builder1.moveToElement(guestRating).perform();
		driver.findElementByXPath("//span[text()='Very good']").click();
		Thread.sleep(5000);
	}

	@Given("Set Hotel Location as Agra Fort and click Done")
	public void set_Hotel_Location_as_Agra_Fort_and_click_Done() throws InterruptedException {
		WebElement hotelLoc = driver.findElementByXPath("//strong[text()='Hotel location']");
	    Actions builder1 = new Actions(driver);
		builder1.moveToElement(hotelLoc).perform();
		WebElement eleSite = driver.findElementById("pois");
		Select site = new Select(eleSite);
		site.selectByVisibleText("Agra Fort");
		Thread.sleep(1000);
		driver.findElementById("filter-popover-done-button").click();
		Thread.sleep(5000);
	}

	@Given("In more Filters, select Air conditioning, Restaurant and WiFi and click Done")
	public void in_more_Filters_select_Air_conditioning_Restaurant_and_WiFi_and_click_Done() throws InterruptedException {
		WebElement moreFilters = driver.findElementByXPath("//strong[text()='More filters']");
	    Actions builder1 = new Actions(driver);
	    builder1.moveToElement(moreFilters).perform();
		driver.findElementByXPath("//label[text()='Air conditioning']").click();
		driver.findElementByXPath("//label[text()='WiFi']").click();
		driver.findElementByXPath("//button[text()='Show more']").click();
		driver.findElementByXPath("//label[text()='Restaurant']").click();
		driver.findElementById("filter-popover-done-button").click();
		Thread.sleep(5000);
	}

	@Given("Sort the result as Rating & Recommended")
	public void sort_the_result_as_Rating_Recommended() throws InterruptedException {
		WebElement sorting = driver.findElementById("mf-select-sortby");
	    Select selectSort = new Select(sorting);
	    selectSort.selectByVisibleText("Rating & Recommended");
	    Thread.sleep(4000);
	}

	@When("Print the Hotel name, Rating, Number of Reviews and Click View Deal")
	public void print_the_Hotel_name_Rating_Number_of_Reviews_and_Click_View_Deal() throws InterruptedException {
		System.out.println("The hotel name is: "+ driver.findElementByXPath("(//span[@class='item-link name__copytext'])[1]").getText());
		System.out.println("The Rating of the hotel is: "+ driver.findElementByXPath("(//span[@itemprop='ratingValue'])[1]").getText());
		String review = driver.findElementByXPath("(//span[@class='details-paragraph details-paragraph--rating'])[1]").getText();
		String reviewValue = review.replaceAll("^[0-9]", "");
		System.out.println("The No of Reviews for the hotel is: " + reviewValue);
		driver.findElementByXPath("(//span[text()='View Deal'])[1]").click();
		Thread.sleep(5000);
	}

	@When("Print the URL of the Page")
	public void print_the_URL_of_the_Page() throws InterruptedException {
		Set<String> windowHandles = driver.getWindowHandles();   
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));
		System.out.println("The URL of the page is: " + driver.getCurrentUrl());
		Thread.sleep(1000);
	}

	@When("Print the Price of the Room and click Choose Your Room")
	public void print_the_Price_of_the_Room_and_click_Choose_Your_Room() throws InterruptedException {
		String price = driver.findElementByXPath("(//div[contains(@class,'bui-price-display__value prco-text-nowrap-helper')])[1]").getText();
		System.out.println("The Price of the Room is: " + price);
		driver.findElementByXPath("(//span[text()[normalize-space()='Choose your room']])[1]").click();
		Thread.sleep(6000);
	}

	@Given("Click Reserve and I'll Reserve")
	public void click_Reserve_and_I_ll_Reserve() {
		driver.findElementByXPath("(//span[@class='bui-button__text'])[1]").click();
	    driver.findElementByXPath("//div[@class='hprt-reservation-cta']").click();
	}
	
	@Then("Close the browser")
	public void closeTheBrowser() {
	    driver.quit();
	}
}
