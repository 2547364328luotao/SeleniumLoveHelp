package cn.officially.demo01;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class MainCode {
    public static String TaskType = null;

    private static void loadPropertiesTask() {
        Properties properties = new Properties();
        try (InputStream input = MainCode.class.getClassLoader().getResourceAsStream("config.properties");) {
            properties.load(input);
            TaskType = properties.getProperty("TaskType", "CycleStartLike");
        } catch (IOException e) {
            e.printStackTrace();
            TaskType = "CycleStartLike"; // 如果读取失败，使用默认值
        }
    }
    private static WebElement getCapTchaElement(WebDriver driver) {
        try {
            return driver.findElement(By.xpath("//*[@id=\"swal2-html-container\"]/div/ul/li[1]/b"));
        } catch (NoSuchElementException e) {
            System.err.println("验证码元素未找到!!!: ");
            return null; // 返回null以指示未找到元素
        }
    }

    public static void main(String[] args) {
        TaskType = null;
        loadPropertiesTask();

    while (true) { // 无限循环
        try {
            // 获取WebDriver实例
            WebDriver driver = OpenLoveHelp.getDriver();

            // 打开任务页面
            switch (TaskType) {
                case "CycleStartLike":
                    driver.get("https://lovehelp.xyz/earn-bilibili-likes");
                    break;
                case "CycleStartFavorites":
                    driver.get("https://lovehelp.xyz/earn-bilibili-favorites");
                    break;
                default:
                    System.err.println("未知任务类型: " + TaskType);
                    break;
            }

            // 浏览器最大化
            driver.manage().window().maximize();

            // 判断是否需要人机验证
            // WebElement capTcha = driver.findElement(By.xpath("//*[@id=\"swal2-html-container\"]/div/ul/li[1]/b"));
            Thread.sleep(5000); // 暂停5秒
            WebElement capTcha = getCapTchaElement(driver);
            if (capTcha != null && capTcha.getText().equals("1. 请选择你完成本次任务使用的B站频道账号")) {
                // 点击“OK”按钮
                WebElement okButton = null;
                try {
                    okButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")); // 使用更通用的XPath
                    okButton.click();
                } catch (NoSuchElementException e) {
                    System.err.println("未找到OK按钮: " + e.getMessage());
                }
                //*****************************************点赞任务开始******************************************************//
                MacChecking macchecking = new MacChecking();
                macchecking.Robot();

                switch (TaskType) {
                    case "CycleStartLike":
                        CycleStartLike cyclestartlike = new CycleStartLike();
                        cyclestartlike.start();
                        break;
                    case "CycleStartFavorites":
                        CycleStartFavorites cyclestartfavorites = new CycleStartFavorites();
                        cyclestartfavorites.start();
                        break;
                    default:
                        System.err.println("未知任务类型: " + TaskType);
                        break;
                }
                CycleStartLike cyclestartlike = new CycleStartLike();
                cyclestartlike.start();
//                CycleStartFavorites cyclestartfavorites = new CycleStartFavorites();
//                cyclestartfavorites.start();
                //*****************************************点赞任务结束******************************************************//
            } else {
                System.out.println("需要人机验证");
                MacChecking macchecking = new MacChecking();
                macchecking.Robot();
                driver.get("https://lovehelp.xyz/earn-bilibili-subs?change=hcaptcha");
                WebElement capTchaChexbox = driver.findElement(By.xpath("//*[@id=\"anchor-tc\"]"));
                Thread.sleep(5000); // 暂停5秒
                capTchaChexbox.click();
                Thread.sleep(5000000); // 暂停5秒后重启
            }

            // 关闭WebDriver实例
            OpenLoveHelp.quitDriver();
        } catch (Exception e) {
            System.err.println("发生错误: " + e.getMessage());
            // 可选择在此处添加延迟以避免过快重启
            try {
                Thread.sleep(5000); // 暂停5秒后重启
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


    private static void performSearch(WebDriver driver) {
        // 定位搜索框并输入关键字
        WebElement searchBox = driver.findElement(By.id("kw"));
        searchBox.sendKeys("哔哩哔哩");

        // 提交搜索
        searchBox.submit();
    }
}
