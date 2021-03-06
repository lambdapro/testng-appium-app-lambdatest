package com.lambdatest.run_parallel_test;

import java.net.URL;
import java.util.Map;
import java.util.Iterator;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.*;


public class BaseTestNGTest {
    public AndroidDriver<AndroidElement> driver;
    public ThreadLocalDriver threadLocalDriver = new ThreadLocalDriver();
    //private static ThreadLocal<AndroidDriver<AndroidElement>> tlDriver = new ThreadLocal<>();

    @BeforeMethod(alwaysRun=true)
    @org.testng.annotations.Parameters(value={"deviceIndex"})
    public void setUp(String deviceIndex) throws Exception {
        long id = Thread.currentThread().getId();
        System.out.println("Before test-method. Thread id is: " + id);
        System.out.println("Index is: " + deviceIndex);
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/com/lambdatest/run_parallel_test/parallel.conf.json"));
        JSONArray envs = (JSONArray) config.get("environments");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(Integer.parseInt(deviceIndex));
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }
        
        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(capabilities.getCapability(pair.getKey().toString()) == null){
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        String username = System.getenv("LT_USERNAME");
        if(username == null) {
            username = (String) config.get("username");
        }
        username=username.trim();

        String accessKey = System.getenv("LT_ACCESS_KEY");
        if(accessKey == null) {
            accessKey = (String) config.get("access_key");
        }
        accessKey=accessKey.trim();
        String app = System.getenv("APP_ID");
        if(app != null && !app.isEmpty()) {
            capabilities.setCapability("app", app);
        }

        capabilities.setCapability("build",System.getenv("LT_BUILD_NAME"));


        threadLocalDriver.setTLDriver(new AndroidDriver<AndroidElement>(new URL("http://"+username+":"+accessKey+"@"+config.get("server")), capabilities));
        driver = threadLocalDriver.getTLDriver();

    }

    @AfterMethod
    public void tearDown() throws Exception {
        long id = Thread.currentThread().getId();
        System.out.println("After test-method. Thread id is: " + id);
        // Invoke driver.quit() to indicate that the test is completed. 
        // Otherwise, it will appear as timed out on BrowserStack.
        driver = threadLocalDriver.getTLDriver();
        if (driver!=null){
            //driver = threadLocalDriver.getTLDriver();
            driver.quit();
            threadLocalDriver.returnDriver().remove();
            System.out.println("test stopped");
        }
        else{
            //driver = threadLocalDriver.getTLDriver();
            driver.quit();
            
            System.out.println("test stopped in else");
        }


    }
}
