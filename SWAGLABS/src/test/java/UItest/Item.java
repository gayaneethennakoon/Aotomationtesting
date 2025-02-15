package UItest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Item {
    String BaseURL ="https://www.saucedemo.com/v1/inventory-item.html?id=4";
    WebDriver driver;

    @BeforeTest
    public void BeforeTestMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);
    }
    @Test(priority = 1)
    public void GetPageTitle() throws InterruptedException{
        Thread.sleep(3000);
        //Verify page Title
        System.out.println("The title of the Swag Labs page is : "+driver.getTitle());

    }
    @Test(priority = 2)
    public void testLogoimage() {
        // Verify Logo image
         WebElement element = driver.findElement(By.className("header_label"));
         String bgImage = element.getCssValue("background-image");

        System.out.println("Background Image: " + bgImage);


        // Verify it contains the correct image URL
        Assert.assertTrue(bgImage.contains("SwagLabs_logo.png"),"Background image URL is incorrect");


    }
    @Test(priority = 3)
    public void testShoppingcart(){
        WebElement shoppingcart = driver.findElement(By.id("shopping_cart_container"));

        Assert.assertTrue(shoppingcart.isDisplayed(), "Cart icon is not visible!");
        Assert.assertTrue(shoppingcart.isEnabled(),"Cart icon is not clickable!");
    }
    @Test(priority = 4)
    public void testMenuvisibility(){
        WebElement sideMenu = driver.findElement(By.className("bm-burger-button"));

        Assert.assertTrue(sideMenu.isDisplayed(),"Menu icon is not visible");
        Assert.assertTrue(sideMenu.isEnabled(),"Menu icon is not clickable");
    }
@Test(priority = 5)
    public void testPriceformat(){
        WebElement priceElement = driver.findElement(By.className("inventory_details_price"));

        // Get the price text
        String priceText = priceElement.getText();
        System.out.println("Extracted Price: " + priceText); // Debugging

        // Regular Expression to match "$XX.XX" format
        String pricePattern = "^\\$\\d+\\.\\d{2}$";

        // Validate the price format
        Assert.assertTrue( priceText.matches(pricePattern),"Price format is incorrect!");
    }
    @Test(priority = 6)
    public void testAddtocart(){

        WebElement shoppingcartold =driver.findElement(By.id("shopping_cart_container"));
        int scno =0;
        WebElement additem = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div/button"));
        if (shoppingcartold.getText().equals("")) {

            System.out.println("Cart = "+ scno);

        } else {
            System.out.println("Cart ="+shoppingcartold.getText());

        }
        additem.click();
        WebElement shoppingcart =driver.findElement(By.id("shopping_cart_container"));

        int cartno = Integer.parseInt(shoppingcart.getText());
        System.out.println("Shopping cart count :"+cartno);
Assert.assertEquals(1,cartno-scno,"Item not added to cart");


    }
    @AfterTest
    public void Aftertest(){
        //driver.quit();
        System.out.println("Clearing test data after execution...");
    }

}
