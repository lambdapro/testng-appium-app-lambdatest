package com.lambdatest.run_parallel_test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class ParallelTest extends BaseTestNGTest {

    @Test
    public void test1() throws Exception {

        System.out.println("Searching india");
      AndroidElement searchElement = (AndroidElement) new WebDriverWait(threadLocalDriver.getTLDriver(), 30).until(
          ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
      searchElement.click();
      AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(threadLocalDriver.getTLDriver(), 30).until(
          ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));

      insertTextElement.sendKeys("India");
      Thread.sleep(5000);



    }
    @Test
    public void test2() throws Exception {

        System.out.println("Searching germany");
        AndroidElement searchElement = (AndroidElement) new WebDriverWait(threadLocalDriver.getTLDriver(), 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
        searchElement.click();
        AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(threadLocalDriver.getTLDriver(), 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("Germany");

        Thread.sleep(5000);



    }
}
