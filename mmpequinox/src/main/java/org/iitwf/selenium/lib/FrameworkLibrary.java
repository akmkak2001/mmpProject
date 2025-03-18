package org.iitwf.selenium.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class FrameworkLibrary {

    protected Properties prop;
    protected WebDriver driver;
    protected static ExtentReports extentReports;

    private static ThreadLocal<String> threadLocalBrowser = new ThreadLocal<>();

    String environment, browser;

    // Getter for browser using ThreadLocal
    public String getBrowser() {
        return threadLocalBrowser.get();
    }

    @BeforeSuite
    public void setUp() {
        System.out.println("Initializing ExtentReports...");
        
        loadExtentReportConfig();
        if (extentReports == null) {
            throw new IllegalStateException("ExtentReports initialization failed in @BeforeSuite.");
        }
        
        System.out.println("ExtentReports initialized successfully.");
    }

    public void loadExtentReportConfig() {
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            System.out.println("Initializing ExtentReports with timestamp: " + timestamp);
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReports_" + timestamp + ".html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            System.out.println("ExtentReports setup complete.");
        } catch (Exception e) {
            System.err.println("Error initializing ExtentReports: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void readProperties() throws IOException {
    	System.out.println("@BeforeTest - Executing readProperties()");
    	
        loadProperties("config//mmp_global.properties");

        // Get environment and browser from properties
        environment = prop.getProperty("environment");
        threadLocalBrowser.set(prop.getProperty("browser"));

        // Validate environment and browser
        if (environment == null || getBrowser() == null) {
            throw new IllegalArgumentException("Environment or browser property is missing in the configuration file.");
        }

        // Load environment-specific properties
        if (environment.equalsIgnoreCase("dev")) {
            loadProperties("config//mmp_dev.properties");
        } else if (environment.equalsIgnoreCase("qa")) {
            loadProperties("config//mmp_qa.properties");
        } else {
            throw new IllegalArgumentException("Invalid environment specified: " + environment);
        }
        
        System.out.println("Environment: " + environment);
        System.out.println("Browser: " + getBrowser());
        System.out.println("@BeforeTest - Completed Executing readProperties().Properties successfully loaded: " + prop);

     
    }

    public void loadProperties(String filePath) throws IOException {
    	
    	if (prop == null) {
            prop = new Properties();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("Properties file not found: " + filePath);
        }

        FileInputStream fis = new FileInputStream(file);
        prop = new Properties();
        prop.load(fis);
        System.out.println("Loaded properties file: " + filePath);
    }

    @BeforeClass
    public void invokeDriverInstance() {
    	System.out.println("Executing @BeforeClass for " + this.getClass().getSimpleName());
        
    	String currentBrowser = getBrowser();
        if (currentBrowser == null) {
            throw new IllegalStateException("Browser is not set. Ensure readProperties() is executed correctly.");
        }

        System.out.println("Initializing WebDriver for browser: " + currentBrowser);
        if (currentBrowser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (currentBrowser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + currentBrowser);
        }

        driver.manage().window().maximize();
    }

    public void launchBrowser(String url) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is null. Ensure WebDriver is initialized before calling launchBrowser().");
        }
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL is null or empty. Check property file configuration.");
        }
        System.out.println("Navigating to URL: " + url);
        driver.get(url);
        System.out.println("Navigation to URL complete.");
    }


    @AfterClass
    public void cleanup() {
    	System.out.println("@AfterClass for " + this.getClass().getSimpleName());
        
    	if (driver != null) {
            System.out.println("Closing WebDriver...");
            driver.quit();
            driver = null;
        }
    }

    @AfterSuite
    public void tearDown() {
    	System.out.println("@AfterSuite: Flushing ExtentReports...");
        
    	if (extentReports != null) {
            extentReports.flush();
            System.out.println("@AfterSuite: ExtentReports flushed successfully.");
        }
    }
}
