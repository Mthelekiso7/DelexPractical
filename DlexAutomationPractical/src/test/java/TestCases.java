import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class TestCases {
    public static String USERNAME = "luckymthelekiso@gmail.com";
    public static String PASSWORD = "Lucky@7";

     WebDriver driver;
    @Test
    void verifyPageTitle() throws InterruptedException {
       // Practical_POM.setPage_Path();
        System.setProperty("webdriver.chrome.driver","C:/Users/MAKOBE/Downloads/chromedriver_win32 (2)/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/");
        WebElement logo = driver.findElement(By.xpath("//*[@id='header_logo']/a/img"));

        Assert.assertTrue(logo.isDisplayed());
        Thread.sleep(8000);
    }
    @Test
    void serchCriteria() throws InterruptedException {
        verifyPageTitle();

        String searchCriteria = "Dress" +","+"T-shirts"+","+"Blouse";
        String[] searchValues = searchCriteria.split(",");

        for (int i = 0; i < searchValues.length; i++) {
           String value = searchValues[i];

           WebElement search = driver.findElement(By.xpath("//*[@id='search_query_top']"));
           search.sendKeys(value);
           Thread.sleep(4000);

           WebElement submit = driver.findElement(By.xpath("//*[@id='searchbox']/button"));
           submit.click();

           WebElement sResults = driver.findElement(By.xpath("//*[@id='center_column']/h1/span[1]"));

            if( sResults.getText().contains(value)){

            }
            else{

            }

            search.clear();
        }
    }
    @Test
    void TC3DataDriven() throws IOException, InterruptedException {
        //Path of the excel file
        FileInputStream fs = new FileInputStream("C:/Users/MAKOBE/IdeaProjects/DlexAutomationPractical/Drivers/AutomationPractical.xlsx");
        //Creating a workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        String url = sheet.getRow(0).getCell(0).toString();

        System.setProperty("webdriver.chrome.driver","C:/Users/MAKOBE/Downloads/chromedriver_win32 (2)/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        WebElement logo = driver.findElement(By.xpath("//*[@id='header_logo']/a/img"));

        Assert.assertTrue(logo.isDisplayed());
        Thread.sleep(8000);


    }
    @Test
    void login() throws InterruptedException {
        WebElement login = driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a"));
        login.click();

        WebElement username = driver.findElement(By.xpath("//*[@id='email']"));
        username.sendKeys(USERNAME);

        WebElement password = driver.findElement(By.xpath("//*[@id='passwd']"));
        password.sendKeys(PASSWORD);

        WebElement signin = driver.findElement(By.xpath("//*[@id='SubmitLogin']/span"));
        signin.click();
        Thread.sleep(8000);
    }

    @Test
    void addToCart() throws InterruptedException {
        WebElement search = driver.findElement(By.xpath("//*[@id='search_query_top']"));
        search.sendKeys("Dress");
        Thread.sleep(4000);

        WebElement submit = driver.findElement(By.xpath("//*[@id='searchbox']/button"));
        submit.click();
        Thread.sleep(16000);

        WebElement add = driver.findElement(By.xpath("//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span"));
        add.click();


    }
    @Test
    void navigatePage()
    {
        //Selecting a dress and subcategory

        Actions actions = new Actions(driver);
        WebElement dress = driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/a"));
        actions.moveToElement(dress);

        WebElement casualD = driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/ul/li[1]/a"));
        actions.moveToElement(casualD);
        actions.click().build().perform();

        //verify if casual dress has been selected
        WebElement casual = driver.findElement(By.xpath("//*[@id='center_column']/h1/span[1]"));
        Assert.assertEquals(casual.getText().toString(),casualD.getText().toString());

    }

    @AfterClass
    void tearDown()
    {
        driver.quit();
    }

}
