package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class homePage {

	protected WebDriver driver;

	private By btn_AddToCart = By.xpath("//a[text()='Add to cart']");
	private By btn_Cart = By.xpath("//a[text()='Cart']");

	public homePage(WebDriver driver) {
		this.driver = driver;
		if (!driver.getTitle().equals("Katalon Shop ? Katalon Ecommerce")) {
			throw new IllegalStateException("This is not login Page. The current page is: "+driver.getCurrentUrl());
		}
	}

	public void addRandomItemsToCart(int noOfItems) {
		try {
			List<WebElement> addToCarts=driver.findElements(btn_AddToCart);
			if (addToCarts.size()>noOfItems) {
				for (int i = 0; i < noOfItems; i++) {
					addToCarts.get(i).click();
					Thread.sleep(1000);
				}
				System.out.println("No of iteams added to cart:"+noOfItems);
			} else {
				System.err.println("Shortage of items in the page");
			}
		} catch (Exception e) {
			System.err.println("Failed in addRandomItemsToCart step...!!!");
			e.printStackTrace();
		}
	}

	public void clickonTab(String Tab) {
		try {
			System.out.println("Clicking on Tab:"+Tab);
			switch (Tab) {
			case "Cart":
				driver.findElement(btn_Cart).click();
				break;
			case "Checkout":

				break;
			case "My account":

				break;
			case "Sample Page":

				break;
			case "Shop":

				break;
			default: 
				System.err.println("Tab which you have entered is not present");
				break;
			}

		} catch (Exception e) {
			System.err.println("Failed in clickonTab step...!!!");
			e.printStackTrace();
		}
	}

}
