import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Wanfang_spider extends Test1
{
    Test1 test1 = new Test1();
    public void collect_Wanfang(int numOfPages)
    {
        String keywords = new String();
        try
        {   
            // 获取搜索关键字  
            keywords = get_Keywords();
        }
        catch(Exception e)
        {
            System.out.println("coding error!");
            System.exit(0);
        }
                  
        // 设置 WebDriver 的路径
        System.setProperty("webdriver.edge.driver", "H:/edgedriver_win64/msedgedriver.exe");

        // 请求头
        // options.addArguments("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        
        // 创建 WebDriver 实例
        WebDriver driver = new EdgeDriver();

        // 导航到论文网站
        driver.get("https://w.wanfangdata.com.cn/index.html?index=true");

        // 设置超时时间不超过6秒
        driver.manage().timeouts().pageLoadTimeout(6, TimeUnit.SECONDS);
        
        // 获取输入框，输入selenium
        driver.findElement(By.id("search-input")).sendKeys(keywords);

        // 获取按钮，进行搜索
        driver.findElement(By.className("search-btn-group")).click();

        for(int page = 0; page < numOfPages; page++) 
        {
            // 进程停顿一下
            try 
            {
                Thread.sleep(2000);
            }
            catch (Exception e) 
            {
                
                e.printStackTrace();
            }

            // 获取页面内所有论文链接<a>标签
            List<WebElement> search_aElement_Results = driver.findElements(By.className("title"));

            // 题目列表
            List<String> titleList = new ArrayList<String>();
            // 链接列表
            List<String> herfList = new ArrayList<String>();
            // 时间列表
            List<String> timeList =new ArrayList<String>();
            // 作者列表
            List<String> authorList = new ArrayList<String>();
            // 摘要列表
            List<String> abstractList = new ArrayList<String>();

            try 
            {
                for(WebElement aElement: search_aElement_Results)
                {
                    // 进程停止3秒钟
                    test1.stop(2000);

                    // 模拟点击进入页面
                    aElement.click();

                    // 获取论文链接,href属性值
                    String herfValue = driver.getCurrentUrl();
                    herfList.add(herfValue);
                    
                    // 使用 Jsoup 连接到网页并获取整个页面的 HTML 代码
                    Document doc = Jsoup.connect(herfValue).get();
                    String htmlCode = doc.html();
                    System.out.println(htmlCode);
                    
                    Elements elements1 = doc.select(".detailTitleCN");
                    Elements elements2 = elements1.first().getElementsByTag("span");
                    for(Element ele : elements2)
                    {
                        System.out.println(ele.text());
                    }

                    // 获取题目
                    String title = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[2]/div[1]/div[1]/div/div[2]/div/div/span")).getText();
                    titleList.add(title);
                    
                    
                    // 获取页面内所有论文时间信息
                    String time = driver.findElement(By.xpath("//*[@id=\"essential\"]/div[6]/div[4]/div/text()")).getText();
                    timeList.add(time);

                    // 获取作者
                    String author = driver.findElement(By.xpath("//*[@id=\"essential\"]/div[3]/div/a/span")).getText();
                    authorList.add(author);
                    
                    // 获取摘要
                    String ab = driver.findElement(By.xpath("//*[@id=\"essential\"]/div[6]/div[1]/span[2]/span/span/text()")).getText();
                    abstractList.add(ab);

                    driver.close();
                }
                
            } 
            catch (Exception e) 
            {
                // TODO: handle exception
                driver.close();
                
                e.printStackTrace();

            }
            
            
            try 
            {
                for(int i = 0; i < authorList.size(); i++)
                {
                    Thread.sleep(500);
                    // 从列表中获取当前论文信息
                    
                    String cur_title = titleList.get(i);
                    String cur_author = authorList.get(i);
                    String cur_time = timeList.get(i);
                    String cur_hrefValue = herfList.get(i);
                    String cur_abstracts = new String("");
                    // 总论文数+1
                    total_numberOf_papers +=1;
                    // 保存到数据库
                    saveToDatabase(total_numberOf_papers, cur_title, cur_author, cur_time,cur_abstracts, cur_hrefValue, 1);
                    
                    // 保存成功并输出
                    System.out.println("\ntitle:"+cur_title);  
                    System.out.format("paper %d load successfully!\n", total_numberOf_papers);
                    
                }
                
            } 
            catch (Exception e) 
            {
                // TODO: handle exception
                e.printStackTrace();
            }
            try 
            {
                WebElement nextPageButton = driver.findElement(By.id("next"));
                // 使用 JavaScript 模拟点击下一页按钮
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextPageButton);
            } 
            catch (Exception e) 
            {
                // TODO: handle exception
                System.out.print("\n\nERROR!\nCan not found next page!\nSystem quit!");
                driver.quit();
            }
            // 获取"下一页"按钮的元素，进行搜索
            
            
        }
        // 5.退出浏览器
        driver.quit();
        System.out.println("\nSpider end~");
    }
}
