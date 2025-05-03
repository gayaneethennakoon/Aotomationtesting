package functions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;

public class NewtoursCommonFunctions {
    WebDriver driver;
    public NewtoursCommonFunctions(WebDriver driver){
        this.driver = driver;
    }
    public String registersuccessfunction(String fname, String lname, String email, String username, String password, String confirmpassword) {
        HomePageUpdated Homepage = new HomePageUpdated(driver);
        Homepage.selectRegisterMenu();

        RegisterPageUpdated RegisterPage = new RegisterPageUpdated(driver);
        RegisterPage.setfirstname(fname);
        RegisterPage.setlastname(lname);
        RegisterPage.seteMail(email);
        RegisterPage.selectCounrty();
        RegisterPage.setUsername(username);
        RegisterPage.setPassword(password);
        RegisterPage.setConfirmPassword(confirmpassword);
        RegisterPage.submit();

            RegisterSuccessPage registerSuccessPage = new RegisterSuccessPage(driver);
           String actualtext = registerSuccessPage.RegisterSuccesstext();
            return actualtext;
    }

}
