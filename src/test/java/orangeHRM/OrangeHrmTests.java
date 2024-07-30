package orangeHRM;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("testes automatizados no orangeHRM")
public class OrangeHrmTests {

    private WebDriver navegador;

    @BeforeEach
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        navegador = new ChromeDriver();

        navegador.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @AfterEach
    public void tearDown() {

        if (navegador != null) {
            navegador.quit();

        }

    }

    @Test
    @DisplayName("sign up fail")
    public void testSignUpFail() {

        navegador.findElement(By.name("username")).sendKeys("teste");
        navegador.findElement(By.name("password")).sendKeys("teste");
        navegador.findElement(By.className("orangehrm-login-button")).click();

        WebElement element = navegador.findElement(By.className("oxd-alert-content-text"));
        String color = element.getCssValue("Color");
        Assertions.assertEquals("rgb(235, 9, 16)", color);

    }

    @Test
    @DisplayName("sign up success")
    public void testSignUpSuccess() {

        navegador.findElement(By.name("username")).sendKeys("Admin");
        navegador.findElement(By.name("password")).sendKeys("admin123");
        navegador.findElement(By.className("orangehrm-login-button")).click();

    }

    @Test
    @DisplayName("change full name")
    public void testChangeFullName() {

        navegador.findElement(By.name("username")).sendKeys("Admin");
        navegador.findElement(By.name("password")).sendKeys("admin123");
        navegador.findElement(By.className("orangehrm-login-button")).click();

        navegador.findElement(By.xpath("//span[text()='My Info']")).click();

        WebElement textFirst = navegador.findElement(By.name("firstName"));
        textFirst.sendKeys("Diego");

        WebElement textMiddle = navegador.findElement(By.name("middleName"));
        textMiddle.sendKeys("Armando");

        WebElement textLast = navegador.findElement(By.name("lastName"));
        textLast.sendKeys("Maradona");

        WebElement childElement = navegador.findElement(By.xpath("//p[text()=' * Required']"));
        WebElement parentElement = childElement.findElement(By.xpath(".."));
        parentElement.findElement(By.className("orangehrm-left-space")).click();

    }

    @Test
    @DisplayName("sign up and logout")
    public void testSignUpAndLogout() {

        navegador.findElement(By.name("username")).sendKeys("Admin");
        navegador.findElement(By.name("password")).sendKeys("admin123");
        navegador.findElement(By.className("orangehrm-login-button")).click();

        navegador.findElement(By.className("oxd-userdropdown-tab")).click();

        navegador.findElement(By.xpath("//a[text()='Logout']")).click();

    }

}
