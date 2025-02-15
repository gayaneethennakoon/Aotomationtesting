package UItest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    String BaseURL ="https://www.saucedemo.com/v1/inventory.html";
    WebDriver driver;
    @BeforeTest
    public void BeforeTestMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);
        WebElement additem = driver.findElement(By.xpath("//*[@id='inventory_container']/div/div[1]/div[3]/button"));
        additem.click();
        driver.get("https://www.saucedemo.com/v1/cart.html");
    }
    //Select a item from the inventory list
    @Test(priority = 1)
    public void selectheader(){
        System.out.println("The title of the Swag Labs page is : "+driver.getTitle());
        WebElement product =driver.findElement(By.className("subheader"));
        String subtitle = product.getText();
        System.out.println("Page sub title :"+ subtitle);
        Assert.assertEquals(subtitle,"Your Cart","Text inside the element is incorrect!");

    }
    @Test(priority = 2)
    public void testLogoimage() {
        // Verify Logo image
        WebElement element = driver.findElement(By.className("app_logo"));
        String bgImage = element.getCssValue("background-image");

        System.out.println("Logo background Image: " + bgImage);

        // Verify it contains the correct image URL
        Assert.assertTrue(bgImage.contains("SwagLabs_logo.png"),"Background image URL is incorrect");


    }
    //Check shopping cart available
    @Test(priority = 3)
    public void testShoppingcart(){
        WebElement shoppingcart = driver.findElement(By.id("shopping_cart_container"));

        Assert.assertTrue(shoppingcart.isDisplayed(), "Cart icon is not visible!");
        Assert.assertTrue(shoppingcart.isEnabled(),"Cart icon is not clickable!");
    }
    //Check menu is available
    @Test(priority = 4)
    public void testMenuvisibility(){
        WebElement sideMenu = driver.findElement(By.className("bm-burger-button"));

        Assert.assertTrue(sideMenu.isDisplayed(),"Menu icon is not visible");
        Assert.assertTrue(sideMenu.isEnabled(),"Menu icon is not clickable");
    }
    // test menu items are available
    @Test(priority = 5)
    public void testsidemenu() throws InterruptedException{
        WebElement menuitem = driver.findElement(By.className("bm-burger-button"));
        menuitem.click();
        Thread.sleep(2000);
        // Find all menu items
        List<WebElement> menuElements = driver.findElements(By.className("bm-item"));
        System.out.println("Menu size : "+menuElements.size());
        // Extract text from menu items
        List<String> actualMenuItems = new ArrayList<>();
        for (WebElement item : menuElements) {
            actualMenuItems.add(item.getText().trim());
            System.out.println(actualMenuItems);
        }
        // Expected menu items list
        List<String> expectedMenuItems = List.of("All Items", "About", "Logout", "Reset App State");

        // Validate menu items
        Assert.assertEquals(actualMenuItems, expectedMenuItems, "Menu items do not match!");

        System.out.println("✅ Menu validation passed!");
        Thread.sleep(2000);
        WebElement allitem = driver.findElement(By.xpath("//*[@id=\"inventory_sidebar_link\"]"));
        allitem.click();
        driver.get("https://www.saucedemo.com/v1/cart.html");

    }
   // @Test(priority = 6)
    public void cartqty(){
        WebElement cartno = driver.findElement(By.id("shopping_cart_container"));
        List<WebElement> qtyElements = driver.findElements(By.className("cart_quantity"));
       // int cartno1 = Integer.parseInt(cartno.getText());
        int QTY1 = qtyElements.size();
        int cartno1 = 0;

        if (cartno.getText().equals("")) {

            System.out.println("Cart no = "+cartno1+ "  QTY  "+ QTY1);
            Assert.assertEquals(cartno1,QTY1,"Cart items no does not match with QTY");

        } else {
            System.out.println("Cart no ="+cartno.getText()+"  QTY  "+QTY1);
            Assert.assertEquals(Integer.parseInt(cartno.getText()),QTY1,"Cart items no does not match with QTY");

        }


    }
    @Test(priority = 6)
public void testItemname()throws InterruptedException{
        cartqty();
        WebElement Itemname = driver.findElement(By.className("inventory_item_name"));
        System.out.println("Inventry item Name  :"+Itemname.getText());
        Itemname.click();
        Thread.sleep(2000);
        driver.navigate().back();
}
//Verify price format

@Test(priority = 7)
    public void testPrformat(){
        WebElement priceElement = driver.findElement(By.className("inventory_item_price"));

        // Get the price text
        String priceText = priceElement.getText();
        System.out.println("Extracted Price: " + priceText); // Debugging

        // Regular Expression to match "$XX.XX" format
        String pricePattern = "^\\$\\d+\\.\\d{2}$";

        // Validate the price format
        Assert.assertTrue( priceText.matches(pricePattern),"Price format is incorrect!");
    }
    //Test continue shopping button
    @Test(priority = 8)
    public void testcontinueShoppingbut() throws InterruptedException{
        WebElement conshop =driver.findElement(By.xpath("//*[@id='cart_contents_container']/div/div[2]/a[1]"));
        conshop.click();
        Thread.sleep(2000);
        driver.navigate().back();
    }
//Test Remove button
    @Test(priority = 9)
    public void testRemovebut()throws InterruptedException{
        WebElement removebut = driver.findElement(By.xpath("//*[@id='cart_contents_container']/div/div[1]/div[3]/div[2]/div[2]/button"));
        removebut.click();
        Thread.sleep(2000);
        WebElement cartno = driver.findElement(By.id("shopping_cart_container"));
        int cartno1 = 0;
        if (cartno.getText().equals("")) {

            System.out.println("Cart = "+ cartno1);

        } else {
            System.out.println("Cart ="+cartno.getText());

        }
        cartqty();
    }

    @Test(priority = 10)
    public void testCheckout()throws InterruptedException{
        WebElement Checkout =driver.findElement(By.xpath("//*[@id='cart_contents_container']/div/div[2]/a[2]"));
        Checkout.click();
        Thread.sleep(2000);
        driver.navigate().back();

    }
    @AfterTest
    public void Aftertest(){
        //driver.quit();
        System.out.println("Clearing test data after execution...");
    }
}
