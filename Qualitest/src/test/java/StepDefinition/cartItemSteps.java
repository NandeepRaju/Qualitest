package StepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Pages.cartPage;
import Pages.homePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class cartItemSteps {
	
	WebDriver driver;
	homePage home;
	cartPage cart;
	
	@Before
	public void browserSetup() {
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is: "+ projectPath);
		System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}
		
	@Given("I add {int} random items to my cart")
	public void i_add_random_items_to_my_cart(Integer int1) {
		driver.get("https://cms.demo.katalon.com/");
		home= new homePage(driver);
		home.addRandomItemsToCart(4);
	}

	@When("I view my cart")
	public void i_view_my_cart() {
		home.clickonTab("Cart");
		cart=new cartPage(driver);
	}

	@Then("I found total {int} items in my cart")
	public void i_found_total_items_in_my_cart(Integer int1) {
		cart.itemsAtCartValidation(int1);
	}

	@Then("I serach for lowest price item")
	public void i_serach_for_lowest_price_item() {
		cart.lowestPriceItemInCart();
	}

	@Then("I am able to remove the lowest price item from my cart")
	public void i_am_able_to_remove_the_lowest_price_item_from_my_cart() {
		cart.removeItemFromCart(cart.lowestPriceProductName);
		}

	@Then("I am able to verify {int} items in my cart")
	public void i_am_able_to_verify_items_in_my_cart(Integer int1) {
		cart.itemsAtCartValidation(int1);
	}

}
