package com.abtester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OpenLove {
    public static void main(String[] args) {
        // 设置ChromeDriver的路径
        System.setProperty("webdriver.chrome.driver", "C:\\Code\\Java\\selenium_study\\src\\drivers\\chromedriver.exe");

        // 创建ChromeOptions对象
        ChromeOptions options = new ChromeOptions();

        // 指定要使用的用户数据目录
        String userDataDir = "C:\\Users\\luotao\\AppData\\Local\\Google\\Chrome\\User Data";
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
        WebDriver driver = new ChromeDriver(options);


        // 打开百度网站
        driver.get("https://www.baidu.com");

        try {
            Thread.sleep(10000); // 10000毫秒 = 10秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭浏览器
        driver.quit();
    }
}
