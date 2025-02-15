package UItest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Finish {
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
        WebElement butFinish = driver.findElement(By.xpath("//*[@id='checkout_summary_container']/div/div[2]/div[8]/a[2]"));
        butFinish.click();
    }
    @Test(priority = 1)
    public void selectheader(){
        System.out.println("The title of the Swag Labs page is : "+driver.getTitle());
        WebElement product =driver.findElement(By.className("subheader"));
        String subtitle = product.getText();
        System.out.println("Page sub title :"+ subtitle);
        Assert.assertEquals(subtitle,"Finish","Text inside the element is incorrect!");

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
        System.out.println("Cart no :"+ currentitem);
        Assert.assertEquals("",currentitem,"Item number does not match!");

    }

    @Test(priority = 6)
    public void msgThankyou(){
        WebElement Thankyou =driver.findElement(By.className("complete-header"));
        String expMessage ="THANK YOU FOR YOUR ORDER";
        Assert.assertEquals(Thankyou.getText(),expMessage,"Thank you message not match!");

    }

    @Test(priority = 7)
    public void testBodyImage(){
        //Verify body image
        WebElement bodyelement = driver.findElement(By.className("pony_express"));
        String bodyImage = bodyelement.getAttribute("src");
       // String bactualUrl = bodyImage.replace("url(\"", "").replace("\")", "").replace("url(", "").replace(")", "");
        String expectedbodyUrl = "https://www.saucedemo.com/v1/img/pony-express.png";
        System.out.println("Body Image :"+ bodyImage);
        // Verify the extracted URL
        Assert.assertEquals(bodyImage, expectedbodyUrl, "Background image URL is incorrect!");
    }

    @AfterTest
    public void Aftertest(){
        //driver.quit();
        System.out.println("Clearing test data after execution...");
    }

}
