package ProgressBar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class T01 {
    public static void main(String[] args) throws InterruptedException {
        int totalSteps = 100; // 假设总共有100个步骤

        // 设置ChromeDriver的路径
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // 创建WebDriver实例
        WebDriver driver = new ChromeDriver();

        // 开始自动化任务
        performAutomationWithProgress(driver, totalSteps);

        // 关闭浏览器
        driver.quit();
    }

    private static void performAutomationWithProgress(WebDriver driver, int totalSteps) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        // 初始化进度为0%
        updateProgress(0, totalSteps);

        for (int i = 1; i <= totalSteps; i++) {
            // 模拟耗时操作
            Thread.sleep(50); // 每个步骤休眠50毫秒

            // 更新进度条
            updateProgress(i * 100 / totalSteps, totalSteps);

            // 在这里插入实际的自动化操作
            // driver.get("https://example.com");
            // driver.findElement(By.id("search")).sendKeys("query");
            // ...
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("\n完成所有任务，总耗时：" + duration + " ms");
    }

    private static void updateProgress(int percent, int totalSteps) {
        String progress = String.format("[%3d%%] %d/%d", percent, percent * totalSteps / 100, totalSteps);
        // 清除当前行的内容
        System.out.print("---------------------------");
    }
}