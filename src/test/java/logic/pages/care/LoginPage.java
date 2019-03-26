package logic.pages.care;

import logic.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "txtUserName")
    WebElement txtUsername;

    @FindBy(id = "txtPassword")
    WebElement txtPassword;

    @FindBy(name = "Ok")
    WebElement btnOk;

    @FindBy(xpath = "//input[@value='Find Now']")
    WebElement btnFindNow;


    public void login(String username, String password) {
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnOk.click();
        waitUntilElementVisible(btnFindNow);
    }
}
