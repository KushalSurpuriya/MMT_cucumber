package StepDefination;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import POM.GiftCard;
import POM.Hotel;
import POM.OutStationCab;
import Utility.ExcelWriteData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BaseClass {
	
	WebDriver driver=Hooks.driver;
	OutStationCab oc = new OutStationCab(driver);
	GiftCard gc = new GiftCard(driver);
	Hotel h = new Hotel(driver);
	String from = "Delhi";
	String to = "Manali, himachal Pradesh";
	String email = "unknownvmail.com";
	public static String error_msg;
	public static String lwstPrice;
	List<String> count = new ArrayList<String>();
	JavascriptExecutor js = (JavascriptExecutor)driver;
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	Actions action = new Actions(driver);
	public static Logger log;
	
	@Given("The User is on the MakeMyTrip homepage")
	public void the_user_is_on_the_make_my_trip_homepage() {
		log = LogManager.getLogger("BaseClass");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		log.debug("The user launched the webpage.....");
	}

	@When("User clicks on cab")
	public void user_clicks_on_cab() {
	    oc.click(oc.cab_Button);
	    log.debug("User clicks on cab....");
	}

	@When("User enters from and to locations")
	public void user_enters_from_and_to_locations() throws InterruptedException {
		//From Location
		oc.click(oc.from_Button);
	    oc.getTextofList(oc.fromTabs, from);
	    //To Location
	    try {
	    Thread.sleep(500);
	    //js.executeScript("arguments[0].setAttribute('value','Manali')",oc.toSearchBar);
		action.sendKeys(oc.toSearchBar, "Manali").build().perform();
	    Thread.sleep(2000);
	    oc.click(oc.toTabs);
	    }catch(Exception e) {}
	    log.debug("User enters from and to locations....");
	}

	@When("User enters date and time and click on search")
	public void user_enters_date_and_time_and_click_on_search() {
		oc.click(oc.cli_date);
		oc.click(oc.cli_time);
		oc.click(oc.cli_search);
		log.info("User enters date and time and click on search....");
	}

	@When("User selects car type as SUV")
	public void user_selects_car_type_as_suv() {
	    oc.click(oc.cli_SUV);
	    log.info("User selects car type as SUV....");
	}

	@Then("User prints the lowest car price")
	public void user_prints_the_lowest_car_price() {
		lwstPrice = oc.getText(oc.get_price);
	    System.out.println("The Lowest Price of vehicle is : "+ lwstPrice);
	    log.info("User prints the lowest car price....");
	}
	
	//Second Scenario
	
	@When("User clicks on more tab and selects Giftcard option")
	public void user_clicks_on_more_tab_and_selects_giftcard_option() {
	    gc=new  GiftCard(driver);
		gc.click(gc.cli_more);
		gc.click(gc.cli_giftcard_option);
		log.info("User clicks on more tab and selects Giftcard option....");
	}

	@When("User selects wedding gift card and selects Email option")
	public void user_selects_wedding_gift_card_and_selects_email_option() throws InterruptedException {
	    gc.click(gc.select_giftcard);
	    js.executeScript("arguments[0].click();",gc.cli_email);
	    log.info("User selects wedding gift card and selects Email option....");
	}

	@When("Scrolll to that field and enter invalid email")
	public void scrolll_to_that_field_and_enter_invalid_email() {
		js.executeScript("arguments[0].scrollIntoView()",gc.cli_email);
		gc.sendKeys(gc.send_email, email);
		log.info("Scrolll to that field and enter invalid email....");
	}

	@When("click on buy now and capture the error message")
	public void click_on_buy_now_and_capture_the_error_message() {
	    gc.click(gc.cli_buy);
	    error_msg = gc.getText(gc.get_message);
	    log.info("click on buy now and capture the error message....");
	}

	@Then("User prints the error message")
	public void user_prints_the_error_message() {
		System.out.println("The Error message is : "+ error_msg);
		log.info("User prints the error message....");
	}
	
	//Third Scenario
	
	
	@When("User clicks on Hotels tab")
	public void user_clicks_on_hotels_tab() {
		h = new Hotel(driver);
		js.executeScript("window.scrollBy(0,-550)");
		//driver.navigate().refresh();
		//h.click(h.cli_hotel);
		js.executeScript("arguments[0].click();",h.cli_hotel);
		log.info("User clicks on Hotels tab....");
	}

	@When("User clicks on guest button and then on adults")
	public void user_clicks_on_guest_button_and_then_on_adults() {		
		js.executeScript("arguments[0].click();",h.cli_guest);
		js.executeScript("arguments[0].click();",h.cli_adults);
		log.info("User clicks on guest button and then on adults....");
	}

	@Then("get the list of number of adults and print it")
	public void get_the_list_of_number_of_adults_and_print_it() throws IOException {
	    for (int i=0;i<40;i++) 
	    {
	    	count.add(h.getText(h.get_list.get(i)));
	    	System.out.println(h.getText(h.get_list.get(i)));
	    }
	    
	    ExcelWriteData.excelWrite(count, lwstPrice, error_msg);
	    log.info("get the list of number of adults and print it....");
	}
}