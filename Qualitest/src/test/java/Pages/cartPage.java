package Pages;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class cartPage {

	protected WebDriver driver;
	public String lowestPriceProductName;
	protected int lowestPriceProductIndex;
	private By clm_ItemsAtCart = By.xpath("//tr[@class='woocommerce-cart-form__cart-item cart_item']");
	private By txt_ItemPrice = By.xpath("//td[@class='product-price']/span");
	private By lnk_ProductName = By.xpath("//td[@class='product-name']/a");
	private By btn_RemoveProduct = By.xpath("//td[@class='product-remove']/a");
	
	


	public cartPage(WebDriver driver) {
		this.driver = driver;
		if (!driver.getTitle().equals("Cart ? Katalon Shop")) {
			throw new IllegalStateException("This is not login Page. The current page is: "+driver.getCurrentUrl());
		}
	}

	public void itemsAtCartValidation(int noOfItems) {
		try {
			if (driver.findElements(clm_ItemsAtCart).size() == noOfItems) {
				System.out.println("No of Items in cart:"+driver.findElements(clm_ItemsAtCart).size());
				assertTrue(true);
			} else {
				assertTrue("Items in the cart didnot match", false);
			}
		} catch (Exception e) {
			System.err.println("Failed in itemsAtCartValidation step...!!!");
			e.printStackTrace();
		}
	}

	public void lowestPriceItemInCart() {
		try {
			List<WebElement> itemPriceEle= driver.findElements(txt_ItemPrice);
			List<WebElement> itemProductNameEle= driver.findElements(lnk_ProductName);
			List<Double> itemPrice = new ArrayList<Double>();
			for (WebElement price : itemPriceEle) {
				String txtPrice = price.getText().substring(1);
				itemPrice.add(Double.valueOf(txtPrice));
			}
			double lowestInt=0;
			lowestPriceProductIndex=0;
			for (int i = 1; i < itemPrice.size(); i++) {
				lowestInt=itemPrice.get(0);
				if (lowestInt > itemPrice.get(i)) {
					lowestInt = itemPrice.get(i);
					lowestPriceProductIndex = i;
				}
			}
			lowestPriceProductName = itemProductNameEle.get(lowestPriceProductIndex).getText();
			System.out.println("Lowest Price Product Name:"+lowestPriceProductName);
		} catch (Exception e) {
			System.err.println("Failed in lowestPriceItemInCart step...!!!");
			e.printStackTrace();
		}
	}

	public void removeItemFromCart(String Item) {
		try {
			List<WebElement> itemProductNameEle= driver.findElements(lnk_ProductName);
			List<WebElement> productRemoveEle= driver.findElements(btn_RemoveProduct);
			for (int i = 0; i < itemProductNameEle.size(); i++) {
				if (itemProductNameEle.get(i).getText().equalsIgnoreCase(Item)) {
					productRemoveEle.get(i).click();
					System.out.println("Item removed from the cart:"+Item);
					Thread.sleep(1000);
					break;
				} else {
					System.err.println("Product name didnot match");
				}	
			}
		} catch (Exception e) {
			System.err.println("Failed in removeItemFromCart step...!!!");
			e.printStackTrace();
		}
	
	}
}
