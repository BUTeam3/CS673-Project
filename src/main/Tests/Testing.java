import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testing {
    static Robot robot;
    static WebDriver driver;
	static WebDriverWait wait;

	public static void main(String[] args) throws AWTException, InterruptedException {
		robot = new Robot();
		driver = new FirefoxDriver();
		wait= new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.get("https://buteam3.herokuapp.com/");
		//register();
		login();
		//issue_testing();
		//chat_testing();
		issue_chat_testing();
		//driver.quit();
	}
	public static void register() throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		//Click create account
		driver.findElement(By.linkText("Create Account")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("button")));
		//Fill out information
		driver.findElement(By.name("givenName")).sendKeys("Regression");
		driver.findElement(By.name("surname")).sendKeys("Test3");
		driver.findElement(By.name("email")).sendKeys("test3@bu.edu");
		//weak password with no caps or numbers
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("confirmPassword")).sendKeys("password");
		//sends then click close
		driver.findElement(By.tagName("button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("close")));
		driver.findElement(By.className("close")).click();
		//password with caps
		driver.findElement(By.name("password")).sendKeys("Password");
		driver.findElement(By.name("confirmPassword")).sendKeys("Password");
		//sends then click close
		driver.findElement(By.tagName("button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("close")));
		driver.findElement(By.className("close")).click();
		//password with caps and number
		driver.findElement(By.name("password")).sendKeys("Password1");
		driver.findElement(By.name("confirmPassword")).sendKeys("Password1");
		driver.findElement(By.tagName("button")).click();
	}
	public static void login() throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Forgot Password?")));
		//fills in false log in information
		driver.findElement(By.name("login")).sendKeys("test2@bu.edu");
		driver.findElement(By.name("password")).sendKeys("errorpassword");
		//login fails then closes error message
		driver.findElement(By.tagName("button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("close")));
		driver.findElement(By.className("close")).click();
		//logs in with correct information
		driver.findElement(By.name("login")).sendKeys("test2@bu.edu");
		driver.findElement(By.name("password")).sendKeys("Password1");
		driver.findElement(By.tagName("button")).click();
		//avoids login bug
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
		driver.get("https://buteam3.herokuapp.com/");
	}
	public static void issue_testing() throws InterruptedException{
		Actions builder = new Actions(driver);
		Action dragAndDrop;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-target='#create_issue_modal']")));
		//inputs new task
		driver.findElement(By.cssSelector("button[data-target='#create_issue_modal']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("data")));
		driver.findElement(By.id("data")).sendKeys("Testing regression");
		//Closes with top right X button
		driver.findElement(By.cssSelector("button[aria-label='Close']")).click();
		Thread.sleep(3000);
		//inputs new task
		driver.findElement(By.cssSelector("button[data-target='#create_issue_modal']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("data")));
		driver.findElement(By.id("data")).clear();
		driver.findElement(By.id("data")).sendKeys("Testing regression");
		//Closes with close button
		driver.findElement(By.cssSelector("#create_issue_form button[data-dismiss='modal']")).click();
		Thread.sleep(3000);
		//inputs new task
		driver.findElement(By.cssSelector("button[data-target='#create_issue_modal']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("data")));
		driver.findElement(By.id("data")).clear();
		driver.findElement(By.id("data")).sendKeys("Testing regression");
		//Submits
		driver.findElement(By.cssSelector("#create_issue_form button[type='submit']")).click();
		//Drag task back to icebox
		Thread.sleep(2000);
		dragAndDrop = builder.clickAndHold(driver.findElement(By.cssSelector("#task_34")))
		     .moveToElement(driver.findElement(By.cssSelector("#icebox_board")))
		    .release(driver.findElement(By.cssSelector("#icebox_board")))
		   .build();
		  dragAndDrop.perform();
		  Thread.sleep(2000);
		//Ice back to backlog with difficulty
		driver.findElement(By.cssSelector("input[value='1']")).click();
		//Backlog to current
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#task_34 input[value='Start']")));
		driver.findElement(By.cssSelector("#task_34 input[value='Start']")).click();
		//Done in current
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#task_34 input[value='Done']")));
		driver.findElement(By.cssSelector("#task_34 input[value='Done']")).click();
		//Reject in current
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#task_34 input[value='Reject']")));
		driver.findElement(By.cssSelector("#task_34 input[value='Reject']")).click();
		//Backlog to current
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#task_34 input[value='Start']")));
		driver.findElement(By.cssSelector("#task_34 input[value='Start']")).click();
		//Accept in current
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#task_34 input[value='Done']")));
		driver.findElement(By.cssSelector("#task_34 input[value='Done']")).click();
		driver.findElement(By.cssSelector("#task_34 input[value='Accept']")).click();
		//Drag task back to icebox
		Thread.sleep(2000);
		dragAndDrop = builder.clickAndHold(driver.findElement(By.cssSelector("#task_34")))
		     .moveToElement(driver.findElement(By.cssSelector("#icebox_board")))
		    .release(driver.findElement(By.cssSelector("#icebox_board")))
		   .build();
		  dragAndDrop.perform();
		  Thread.sleep(2000);
		//Drag task to backlog
		dragAndDrop = builder.clickAndHold(driver.findElement(By.cssSelector("#task_34")))
		     .moveToElement(driver.findElement(By.cssSelector("#backlog_board")))
		    .release(driver.findElement(By.cssSelector("#backlog_board")))
		   .build();
		  dragAndDrop.perform();	
		  Thread.sleep(2000);	
		//Drag task to current
		dragAndDrop = builder.clickAndHold(driver.findElement(By.cssSelector("#task_34")))
		     .moveToElement(driver.findElement(By.cssSelector("#current_board")))
		    .release(driver.findElement(By.cssSelector("#current_board")))
		   .build();
		  dragAndDrop.perform();
		  Thread.sleep(2000);		
		//Drag task to done
		dragAndDrop = builder.clickAndHold(driver.findElement(By.cssSelector("#task_34")))
		     .moveToElement(driver.findElement(By.cssSelector("#done_board")))
		    .release(driver.findElement(By.cssSelector("#done_board")))
		   .build();
		  dragAndDrop.perform();
		  Thread.sleep(2000);		
	}
	public static void chat_testing() throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='chat_header']")));
		driver.findElement(By.cssSelector("div[class='chat_header']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='chat_box active']")));
		Thread.sleep(1000);
		driver.findElement(By.id("message")).sendKeys("Testing chat regression");
		driver.findElement(By.id("message")).sendKeys(Keys.RETURN);
		if(driver.findElement(By.cssSelector("div[class='msg_content me_msg']")).getText().equals("Testing chat regression")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='chat_header']")));
		}
	}

	public static void issue_chat_testing() throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='task_header']")));
		driver.findElement(By.cssSelector("div[class='task_header']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='task_box active']")));
		Thread.sleep(1000);
		driver.findElement(By.id("chat_button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("chat_count")));
		driver.findElement(By.id("chat_button")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("chat_count")));
		driver.findElement(By.id("chat_button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("chat_count")));
		driver.findElement(By.id("6")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("taskMessage")).sendKeys("Testing task chat 6 regression");
		driver.findElement(By.id("taskMessage")).sendKeys(Keys.RETURN);
		if(driver.findElement(By.cssSelector("div[class='msg_content me_msg']")).getText().equals("Testing task chat 6 regression")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='task_header']")));
		}
		
	}
}
