package UItest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class Overview {
    String BaseURL ="https://www.saucedemo.com/v1/inventory.html";
    WebDriver driver;
    WebElement cartno;
    String cartitem ="";
    WebElement invItemname;
    String pvitemname ="";

    @BeforeTest
    public void BeforeTestMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);
        WebElement additem = driver.findElement(By.xpath("//*[@id='inventory_container']/div/div[1]/div[3]/button"));
        additem.click();
        driver.get("https://www.saucedemo.com/v1/cart.html");
        cartno = driver.findElement(By.id("shopping_cart_container"));
        cartitem=cartno.getText();
        invItemname =driver.findElement(By.className("inventory_item_name"));
        pvitemname = invItemname.getText();
        System.out.println("Previous page selected item : "+pvitemname);
        WebElement Checkout =driver.findElement(By.xpath("//*[@id='cart_contents_container']/div/div[2]/a[2]"));
        Checkout.click();
        driver.get("https://www.saucedemo.com/v1/checkout-step-one.html");
        WebElement firstname = driver.findElement(By.id("first-name"));
        WebElement lastname = driver.findElement(By.id("last-name"));
        WebElement postalcode = driver.findElement(By.id("postal-code"));
        WebElement continuebtu =driver.findElement(By.className("btn_primary"));
        //Trigger the action
        String Firstname_txt = "Ranudi";
        String lastnamw_txt ="Pitigala";
        String postalcode_txt ="10300";
        firstname.sendKeys(Firstname_txt);
        lastname.sendKeys(lastnamw_txt);
        postalcode.sendKeys(postalcode_txt);
        continuebtu.click();
    }
    @Test(priority = 1)
    public void selectheader(){
        System.out.println("The title of the Swag Labs page is : "+driver.getTitle());
        WebElement product =driver.findElement(By.className("subheader"));
        String subtitle = product.getText();
        System.out.println("Page sub title :"+ subtitle);
        Assert.assertEquals(subtitle,"Checkout: Overview","Text inside the element is incorrect!");

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
    public void testcartitemno(){
        WebElement Currentcartno = driver.findElement(By.id("shopping_cart_container"));
        String currentitem = Currentcartno.getText();
        System.out.println("Previous Cart no :"+ cartitem +"  current cart no : "+currentitem);
        Assert.assertEquals(cartitem,currentitem,"Item number does not match!");

    }
    @Test(priority = 6)
    public void testcartqty(){
        WebElement cartno = driver.findElement(By.id("shopping_cart_container"));
        List<WebElement> qtyElements = driver.findElements(By.className("summary_quantity"));
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
    @Test(priority = 7)
    public void testItemname()throws InterruptedException{

        WebElement Itemname = driver.findElement(By.className("inventory_item_name"));
        System.out.println("Inventry item Name  :"+Itemname.getText());
        Assert.assertEquals(Itemname.getText(),pvitemname,"Invalid item Name");
        Itemname.click();
        Thread.sleep(2000);
        driver.navigate().back();
    }
    @Test (priority = 8)
    public void testPriceformat(){
        WebElement priceElement = driver.findElement(By.className("inventory_item_price"));

        // Get the price text
        String priceText = priceElement.getText();
        System.out.println("Extracted Price: " + priceText); // Debugging

        // Regular Expression to match "$XX.XX" format
        String pricePattern = "^\\$\\d+\\.\\d{2}$";

        // Validate the price format
        Assert.assertTrue( priceText.matches(pricePattern),"Price format is incorrect!");
    }
    //Validate Payment Information
    @Test(priority = 9)
    public void testPayinfor(){
        WebElement payinfor = driver.findElement(By.className("summary_value_label"));
        String Actualpayinfor = payinfor.getText();
        String expectedPayinfor = "SauceCard #31337";
        Assert.assertEquals(Actualpayinfor,expectedPayinfor,"Payment information is incorrect");
    }
    @Test(priority = 10)
    public void testShippinginfor(){
        WebElement shipinfor = driver.findElement(By.xpath("//*[@id='checkout_summary_container']/div/div[2]/div[4]"));
        String actShipinfor =shipinfor.getText();
        String expShipinfor = "FREE PONY EXPRESS DELIVERY!";
        Assert.assertEquals(actShipinfor,expShipinfor,"Shipping information is incorrect !");
    }
    @Test(priority = 11)
    public void testItemtotal(){
        List<WebElement> Itemprice = driver.findElements(By.className("inventory_item_price"));
        double calculatedTotal = 0.0;
// Loop through each item price and sum up
        for (WebElement priceElement : Itemprice) {
            String priceText = priceElement.getText().replace("$", ""); // Remove "$"
            double price = Double.parseDouble(priceText);
            calculatedTotal += price;
        }

        // Get the displayed Item Total
        WebElement itemTotalElement = driver.findElement(By.className("summary_subtotal_label"));
        String displayedTotalText = itemTotalElement.getText().replace("Item total: $", "");
        double displayedTotal = Double.parseDouble(displayedTotalText);

        // Validate the calculated total matches the displayed total
        Assert.assertEquals(calculatedTotal, displayedTotal, "Item Total mismatch!");

    }

    @Test(priority = 12)
    public void testTotalpriceformat(){
        WebElement priceElement = driver.findElement(By.className("summary_subtotal_label"));

        // Get the price text
        String priceText = priceElement.getText();
        System.out.println("Item total Price: " + priceText); // Debugging

        // Regular Expression to match "$XX.XX" format
        String pricePattern = "Item total: \\$\\d+\\.\\d{2}";


        // Validate the price format
        Assert.assertTrue( priceText.matches(pricePattern),"Item total format is incorrect!");
    }
    @Test(priority = 13)
    public void testTaxcalulation(){
        // Get the displayed Item Total
        WebElement itemTotalElement = driver.findElement(By.className("summary_subtotal_label"));
        String itemTotalText = itemTotalElement.getText().replace("Item total: $", "");
        double itemTotal = Double.parseDouble(itemTotalText);
        System.out.println("Item total Taxcalulation-->"+ itemTotal);

        // Get the displayed Tax
        WebElement taxElement = driver.findElement(By.className("summary_tax_label"));
        String taxText = taxElement.getText().replace("Tax: $", "");
        double displayedTax = Double.parseDouble(taxText);
        System.out.println("Tax Taxcalulation-->"+ displayedTax);

        // Calculate expected tax
        double expectedTax = Math.round((itemTotal * 0.08) * 100.0) / 100.0; // Round to 2 decimal places

        // Assert that displayed tax matches expected tax
        Assert.assertEquals(displayedTax, expectedTax, "Tax calculation is incorrect!");

    }
    @Test(priority = 14)
    public void testTotalamount(){
        // Get the displayed Item Total
        WebElement itemTotalElement = driver.findElement(By.className("summary_subtotal_label"));
        String itemTotalText = itemTotalElement.getText().replace("Item total: $", "");
        double itemTotal = Double.parseDouble(itemTotalText);
        System.out.println("Item total Totalamount-->"+ itemTotal);

        // Get the displayed Tax
        WebElement taxElement = driver.findElement(By.className("summary_tax_label"));
        String taxText = taxElement.getText().replace("Tax: $", "");
        double tax = Double.parseDouble(taxText);
        System.out.println("Tax Totalamount-->"+ tax);

        // Get the displayed Total
        WebElement totalElement = driver.findElement(By.className("summary_total_label"));
        String totalText = totalElement.getText().replace("Total: $", "");
        double displayedTotal = Double.parseDouble(totalText);
        System.out.println("Total Totalamount-->"+ displayedTotal);

     // Calculate expected total
        double expectedTotal = Math.round((itemTotal + tax) * 100.0) / 100.0; // Round to 2 decimal places

        // Assert that displayed total matches expected total
        Assert.assertEquals(displayedTotal, expectedTotal, "Total calculation is incorrect!");

    }
    @Test(priority = 15)
    public void testCancel()throws InterruptedException{
        WebElement butCancel = driver.findElement(By.className("cart_cancel_link"));
        butCancel.click();
        Thread.sleep(2000);
        driver.navigate().back();
    }
    @Test(priority = 16)
    public void testFinish()throws InterruptedException{
        WebElement butFinish =driver.findElement(By.className("btn_action"));
        butFinish.click();
        Thread.sleep(2000);
        driver.navigate().back();
    }
    @AfterTest
    public void Aftertest(){
        //driver.quit();
        System.out.println("Clearing test data after execution...");
    }
}
