package Assignments;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.lang.*;


@SuppressWarnings("unused")

public class MergedCode {

    private String URL1 = "http://rav-vm-vdi-065/RMSTraining/";
    public String SelectedTrainingType = "Knowledge Sharing Session";
    public String SelectedType  = "Testing";
    public String Agenda  = "MaheshGaikar Agenda";
    public String date1  = "30/03/2017";
    //public String Comments  = "HV Comments";
    public String topic  = "MaheshGaikar Topic";
    public String empId = "P1179";
    public String Presenter1;  //   = "Haresh Vaity";
    boolean type;

    WebDriver driver = null;
    WebElement element;


    @BeforeTest
    public void setup() throws Exception
    {
        // Open RMS Training home page
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium training\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL1);
    }  //End of @BeforeTest


    @Test (priority = 1)
    public void testcase1() throws Exception
    {
        // driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

        Actions actions = new Actions(driver);
        WebElement menuHoverLink = driver.findElement(By.xpath(".//*[@id='divMenu']/ul/li[2]"));
        //WebElement menuHoverLink = driver.findElement(By.className("current"));
        actions.moveToElement(menuHoverLink);
        actions.click().build().perform();

        WebElement mclick=driver.findElement(By.xpath(".//*[@id='divMenu']/ul/li[2]/ul/li[1]/a"));
        actions.moveToElement(mclick);
        actions.click().build().perform();

        //TC-1 :Validate that the window title is "Training Module"
        String title = driver.getTitle();
        if (title.contains("Training Module"))
        {
            System.out.println("Validated window title as 'training module'");
        } else
        {
            System.out.println("Window title is not 'training module'");
        }
        getscreenshot();
    }




    @Test (priority = 2)
    public void testcase2() throws Exception
    {

        //TC-2 : Validating fields on 'Raise Training Request' Page
        Select oSelect = new Select(driver.findElement(By.id("TrainingType")));//first go to KSS Page by selecting value from drop down
        oSelect.selectByVisibleText("Knowledge Sharing Session");
        //validate 'Type' field is present on page or not. repeat same for other 3 fields that is  Topic Agenda Presenter Date

        Thread.sleep(5000);
        boolean status = driver.findElement(By.xpath("//*[@id=\"divGrid\"]/div[1]/div/div[1]/div[1]/label")).isDisplayed();

        if (status = true)
        {
            System.out.println("Type present");
        }

    }

    @Test (priority = 3)
    public void testcase3() throws Exception
    {


        //TC-3 : Select KSS Type Testing/Technical/BA/Usability from the drop dow

        Select selectType = new Select(driver.findElement(By.name("Type")));
        selectType.selectByVisibleText("Testing");

    }


    @Test (priority = 4)
    public void testcase4() throws Exception
    {



        //TC-4 :  Topic – Text area should be displayed to enter the topic

        driver.findElement(By.id("Topic")).sendKeys(topic);

    }


    @Test (priority = 5)
    public void testcase5() throws Exception
    {


        //TC-5 :  Agenda – Text area should be displayed to enter the agenda
        driver.findElement(By.cssSelector("#Agenda")).sendKeys(Agenda);

    }

    @Test (priority = 6)
    public void testcase6() throws Exception
    {

        //TC-6 : select presenter from the list
        driver.findElement(By.xpath("//*[@id=\"check\"]/img")).click();
        driver.findElement(By.xpath("//*[@id='viewreport_filter']/label/input")).click();
        driver.findElement(By.xpath("//*[@id='viewreport_filter']/label/input")).sendKeys(empId);

        Thread.sleep(3000);

        WebElement table = driver.findElement(By.id("viewreport"));

        List<WebElement> rows=table.findElements(By.tagName("tr"));
        for (int i=0; i<rows.size(); i++)
        {
            List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
            for(int j=0;j < cols.size();j++)
            {

                // Thread.sleep(5000);
                //String ocheck=cols.get(j).getText();
                //WebElement tmp=cols.get(j).findElement(By.tagName("input"));
                if(cols.get(j).getText().contentEquals(empId))
                {
                    System.out.println("match found for presenter");

                    rows.get(i).findElement(By.tagName("input")).click();
                    //  tmp.click();
                    break;
                }
            }

        }//end of for

        Presenter1 = driver.findElement(By.id("Presenter")).getText();
        System.out.println("Presenterrrrrrrrrrrrr   Value Entered is " + Presenter1);
        Thread.sleep(3000);
        driver.findElement(By.id("BtnSecondSelect")).click();
        Thread.sleep(2000);

    }

    @Test (priority = 7)
    public void testcase7() throws Exception
    {

        //date picker

        driver.findElement(By.xpath(".//*[@id='Date']")).click();
        WebElement StartdateWidget = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[5]/a"));
        driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[5]/a")).click();
        StartdateWidget.findElements(By.tagName("tr"));

    }

    @Test (priority = 8)
    public void testcase8() throws Exception
    {



        //to click submit on raise
        driver.findElement(By.id("BtnSubmit")).submit();
        Thread.sleep(3000);
        driver.navigate().to("http://rav-vm-vdi-065/RMSTraining");

    }

    //Test Case 9
    @Test (priority = 9)
    public void TestCase9() throws Exception
    {
        // Click on Training Tab
        Actions actions = new Actions(driver);
        WebElement mainMenu = driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]"));
        actions.moveToElement(mainMenu);
        actions.click().build().perform();

        //Click on View Training Request Sub-Tab
        WebElement subMenu = driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]/ul/li[2]/a"));
        actions.moveToElement(subMenu);
        actions.click().build().perform();

        // TC 9 - Validate View training Request
        String curPage = driver.getTitle();
        // String str2 is created to compare the text of Page Title
        String str2 = "ViewTrainingRequest Training Module";
        System.out.println("Current Page Title is " + curPage);
        System.out.println("---------------------");
        Thread.sleep(3000);
        Assert.assertEquals(str2, curPage);
	/*
		if (curPage.equalsIgnoreCase(str2))
		{
			System.out.println("viewTrainingRequest Page displayed correctly");
		}
	 */
    } // End of @Test Case 9

//Test Case 10

    @Test (priority =10)
    public void TestCase10() throws Exception
    {
        // To click on Knowledge Sharing
        try
        {
            List<WebElement> lis = driver.findElements(By.name("category"));
            int isize = lis.size();
            for(int i=0;i < isize; i++)
            {
                String sVal = lis.get(i).getAttribute("value");
                if (sVal.equalsIgnoreCase("1210"))
                {
                    lis.get(i).click();
                    System.out.println(lis.get(i).getAttribute("id"));
                }  // End if
            }  //  End for
            Thread.sleep(3000);
            int c=0;

            //This will Search for Topic and then click on view Icon
            driver.findElement(By.xpath("//*[@id='viewreport_filter']/label/input")).click();
            driver.findElement(By.xpath("//*[@id='viewreport_filter']/label/input")).sendKeys(topic);

            WebElement table = driver.findElement(By.id("viewreport"));

            List<WebElement> rows=table.findElements(By.tagName("tr"));
            OUTER:
            for (int i=0;i<rows.size();i++)
            {
                List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
                INNER:
                for(int j=0;j<cols.size();j++)
                {

                    if((cols.get(j).getText().contentEquals(topic)))
                    {
                        //System.out.println("match found for presenter" + "Value of I"+ i + "  Value of J" + j);
                        System.out.println("-----");
                        rows.get(i).findElement(By.className("view")).click();
                        //System.out.println("BREAK");
                        //break;
                        c=1;
                        break INNER;

                    }// End if
                }//End of Inner for loop
                if (c ==1)
                {
                    break OUTER;
                } // End if c==1
            } // End of Outer for loop
            Thread.sleep(3000);


            //  Below will check that the Training Type Field is Displayed on the page and has correct value as Entered.
            Select sel = new Select (driver.findElement(By.id("TrainingType")));
            //		WebElement TrainingType = driver.findElement(By.id("TrainingType"));

            boolean TrainType = driver.findElement(By.id("TrainingType")).isDisplayed();
            if (TrainType == true)
            {

                if (SelectedTrainingType.equals(sel.getFirstSelectedOption().getText()))
                {
                    //System.out.println("Test Case TC10 is Passed ");
                    System.out.println("Selected Training Type " + SelectedTrainingType  + " is correctly displayed on view training request page");

                }
                else
                {
                    System.out.println("Selected Training Type " + SelectedTrainingType  + " is NOT correctly displayed on view training request page");
                    System.out.println("Currently Training Type field has value " + sel.getFirstSelectedOption().getText() );
                }
                //System.out.println("TrainingType Field is present on view training request page");
            }
            else
            {
                System.out.println("TrainingType Field is NOT present on view training request page");

            }


        }  // End try block

        catch (Exception e)
        {
            System.out.println("Stale Error"+e);
        }
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("------------------");

    } // End of @Test Case 10

    @Test (priority = 11)
    public void TestCase11()
    {
        //  Below will check that Type Field is Displayed on the page and has correct value as Entered.
        Select sel2 = new Select (driver.findElement(By.id("Type")));
        //		WebElement TrainingType = driver.findElement(By.id("TrainingType"));
        type = driver.findElement(By.id("Type")).isDisplayed();
        if (type == true)
        {

            if (SelectedType.equals(sel2.getFirstSelectedOption().getText()))
            {
                //System.out.println("Test Case TC10 is Passed ");
                System.out.println("Selected  Type " + SelectedType  + " is correctly displayed on view training request page");

            }
            else
            {
                System.out.println("Selected Type " + SelectedType  + " is NOT correctly displayed on view training request page");
                System.out.println("Currently Type field has value " + sel2.getFirstSelectedOption().getText() );
            }
            //System.out.println("TrainingType Field is present on view training request page");
        }
        else
        {
            System.out.println("Type Field is NOT present on view training request page");

        }
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("------------------");
    }
    @Test (priority = 12)
    public void TestCase12()
    {
        //  Below will check that Agenda Field is Displayed on the page and has correct value as Entered.
        boolean agenda = driver.findElement(By.name("Agenda")).isDisplayed();
        if (type == true)
        {

            if (Agenda.equals(driver.findElement(By.name("Agenda")).getText()))
            {
                //System.out.println("Test Case TC10 is Passed ");
                System.out.println("Selected  Agenda " + Agenda  + " is correctly displayed on view training request page");

            }
            else
            {
                System.out.println("Selected  Agenda " + Agenda  + " is NOT correctly displayed on view training request page");
                System.out.println("Currently Agenda field has value " + driver.findElement(By.name("Agenda")).getText() );
            }
            //System.out.println("TrainingType Field is present on view training request page");
        }
        else
        {
            System.out.println("Agenda Field is NOT present on view training request page");

        }
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("------------------");
    }

    @Test (priority = 13)
    public void TestCase13()
    {
        //  Below will check that Date Field is Displayed on the page and has correct value as Entered.
        // Use of Comparison Operation - &&

        if ((driver.findElement(By.id("Date")).isDisplayed()==true) &&  (date1.equals(driver.findElement(By.id("Date")).getAttribute("Value")) ))
        {
            System.out.println("Selected  Date " + date1  + " is correctly displayed on view training request page");
        }
        else
        {
            System.out.println("Selected  Date " + date1  + " is NOT correctly displayed on view training request page");
            System.out.println("Currently Date field has value " + driver.findElement(By.id("Date")).getAttribute("Value") );
        }

        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("------------------");

    }

    /*
    @Test (priority = 14)
    public void TestCaseComments()
    {
        //  Below will check that Comments Field is Displayed on the page and has correct value as Entered.
        // Use of Comparison Operation - &&

        if ((driver.findElement(By.id("Comments")).isDisplayed()==true) &&  (Comments.equals(driver.findElement(By.id("Comments")).getText()) ))
        {
            System.out.println("Selected  Comments " + Comments + " is correctly displayed on view training request page");
        }
        else
        {
            System.out.println("Selected  Comments " + Comments + " is NOT correctly displayed on view training request page");
            System.out.println("Currently Comments field has value " + driver.findElement(By.id("Comments")).getText() );
        }

        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("------------------");

    }
    */
    @Test (priority = 15)
    public void TestCase14()
    {
        //Below will check that Topic Field is Displayed on the page and has correct value as Entered.
        // Use of Comparison Operation - &&

        if ((driver.findElement(By.id("Topic")).isDisplayed()==true) &&  (topic.equals(driver.findElement(By.id("Topic")).getText()) ))
        {
            System.out.println("Selected  Topic  " + topic + " is correctly displayed on view training request page");
        }
        else
        {
            System.out.println("Selected  Topic " + topic + " is NOT correctly displayed on view training request page");
            System.out.println("Currently Topic field has value " + driver.findElement(By.id("Topic")).getText() );
        }

        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("------------------");

    }

    @Test (priority = 16)
    public void TestCase15()
    {

        //Below will check that Presenter Field is Displayed on the page and has correct value as Entered.
        // Use of Comparison Operation - &&

        if ((driver.findElement(By.name("Presenter")).isDisplayed()==true) &&  (Presenter1.equals(driver.findElement(By.name("Presenter")).getText()) ))
        {
            System.out.println("Selected  Presenter  " + Presenter1 + " is correctly displayed on view training request page");
        }
        else
        {
            System.out.println("Selected  Presenter " + Presenter1 + " is NOT correctly displayed on view training request page");
            System.out.println("Currently Presenter field has value " + driver.findElement(By.name("Presenter")).getText() );
        }

        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("------------------");

    }

    public void getscreenshot() throws IOException {
        System.out.println("In screenshot");
        long millis = System.currentTimeMillis();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
        FileUtils.copyFile(scrFile, new File("D:\\salenium\\Screens\\screenshot" + millis + ".png"));
    }

    @AfterTest
    public void endtest() throws Exception
    {

        Thread.sleep(3000);
        driver.close();
    }  // End of @AfterTest



}// End of Class Assignment1