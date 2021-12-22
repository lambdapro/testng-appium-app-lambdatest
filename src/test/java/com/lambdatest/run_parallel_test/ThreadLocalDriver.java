package com.lambdatest.run_parallel_test;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidDriver;

public class ThreadLocalDriver {
    private ThreadLocal<AndroidDriver<AndroidElement>> tlDriver = new ThreadLocal<>();

    //OB: Setting AndroidDriver to ThreadLocal driver
    public synchronized void setTLDriver(AndroidDriver driver) {
        tlDriver.set(driver);
    }

    public synchronized AndroidDriver getTLDriver() {
        return tlDriver.get();
    }
    public ThreadLocal<AndroidDriver<AndroidElement>> returnDriver(){return tlDriver; }
}
