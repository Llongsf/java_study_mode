import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
// import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Patch_test 
{
    public static int total_numberOf_papers = 0;
    public static void main(String[] args) 
    {
        // 设置 WebDriver 的路径
        // System.setProperty("webdriver.edge.driver", "H:/edgedriver_win64/msedgedriver.exe");
        System.setProperty("webdriver.chrome.driver", "H:/chromedriver-win64/chromedriver-win64/chromedriver.exe");


        // 创建 WebDriver 实例
        WebDriver driver = new ChromeDriver();

        // 导航到论文网站
        driver.get("https://kns.cnki.net/kns8s/");

        // 获取输入框，输入selenium
        driver.findElement(By.id("txt_search")).sendKeys("计算机科学");

        // 获取按钮，进行搜索
        driver.findElement(By.className("search-btn")).click();

        
        // 进程停顿一下
        try 
        {
            Thread.sleep(5000);
        } 
        catch (Exception e) 
        {
            
            e.printStackTrace();
        }

        // 获取页面内所有论文时间信息
        List<WebElement> search_time_Results = driver.findElements(By.className("date"));
        List<String> timeList =new ArrayList<String>();
        for(WebElement searchResult : search_time_Results)
        {
            String time = searchResult.getText();
            timeList.add(time);
        }

        // 获取页面内所有论文链接<a>标签
        List<WebElement> search_aElement_Results = driver.findElements(By.className("fz14"));
        // 题目列表
        List<String> titleList = new ArrayList<String>();
        // 链接列表
        List<String> herfList = new ArrayList<String>();
        
        for(WebElement aElement: search_aElement_Results)
        {
            // 获取题目
            String title = aElement.getText();
            titleList.add(title);
            
            // 获取论文链接,herf属性值
            String herfValue = aElement.getAttribute("herf");
            herfList.add(herfValue);
            System.out.println(herfValue);
        }

        // 获取页面内所有作者信息
        List<WebElement> search_author_Results = driver.findElements(By.className("author"));
        List<String> authorList = new ArrayList<String>();

        for(WebElement authorElement: search_author_Results)
        {
            // 遍历获取作者
            String author = authorElement.getText();
            authorList.add(author);
            
        }
        for(int i = 0; i < 20 ;i++)
        {
            try 
            {
                Thread.sleep(1000);
                String cur_title = titleList.get(i);
                String cur_author = authorList.get(i);
                String cur_time = timeList.get(i);
                String cur_hrefValue = herfList.get(i);
                total_numberOf_papers +=1;
                saveToDatabase(total_numberOf_papers, cur_title, cur_author, cur_time, cur_hrefValue);
                System.out.format("paper %d load successfully!", total_numberOf_papers);
                System.out.print("\n");
            } 
            catch (Exception e) 
            {
                // TODO: handle exception
                e.printStackTrace();
            }
            
            
        }       
        
        // 5.退出浏览器
        driver.quit();
    }
    private static void saveToDatabase( int total_num, String title, String author,String time, String link) 
    {
        // 配置数据库连接
        String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";

        // 建立数据库连接
        try (Connection conn = DriverManager.getConnection(url, username, password)) 
        {
            // 准备 SQL 插入语句
            String sql =    "INSERT INTO `paper` (`id`, `title`, `author`, `Publication Date`, `abstract`, `Link`) VALUES (?, ?, ?, ?, NULL, ?)";

            // 创建 PreparedStatement 对象
            PreparedStatement statement = conn.prepareStatement(sql);

            // 设置参数
            statement.setInt(1, total_num);
            statement.setString(2, title);
            statement.setString(3, author);
            statement.setString(4, time);
            statement.setString(5, link);

            // 执行插入操作
            statement.executeUpdate();

            // 关闭 PreparedStatement
            statement.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

// 获取论文发表时间
// String time = driver.findElement(By.className("date")).getText();

// // 定位a元素属性
// WebElement aElement = driver.findElement(By.className("fz14"));

// String title = aElement.getText();
// // 获取论文链接,herf属性值
// String hrefValue = aElement.getAttribute("href");

// // 获取作者
// String author = driver.findElement(By.className("author")).getText();

// // 进入论文链接
// driver.findElement(By.className("fz14")).click();

// WebElement titleElement = driver.findElement(By.className("wx-tit"));