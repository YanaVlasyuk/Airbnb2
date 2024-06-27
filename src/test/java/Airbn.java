import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Airbn {

    @Test
    public void testing() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.airbnb.com/");
//Login
        driver.findElement(By.xpath("//button[@data-testid='cypress-headernav-profile']")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Log in")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[.='Continue with email']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@data-testid='email-login-email']")).sendKeys("yana.vlasyuk129@gmail.com", Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.name("user[password]")).sendKeys("Q1234W@!", Keys.ENTER);
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.xpath("//button[@data-testid='cypress-headernav-profile']//img[@class]"))
                .isDisplayed());
        Thread.sleep(1000);
//Property search

        driver.findElement(By.id("bigsearch-query-location-input")).sendKeys("Ibiza, Spain", Keys.ENTER);
        driver.findElement(By.xpath("//div[@class='p1m42al0 atm_c8_km0zk7 atm_g3_18khvle atm_fr_1m9t47k atm_cs_6adqpa atm_ks_15vqwwr atm_sq_1l2sidv atm_vy_1osqo2v p1t4vwjw atm_7l_1jsbn00 dir dir-ltr']")).click();

        driver.findElement((By.xpath("//div[@data-testid='calendar-day-07/14/2024']"))).click();
        Thread.sleep(3000);
        // driver.findElement(By.xpath("//div[@class='p1m42al0 atm_c8_km0zk7 atm_g3_18khvle atm_fr_1m9t47k atm_cs_6adqpa atm_ks_15vqwwr atm_sq_1l2sidv atm_vy_1osqo2v p1t4vwjw atm_7l_1jsbn00 dir dir-ltr']")).click();
        driver.findElement((By.xpath("//div[@data-testid='calendar-day-07/20/2024']"))).click();
        Thread.sleep(3000);

        driver.findElement((By.xpath("//div[.='Who']"))).click();

        //driver.findElement(By.xpath("//button[aria-label='Move forward to switch to the next month.']")).click();
        //driver.findElement(By.xpath("//button[aria-label='Move forward to switch to the next month.']")).click(); IF TO SEARCH FOR NEXT MONTH?

        Thread.sleep(1000);
        WebElement numberOfAdults = driver.findElement(By.xpath("//button[@data-testid='stepper-adults-increase-button']"));
        numberOfAdults.click();
        numberOfAdults.click();

        WebElement numberOfChild = driver.findElement(By.xpath("//button[@data-testid='stepper-children-increase-button']"));
        numberOfChild.click();
        numberOfChild.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@data-testid='structured-search-input-search-button']")).click();
        Thread.sleep(1000);

        //Filtering Results:
        driver.findElement(By.xpath("//span[.='Filters']")).click();
        Thread.sleep(2000);
        WebElement minPrice = driver.findElement(By.id("price_filter_min"));
        minPrice.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE);
        minPrice.sendKeys("100");
        Thread.sleep(2000);

        WebElement maxPrice = driver.findElement(By.id("price_filter_max"));
        maxPrice.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE);
        maxPrice.sendKeys("600");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@class='ptiimno atm_7l_1p8m8iw dir dir-ltr']")).click();

        List<WebElement> priceElements = driver.findElements(By.xpath("//span[@class='_1e9y657'] | //span[@class='_t6t62s']"));
        List<Integer> resultPrices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replaceAll("[$,]", "");
            Thread.sleep(3000);
            int price = Integer.parseInt(priceText);
            if (price > 100 && price < 600) {
                System.out.println("true");
                Thread.sleep(3000);
            } else {
                System.out.println("Invalid price: " + priceText);
                Thread.sleep(3000);
            }
            resultPrices.add(price);
        }
        List<WebElement> discounted = driver.findElements(By.xpath("//span[@class='_1e9y657']"));
        List<Integer> discountedResult = new ArrayList<>();
        for (WebElement discount : discounted) {
            String priceDiscounted = discount.getText().replaceAll("[$,]", "");
            Thread.sleep(3000);
            int price2 = Integer.parseInt(priceDiscounted);
            if (price2 > 100 && price2 < 600) {
                System.out.println("true");
                Thread.sleep(3000);
            } else {
                System.out.println("Invalid price: " + priceDiscounted);
                Thread.sleep(3000);
            }
            discountedResult.add(price2);
        }
        List<WebElement> pricesPerNight = driver.findElements(By.xpath("//div[@data-testid='price-availability-row']//span[@class='a8jt5op atm_3f_idpfg4 atm_7h_hxbz6r atm_7i_ysn8ba atm_e2_t94yts atm_ks_zryt35 atm_l8_idpfg4 atm_mk_stnw88 atm_vv_1q9ccgz atm_vy_t94yts dir dir-ltr']"));
        String pricePerNightActual = pricesPerNight.get(0).getText().replaceAll("[^0-9]+", "");
        Thread.sleep(3000);
        System.out.println("Price per night Actual: " + pricePerNightActual);

        List<WebElement> totalPrices = driver.findElements(By.xpath("//div[@class='_tt122m']"));
        String totalPriceActual = totalPrices.get(0).getText().replaceAll("[^0-9]+", "");
        Thread.sleep(3000);
        System.out.println("Total price Actual: " + totalPriceActual);

        List<WebElement> ratings = driver.findElements(By.xpath("//span[@class='ru0q88m atm_cp_1ts48j8 dir dir-ltr']"));
        String ratingFirstActual = ratings.get(0).getText().substring(0, 3);
        System.out.println("Rating Actual: " + ratingFirstActual);

        driver.findElement(By.xpath("//div[@aria-labelledby='title_602673762718870573']")).click();
        String originalWindow = driver.getWindowHandle();
        if (driver.findElement(By.xpath("//span[@class='i3tjjh1 atm_mk_h2mmj6 dir dir-ltr']")).isDisplayed()) {
            driver.findElement(By.xpath("//span[@class='i3tjjh1 atm_mk_h2mmj6 dir dir-ltr']")).click();
        }
        String pricePerNightExpected = driver.findElement(By.xpath("(//span[@class='_t6t62s'])[2]")).getText().replaceAll("[^0-9]+", "");
        String totalPriceExpected = driver.findElement(By.xpath("//span[@class='_1qs94rc']//span[@class='_j1kt73']")).getText().replaceAll("[^0-9]+", "");
        String ratingFirstExpected = driver.findElement(By.xpath("//div[@data-testid='pdp-reviews-highlight-banner-host-rating']//div[@aria-hidden]")).getText();

        System.out.println("Per night Expected: " + pricePerNightExpected);
        System.out.println("Total price Expected: " + totalPriceExpected);
        System.out.println("Rating Expected: " + ratingFirstExpected);

        Assert.assertEquals(pricePerNightActual, pricePerNightExpected);
        Assert.assertEquals(totalPriceActual, totalPriceExpected);
        Assert.assertEquals(ratingFirstActual, ratingFirstExpected);
        driver.close();
        driver.switchTo().window(originalWindow);

        //Logout:
        driver.findElement(By.xpath("//button[@data-testid='cypress-headernav-profile']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[.='Log out']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@data-testid='cypress-headernav-profile']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Log in")).isDisplayed());
        Thread.sleep(1000);
        driver.quit();

    }
}


