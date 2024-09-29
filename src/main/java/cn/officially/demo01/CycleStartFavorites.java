package cn.officially.demo01;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.Properties;


public class CycleStartFavorites {
    int Count = 0;

    int Integral = 0;

    int ErrNum;

    private void loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");) {
            properties.load(input);
            ErrNum = Integer.parseInt(properties.getProperty("ErrNum", "2")); // 默认值为2
        } catch (IOException e) {
            e.printStackTrace();
            ErrNum = 2; // 如果读取失败，使用默认值
        }
    }

    public String integralQuery(){
        WebDriver driver = OpenLoveHelp.getDriver();
        WebElement integral = driver.findElement(By.id("currentpoints"));
        String integralText = integral.getText();
        return integralText;
    }
    public void start() throws InterruptedException {
        // 获取WebDriver实例
        WebDriver driver = OpenLoveHelp.getDriver();
        loadProperties();
        System.out.println(ErrNum);

        while (true){
            Thread.sleep(1000); // 睡眠1秒

            // 创建显式等待对象，设置最多等待10秒
            WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // 等待“我要点赞”按钮可点击
            WebElement likeButton = Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"FBBox\"]/a")));
            // 点击“我要点赞”按钮
            likeButton.click();

            // 获取所有窗口句柄
            String originalWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();

            // 切换到新打开的窗口
            for (String window : allWindows) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    break; // 只切换窗口
                }
            }

            // 等待新页面加载，您可以根据需要调整等待时间
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // 等待指定元素加载完成
//            WebElement element = wait.until(
//                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='arc_toolbar_report']/div[1]/div"))
//            );
            Thread.sleep(5000); // 睡眠5秒

            //按Q键对视频进行点赞
            Actions actions = new Actions(driver);
            actions.sendKeys("e").perform();
            WebElement Title = null;
            boolean isTitleClickable = false; // 定义一个布尔变量来保存状态
                try {
                    Title = Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='添加到收藏夹']")));
                    if (Title != null){
                        isTitleClickable = true;
                    }
                    System.out.println(isTitleClickable);
            } catch (Exception e) {
                System.err.println("等待或点击'添加到收藏夹'时发生错误：");
//                    try {
//                        // 查找元素
//                        WebElement fivepointedstar = driver.findElement(By.cssSelector(".video-fav-icon > path"));
//                        // 点击该元素
//                        fivepointedstar.click();
//                    } catch (NoSuchElementException e1) {
//                        System.out.println("未找到该元素: " + e1.getMessage());
//                    } catch (ElementClickInterceptedException e1) {
//                        System.out.println("点击操作被拦截: " + e1.getMessage());
//                    } catch (Exception e1) {
//                        System.out.println("发生错误: " + e1.getMessage());
//                    }

            }


            //选择收藏分类
            try {
                // 使用显式等待查找“已完成”按钮，并确保它可点击
                WebElement favoritesButton = Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='默认收藏夹']")));
                favoritesButton.click();
            } catch (Exception e) {
                System.err.println("点击复选框按钮时发生错误" );
                // 根据需要进行进一步处理，例如记录日志或重试
            }

            //确认
            Thread.sleep(1000); // 睡眠1秒
            try {
                // 使用显式等待查找“确认”按钮，并确保它可点击
                WebElement finishButton = Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.bili-dialog-m > div > div > div.bottom > button")));
                finishButton.click();
            } catch (Exception e) {
                System.err.println("点击确认按钮时发生错误");
                // 根据需要进行进一步处理，例如记录日志或重试
            }

            // 在完成操作后，如果需要返回原始窗口
            // 模拟用户操作关闭当前页面
            Thread.sleep(3000); // 睡眠1秒
            Actions ACtions = new Actions(driver);
            ACtions.sendKeys(Keys.CONTROL, "w").perform(); // 模拟 Ctrl + W 快捷键关闭当前标签页
            driver.switchTo().window(originalWindow);

            // 点击“已完成”按钮
            try {
                // 使用显式等待查找“已完成”按钮，并确保它可点击
                WebElement finishButton = Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.swal2-container.swal2-center.swal2-backdrop-show > div > div.swal2-actions > button.swal2-confirm.swal2-styled")));
                finishButton.click();
            } catch (Exception e) {
                System.err.println("点击已完成按钮时发生错误");
                // 根据需要进行进一步处理，例如记录日志或重试
            }

            // 等待校验完成
            WebElement title = driver.findElement(By.xpath("//*[@id=\"FBLike\"]/table/tbody/tr[1]/td"));
            // 提取文本内容
            String titleText = title.getText();
            try{
                WebDriverWait waiT = new WebDriverWait(driver, Duration.ofSeconds(5));
                waiT.until(d -> !d.findElement(By.xpath("//*[@id=\"FBLike\"]/table/tbody/tr[1]/td")).getText().equals(titleText));
                Count++;
                Integral = 0;
                System.out.println("第"+Count+"个收藏完成" + "当前积分：" + integralQuery());
            } catch (TimeoutException e){
                Integral++;
                System.out.println("第"+Count+"次收藏，第" + Integral + "次尝试错误" + "当前积分：" + integralQuery());
                WebElement fail = driver.findElement(By.id("FBPoints"));
//                if (fail.isDisplayed()){
//                    WebElement error = driver.findElement(By.xpath("//a[text()='跳过这个任务']"));
//                    error.click();
//                    Integral = 0;
//                }
                if (Integral >= ErrNum){
                    WebElement error = driver.findElement(By.xpath("//a[text()='跳过这个任务']"));
                    error.click();
                    Thread.sleep(1000); // 睡眠1秒
                    Integral = 0;
                }

            }

            // Thread.sleep(5000); // 睡眠5秒

        }
    }
}
