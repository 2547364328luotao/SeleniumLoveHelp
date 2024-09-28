package experiment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        // 获取WebDriver实例
        WebDriver driver = WebDriverManager.getDriver();
        System.out.println(driver);

        // 打开搜索页面
        driver.get("https://www.baidu.com");

        // 浏览器最大化
        driver.manage().window().maximize();

        // 执行搜索操作
        performSearch(driver);

        //等待页面加载完成
        Thread.sleep(3000);

        //点击“哔哩哔哩”官网连接
        WebElement biliLink = driver.findElement(By.xpath("//*[@id=\"1\"]/div/h3/a[1]"));
        biliLink.click();

        // 暂停一段时间，以便观察搜索结果
        Thread.sleep(3000);

        // 关闭WebDriver实例
        WebDriverManager.quitDriver();
    }

    private static void performSearch(WebDriver driver) {
        // 定位搜索框并输入关键字
        WebElement searchBox = driver.findElement(By.id("kw"));
        searchBox.sendKeys("哔哩哔哩");

        // 提交搜索
        searchBox.submit();
    }
}