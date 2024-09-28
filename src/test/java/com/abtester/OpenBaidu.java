package com.abtester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OpenBaidu {
    public static void main(String[] args) {
        // 创建OpenWeb实例
        OpenWeb web = new OpenWeb();

        // 调用openLove方法
        web.openLove();

        // 通过getDriver获取driver
        web.getDriver().quit(); // 继续使用driver并退出
    }
}
