package week4.day1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HCLTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver2/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//creating a textfile
		File file1 = new File("Result.txt");
		FileWriter fw = new FileWriter(file1);
		
		//Open Return order URL
		driver.get("https://return-order-app.herokuapp.com/");
		
		//maximizing the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Navigate to Order Field
		driver.findElementByXPath("//input[@id='order-id']").click();
		Thread.sleep(5000);
		
		//validating the order ID 1257945872
		//by Fetching the placeholder value
      	WebElement orderId = driver.findElementById("order-id");
      	String placeholderVal = orderId.getAttribute("placeholder");
      	String expectedVal = "1257945872";
      	if(placeholderVal.equalsIgnoreCase(expectedVal))
             	System.out.println("The placeholder value is expected, ie : "+placeholderVal);
      	else
             	System.out.println("The placeholder value is not correct --- " +placeholderVal);
      	
		//Enter the Order ID in the searchbox
		driver.findElementByXPath("//input[@id='order-id']").sendKeys(expectedVal);
		Thread.sleep(5000);
		
		//searching for the result
		driver.findElementByXPath("//div[@class=\"form-group\"][2]").click();
		Thread.sleep(5000);
		
		//scrolls down window
		js.executeScript("window.scrollBy(0,1000)");
		
		//returning the product
		driver.findElementByXPath("//div[@class=\"return-address\"]/button").click();
		
		//driver.findElementByClassName("return-address").click();
		Thread.sleep(5000);
		
		//printing the text displayed
		String str = driver.findElementByXPath("//div[@class=\"result\"]").getText();
		System.out.println(str);
		
		//copy the result text to a text file
		fw.write(str);
		fw.close();
		
		//closing the window
		driver.close();
		
		

	}

}
