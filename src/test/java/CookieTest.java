import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class CookieTest {

    /*
        Продемонстрировать поведение зависящее от cookie
        https://www.selenium.dev/documentation/en/support_packages/working_with_cookies/

        - Открыть сайт в режиме инкогнито
        https://geekbrains.ru/professions/ios_developer

        - Открыть ту же страницу с установленными куки
            promo_code_showed
            promo_code

        - Убедиться, что промо баннер не отображается

        (!) Требуется быть внимательным с этим примером: обновление на сайте может сломать тест
     */

    private final String BASE_URL = "https://gb.ru/";
    private final String TARGET_PAGE = "professions/ios_developer";
    private WebDriver driver;

    @BeforeAll
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        initChromeDriver();
    }

    public void initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void initFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testWithoutCookie() {
        driver.get(BASE_URL + TARGET_PAGE);
        WebElement promoBanner = driver.findElement(By.cssSelector(
                "div[data-testid='promocode-banner-container']")
        );

        Assertions.assertTrue(promoBanner.isDisplayed());
    }
}

