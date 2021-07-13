import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/*
       Демонстрация наведения на элемент
       https://www.selenium.dev/documentation/en/support_packages/mouse_and_keyboard_actions_in_detail/

       Если страница будет тупить при загрузке, показать LoadingStrategy
       https://www.selenium.dev/documentation/en/webdriver/page_loading_strategy/
 */

public class ActionDemoTest {

        private final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
        private final String STUDENT_LOGIN = "Applanatest1";
        private final String STUDENT_PASSWORD = "Student2020!";
        private WebDriver driver;

        @BeforeEach
        public void beforeTest() {
            setUpDriverSession();
            login();
        }

        @AfterEach
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
@Test
    private void login() {
            driver.get(LOGIN_PAGE_URL);

            WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
            loginTextInput.sendKeys(STUDENT_LOGIN);

            WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
            passwordTextInput.sendKeys(STUDENT_PASSWORD);

            WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
            loginButton.click();
        }

        private void setUpDriverSession() {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);

            driver = new ChromeDriver(options);

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
    }


