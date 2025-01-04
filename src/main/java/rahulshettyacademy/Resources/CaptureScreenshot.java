package rahulshettyacademy.Resources;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
	WebDriver driver;
	public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+ "//reports//"+ testCaseName + ".png");
		FileUtils.copyFile(srcFile,destFile);
		return System.getProperty("user.dir")+ "//reports//"+ testCaseName + ".png";
	}

}
