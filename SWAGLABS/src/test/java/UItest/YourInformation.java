package UItest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class YourInformation {
    String BaseURL ="https://www.saucedemo.com/v1/inventory.html";
    WebDriver driver;
    WebElement cartno;
    String cartitem ="";
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
        WebElement Checkout =driver.findElement(By.xpath("//*[@id='cart_contents_container']/div/div[2]/a[2]"));
        Checkout.click();
        driver.get("https://www.saucedemo.com/v1/checkout-step-one.html");
    }
    @Test (priority = 1)
    public void selectheader(){
        System.out.println("The title of the Swag Labs page is : "+driver.getTitle());
        WebElement product =driver.findElement(By.className("subheader"));
        String subtitle = product.getText();
        System.out.println("Page sub title :"+ subtitle);
        Assert.assertEquals(subtitle,"Checkout: Your Information","Text inside the element is incorrect!");

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
    public void cartqty(){
        WebElement Currentcartno = driver.findElement(By.id("shopping_cart_container"));
        String currentitem = Currentcartno.getText();
        System.out.println("Previous Cart no :"+ cartitem +"  current cart no : "+currentitem);
Assert.assertEquals(cartitem,currentitem,"Item number does not match!");

    }
    @Test(priority = 6)
    public void testCancel() throws InterruptedException{
        WebElement btuCancel =driver.findElement(By.xpath("//*[@id='checkout_info_container']/div/form/div[2]/a"));
        btuCancel.click();
        Thread.sleep(2000);
        driver.navigate().back();


    }
    @Test(priority = 7)
    public void testPlaceholderText(){
        WebElement firstname = driver.findElement(By.id("first-name"));
        WebElement lastname = driver.findElement(By.id("last-name"));
        WebElement postalcode = driver.findElement(By.id("postal-code"));
        // Get the placeholder attribute value
        String actFnPlaceholder = firstname.getAttribute("placeholder");
        String actLnPlaceholder = lastname.getAttribute("placeholder");
        String actPcPlaceholder = postalcode.getAttribute("placeholder");

        // Define the expected placeholder text
        String exFNPlaceholder = "First Name";
        String exLNPlaceholder = "Last Name";
        String exPCPlaceholder ="Zip/Postal Code";

        System.out.println("Placeholder First name : " +actFnPlaceholder + " ---Last Name : "+ actLnPlaceholder + "--Postal Code--"+ actPcPlaceholder);
        // Verify that the placeholder text is correct
        Assert.assertEquals(actFnPlaceholder, exFNPlaceholder, " First name Placeholder text is incorrect!");
        Assert.assertEquals(actLnPlaceholder, exLNPlaceholder, "Last name Placeholder text is incorrect!");
        Assert.assertEquals(actPcPlaceholder, exPCPlaceholder, "Postal Code Placeholder text is incorrect!");
    }
    @Test (priority = 8)
    public void testNulltext(){
        WebElement firstname = driver.findElement(By.id("first-name"));
        WebElement lastname = driver.findElement(By.id("last-name"));
        WebElement postalcode = driver.findElement(By.id("postal-code"));
        WebElement continuebtu =driver.findElement(By.className("btn_primary"));
        //Trigger the action
        String Firstname_txt = "";
        String lastnamw_txt ="";
        String postalcode_txt ="";
        firstname.sendKeys(Firstname_txt);
        lastname.sendKeys(lastnamw_txt);
        postalcode.sendKeys(postalcode_txt);
        continuebtu.click();
        String ActError = driver.findElement(By.xpath("//*[@id='checkout_info_container']/div/form/h3")).getText();
        String ExpectError ="Error: First Name is required";
        System.out.println("Error message : "+ ActError);

        Assert.assertEquals(ActError, ExpectError, "Error message mismatch!");
    }
    @Test(priority = 9)
    public void testLastnameblank()throws InterruptedException{
        Thread.sleep(1000);
        WebElement firstname = driver.findElement(By.id("first-name"));
        WebElement lastname = driver.findElement(By.id("last-name"));
        WebElement postalcode = driver.findElement(By.id("postal-code"));
        WebElement continuebtu =driver.findElement(By.className("btn_primary"));
        //Trigger the action
        String Firstname_txt = "Ranudi";
        String lastnamw_txt ="";
        String postalcode_txt ="";
        firstname.sendKeys(Firstname_txt);
        lastname.sendKeys(lastnamw_txt);
        postalcode.sendKeys(postalcode_txt);
        continuebtu.click();
        String ActError = driver.findElement(By.xpath("//*[@id='checkout_info_container']/div/form/h3")).getText();
        String ExpectError ="Error: Last Name is required";
        System.out.println("Error message : "+ ActError);

        Assert.assertEquals(ActError, ExpectError, "Error message mismatch!");
    }

    @Test(priority = 10)
    public void testpostalblank() throws InterruptedException{
        Thread.sleep(1000);
        WebElement firstname = driver.findElement(By.id("first-name"));
        WebElement lastname = driver.findElement(By.id("last-name"));
        WebElement postalcode = driver.findElement(By.id("postal-code"));
        WebElement continuebtu =driver.findElement(By.className("btn_primary"));
// Clear first Name
        firstname.sendKeys(Keys.CONTROL+"a");
        firstname.sendKeys(Keys.BACK_SPACE);

        //Trigger the action
        String Firstname_txt = "Ranudi";
        String lastnamw_txt ="Pitigala";
        String postalcode_txt ="";
        firstname.sendKeys(Firstname_txt);
        lastname.sendKeys(lastnamw_txt);
        postalcode.sendKeys(postalcode_txt);
        continuebtu.click();
        String ActError = driver.findElement(By.xpath("//*[@id='checkout_info_container']/div/form/h3")).getText();
        String ExpectError ="Error: Postal Code is required";
        System.out.println("Error message : "+ ActError);

        Assert.assertEquals(ActError, ExpectError, "Error message mismatch!");
    }
    @Test(priority = 11)
    public void testHappypath() throws InterruptedException{
        Thread.sleep(1000);
        WebElement firstname = driver.findElement(By.id("first-name"));
        WebElement lastname = driver.findElement(By.id("last-name"));
        WebElement postalcode = driver.findElement(By.id("postal-code"));
        WebElement continuebtu =driver.findElement(By.className("btn_primary"));
// Clear first Name
        firstname.sendKeys(Keys.CONTROL+"a");
        firstname.sendKeys(Keys.BACK_SPACE);
        //clear Last name
        lastname.sendKeys(Keys.CONTROL+"a");
        firstname.sendKeys(Keys.BACK_SPACE);

        //Trigger the action
        String Firstname_txt = "Ranudi";
        String lastnamw_txt ="Pitigala";
        String postalcode_txt ="10300";
        firstname.sendKeys(Firstname_txt);
        lastname.sendKeys(lastnamw_txt);
        postalcode.sendKeys(postalcode_txt);
        continuebtu.click();
        Thread.sleep(3000);
        driver.navigate().back();
    }
    @AfterTest
    public void Aftertest(){
        //driver.quit();
        System.out.println("Clearing test data after execution...");
    }
}
