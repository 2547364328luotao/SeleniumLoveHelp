package cn.officially.demo01;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MacChecking {
    String MethodName;
    public void UseCoockie(){
        System.out.println("Use cookie to login");
    }

    public void UseAPI(){
        System.out.println("Use API to login");
    }

    public void UsePluggable(){
        System.out.println("Use token to login");
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");) {
            properties.load(input);
            MethodName = properties.getProperty("MethodName", "UseCoockie");
        } catch (IOException e) {
            e.printStackTrace();
            MethodName = "UseCoockie"; // 如果读取失败，使用默认值
        }
    }

    public void Robot(){
        loadProperties();
        if(MethodName.equals("UseCoockie")){
            UseCoockie();
        }else if(MethodName.equals("UseAPI")){
            UseAPI();
        }else if(MethodName.equals("UsePluggable")){
            UsePluggable();
        }

    }
}
