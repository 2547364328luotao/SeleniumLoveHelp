package cn.officially.demo01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OpenLoveHelp {
    public static String ChromePath;
    private static WebDriver driver;

    private static void loadPropertiesPath() {
        Properties properties = new Properties();
        try (InputStream input = MainCode.class.getClassLoader().getResourceAsStream("config.properties");) {
            properties.load(input);
            ChromePath = properties.getProperty("ChromePath", "C:\\Users\\luotao\\AppData\\Local\\Google\\Chrome\\User Data");
        } catch (IOException e) {
            e.printStackTrace();
            ChromePath = "C:\\Users\\luotao\\AppData\\Local\\Google\\Chrome\\User Data"; // 如果读取失败，使用默认值
        }
    }

    // 私有构造函数，防止外部实例化
    private OpenLoveHelp() {}

    // 获取WebDriver实例的方法
    public static WebDriver getDriver() {
        if (driver == null) {
            // 设置ChromeDriver的路径
            System.setProperty("webdriver.chrome.driver", "C:\\Code\\Java\\selenium_study\\src\\drivers\\chromedriver.exe");

            // 创建ChromeOptions对象
            ChromeOptions options = new ChromeOptions();

            // 指定要使用的用户数据目录
            loadPropertiesPath();
            String userDataDir = ChromePath;
            options.addArguments("user-data-dir=" + userDataDir);

            // 指定要使用的配置文件目录
            // 注意：这里的Default或Profile 1应该替换为你实际的配置文件名称
            String profileDirectory = "Default"; // 或者 "Profile 1"
            options.addArguments("profile-directory=" + profileDirectory);

            // 禁用一些可能导致问题的特性
            // options.addArguments("--disable-extensions"); // 禁用扩展
            options.addArguments("--no-sandbox"); // 禁用沙箱模式
            options.addArguments("--disable-dev-shm-usage"); // 禁用共享内存

            // 初始化WebDriver实例
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    // 关闭WebDriver实例的方法
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // 确保下次重新初始化
        }
    }
}
