# testng-appium-app-lambdatest

This repository demonstrates how to run Appium tests in [TestNG](http://testng.org) on Lambdatest.

## Setup

### Requirements

1. Java 8+

    - If Java is not installed, follow these instructions:
        - For Windows, download latest java version from [here](https://java.com/en/download/) and run the installer executable
        - For Mac and Linux, run `java -version` to see what java version is pre-installed. If you want a different version download from [here](https://java.com/en/download/)

2. Maven
   - If Maven is not downloaded, download it from [here](https://maven.apache.org/download.cgi)
   - For installation, follow the instructions [here](https://maven.apache.org/install.html)

### Steps

LambdaTest Credentials
Set LambdaTest username and access key in environment variables. It can be obtained from LambdaTest Automation Dashboard
example:
For linux/mac
```sh
export LT_USERNAME="YOUR_USERNAME"
export LT_ACCESS_KEY="YOUR ACCESS KEY"
```
For Windows
```sh
set LT_USERNAME="YOUR_USERNAME"
set LT_ACCESS_KEY="YOUR ACCESS KEY"
```


To upload your app files:

   Use the following cURL command with your basic auth token
   curl --location --request POST 'https://manual-api.lambdatest.com/app/upload/realDevice' \ --header 'Authorization: Basic <add_your_basic_auth_from_LambdaTest_username_accessKey>' \
   --form 'name="lambda1"' \
   --form 'appFile=@"/path/to/file"'
   To generate Basic Auth (BASE64) token use:
   https://mixedanalytics.com/knowledge-base/api-connector-encode-credentials-to-base -64/ with your LambdaTest username & access key.

To execute the test cases
   - Use the Grid URL as: beta-hub.lambdatest.com/wd/hub
     Please refer to the capability generator on https://www.lambdatest.com/capabilities-generator/beta/index.html A more detailed capability guide (pdf) is attached (sample capability object is attached below)
     "deviceName": "Galaxy Tab S4", "platformName": "android", "platformVersion": "10",
     "app": "lt://APP10020241629742380085180", "visual": True,
     "console": True, "deviceOrientation": "PORTRAIT", "build": "new-12",
     "isRealMobile": True,
     }
     
     The test execution can be viewed on the Dashboard URL:
     https://appautomation.lambdatest.com/build
     List of supported Real Devices is available on:
     https://www.lambdatest.com/capabilities-generator/beta/index.html
     (lookout for the red dot next to the device name indicating the Real Devices)


### Install the dependencies

To install the dependencies for Android tests, run :
```sh
cd android/testng-examples
mvn clean
```


### **Run first test :**

- Switch to `run_first_test` directory under [Android examples](android/testng-examples) 
```sh
cd android/testng-examples
mvn test -P first
```

### **Speed up test execution with parallel testing :**

- Switch to `run_parallel_test` directory under [Android examples](android/testng-examples/) 
```sh
cd ios/testng-examples
mvn test -P parallel
```
### **Use Local testing for apps that access resources hosted in development or testing environments :**

- Switch to `run_local_test` directory under [Android examples](android/testng-examples/) or [iOS examples](ios/testng-examples/)
```sh
cd android/testng-examples
mvn test -P local
```
**Note**: If you are facing any issues, refer [Getting Help section](#Getting-Help)


## About LambdaTest

LambdaTest is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. LambdaTest supports all programming languages and frameworks that are supported with Selenium, and have easy integrations with all popular CI/CD platforms. It's a perfect solution to bring your selenium automation testing to cloud based infrastructure that not only helps you increase your test coverage over multiple desktop and mobile browsers, but also allows you to cut down your test execution time by running tests on parallel.
