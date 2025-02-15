package UItest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Products {
    String BaseURL ="https://www.saucedemo.com/v1/inventory.html";
    WebDriver driver;

    @BeforeTest
    public void BeforeTestMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);
    }
    // Verify page Title
    @Test(priority = 1)
    public void GetPageTitle() throws InterruptedException{
        Thread.sleep(3000);
        //Verify page Title
        System.out.println("The title of the Swag Labs page is : "+driver.getTitle());

    }
    // Verify page sub title
    @Test(priority = 2)
    public void testClassText(){
    WebElement product =driver.findElement(By.className("product_label"));
    //Validate class name
        String actualClass = product.getAttribute("class");
        Assert.assertTrue(actualClass.contains("product_label"), "Class name is incorrect!");
  // Validate inner text
        String actualText = product.getText().trim();
        System.out.println("Sub Title : "+ actualText );
        Assert.assertEquals(actualText, "Products", "Text inside the element is incorrect!");

    }
    //Check logo image is correct
    @Test(priority = 2)
    public void testLogoimage(){
        // Verify Logo image
        WebElement element = driver.findElement(By.className("app_logo"));
        String bgImage = element.getCssValue("background-image");

        System.out.println("Background Image: " + bgImage);


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
    // add one item
    @Test(priority = 4)
    public void testaddtocart(){
        WebElement additem = driver.findElement(By.xpath("//*[@id='inventory_container']/div/div[1]/div[3]/button"));
        additem.click();
    }
    //Check menu is available
    @Test(priority = 5)
    public void testMenuvisibility(){
        WebElement sideMenu = driver.findElement(By.className("bm-burger-button"));

        Assert.assertTrue(sideMenu.isDisplayed(),"Menu icon is not visible");
        Assert.assertTrue(sideMenu.isEnabled(),"Menu icon is not clickable");
    }
//check sorting option is available
    @Test(priority = 6)
    public void testSortcontainer() {

        WebElement Sortcontainer = driver.findElement(By.className("product_sort_container"));

        Assert.assertTrue(Sortcontainer.isDisplayed(),"Sort container is not visible");
    }
    //Check inventory items are display properly
@Test(priority = 7)
public void testInventryitem(){
        List<WebElement> intItems = driver.findElements(By.className("inventory_list"));

    Assert.assertFalse(intItems.isEmpty(), "Inventory list should not be empty!");
    // Print item names for debugging
    for (WebElement item : intItems) {
        System.out.println("Item Name: " + item.getText());

    }
}
    //Check inventory name and details are display properly
@Test(priority = 8)
    public void testItemname(){
        List<WebElement> intItems = driver.findElements(By.className("inventory_item"));
    int itemsize = intItems.size();
    System.out.println("Item size: " + itemsize);
    for (int i = 0; i < itemsize; i++) {
        String itemname = intItems.get(i).getText();
        WebElement invname =driver.findElement(By.className("inventory_item_name"));
        String outer = intItems.get(i).getAttribute("innerHTML");
        System.out.println("Item "+ i + "  ---  "+ itemname );
        //System.out.println("href "+ i + "  ---  "+ outer );

    }
    }
    //Check inventory item images are display properly
    @Test(priority = 9)
    public void testItemimg(){
        List<WebElement> intItems = driver.findElements(By.className("inventory_item"));
        int itemsize = intItems.size();
        System.out.println("Item size: " + itemsize);
        for (int i = 0; i < itemsize; i++) {
            String itemname = intItems.get(i).getText();
            WebElement invname = driver.findElement(By.className("inventory_item_name"));
            String innerHTML = intItems.get(i).getAttribute("innerHTML");

            System.out.println("href " + i + "  ---  " + innerHTML);
        }

    }
    //Check inventory item price format
    @Test(priority = 10)
    public void testItemprice(){
        List<WebElement> intItems = driver.findElements(By.className("inventory_item"));
        int itemsize = intItems.size();
        System.out.println("Item size: " + itemsize);
        for (int i = 0; i < itemsize; i++) {
            String itemname = intItems.get(i).getText();
            WebElement invname = driver.findElement(By.className("inventory_item_label"));
            String innerHTML = intItems.get(i).getAttribute("innerHTML");
            String pricePattern = "^\\$\\d+\\.\\d{2}$";

            WebElement itemprice = driver.findElement(By.className("inventory_item_price"));
            String itprice = itemprice.getText();
            System.out.println("Item price  " + i + "  ---  " + itprice);
            // Validate the price format
            Assert.assertTrue( itprice.matches(pricePattern),"Price format is incorrect!");
        }
    }
@Test(priority = 11)
    public void testCartcount(){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement cartSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shopping_cart_container']/a/span")));

    String cartText = cartSpan.getText();
    System.out.println("Cart count: " + cartText);
    Assert.assertFalse(cartText.isEmpty(), "Cart count is missing!");
    }
@Test(priority = 12)
    public void testNoofitem(){
        List<WebElement> Removeitem =driver.findElements(By.className("btn_secondary"));
        WebElement shoppingcart =driver.findElement(By.id("shopping_cart_container"));
        int removesize = Removeitem.size();
        int cartno = Integer.parseInt(shoppingcart.getText());
        System.out.println("Remove button size : "+removesize+ "Shopping Cart count : "+ shoppingcart.getText());
        Assert.assertEquals(removesize,cartno,"Shopping cart number does not match with selected items");
    }
    @Test(priority = 13)
    public void testnavigtation() throws InterruptedException {
        // Verify navigation links
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> links = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".inventory_item_img a")));

        int itemsize = links.size();

        System.out.println("Item Images size :"+itemsize);
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            System.out.println("Link: " + href);
            driver.get(href);

            Thread.sleep(1000);
            driver.navigate().back();
        }
    }
    @Test(priority = 14)
    public void testCartnonextpage() throws InterruptedException{
       WebElement shoppingcart =driver.findElement(By.id("shopping_cart_container"));
        int maincartno = Integer.parseInt(shoppingcart.getText());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> links = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".inventory_item_img a")));

        int itemsize = links.size();

        System.out.println("Item Images size :"+itemsize);
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            System.out.println("Link: " + href);
            driver.get(href);
            Thread.sleep(1000);
            WebElement nextshcart =driver.findElement(By.id("shopping_cart_container"));
            int subcartno = Integer.parseInt(nextshcart.getText());
            System.out.println("Cart Amount Before Navigation: " + maincartno);
            System.out.println("Cart Amount After Navigation: " + subcartno);

            Assert.assertEquals(subcartno, maincartno, "Cart amount is incorrect!");

            driver.navigate().back();
        }

    }
    @Test(priority = 15)
    public void testshoppingcart() throws InterruptedException{
        WebElement maincart =driver.findElement(By.id("shopping_cart_container"));
        int maincartno = Integer.parseInt(maincart.getText());
        System.out.println("Cart Amount Before Navigation: " + maincartno);
        maincart.click();
        Thread.sleep(1000);
        WebElement nextcart =driver.findElement(By.id("shopping_cart_container"));
        int subcartno = Integer.parseInt(nextcart.getText());
        System.out.println("Cart Amount Before Navigation: " + maincartno);
        System.out.println("Cart Amount in the cart page : " + subcartno);

        Assert.assertEquals(subcartno, maincartno, "Cart amount is incorrect!");
        driver.navigate().back();
    }
    // test menu items are available
    @Test(priority = 16)
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
        WebElement allitem = driver.findElement(By.xpath("//*[@id=\"inventory_sidebar_link\"]"));
        allitem.click();
        Thread.sleep(2000);
    }
    @Test(priority = 17)
    public void testCartadding() throws InterruptedException{

        WebElement additem2 =driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[3]/div[3]/button"));
        additem2.click();
        WebElement cart1 =driver.findElement(By.id("shopping_cart_container"));
        System.out.println("Cart item no : "+ cart1.getText());
        driver.get("https://www.saucedemo.com/v1/inventory-item.html?id=0");
        Thread.sleep(3000);
        WebElement additem3 =driver.findElement(By.xpath("//*[@id='inventory_item_container']/div/div/div/button"));
        additem3.click();
        WebElement cart2 =driver.findElement(By.id("shopping_cart_container"));
        int subcartno = Integer.parseInt(cart2.getText());
        System.out.println("Next page cart number"+subcartno);
        Thread.sleep(3000);
        driver.navigate().back();
        WebElement cart3 =driver.findElement(By.id("shopping_cart_container"));
        WebElement butremove =driver.findElement(By.xpath("//*[@id='inventory_container']/div/div[2]/div[3]/button"));
        int cartno2 = Integer.parseInt(cart3.getText());
        String butname = butremove.getText();

        System.out.println("back main page cart number"+cartno2 +" button- "+ butname);

        Assert.assertEquals(cartno2,subcartno,"Cart item number is not match");
        Assert.assertEquals("REMOVE",butname,"The Button text does not match!");


    }
    @AfterTest
public void Aftertest(){
        //driver.quit();
        System.out.println("Clearing test data after execution...");
}

}
