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
        // ���� WebDriver ��·��
        // System.setProperty("webdriver.edge.driver", "H:/edgedriver_win64/msedgedriver.exe");
        System.setProperty("webdriver.chrome.driver", "H:/chromedriver-win64/chromedriver-win64/chromedriver.exe");


        // ���� WebDriver ʵ��
        WebDriver driver = new ChromeDriver();

        // ������������վ
        driver.get("https://kns.cnki.net/kns8s/");

        // ��ȡ���������selenium
        driver.findElement(By.id("txt_search")).sendKeys("�������ѧ");

        // ��ȡ��ť����������
        driver.findElement(By.className("search-btn")).click();

        
        // ����ͣ��һ��
        try 
        {
            Thread.sleep(5000);
        } 
        catch (Exception e) 
        {
            
            e.printStackTrace();
        }

        // ��ȡҳ������������ʱ����Ϣ
        List<WebElement> search_time_Results = driver.findElements(By.className("date"));
        List<String> timeList =new ArrayList<String>();
        for(WebElement searchResult : search_time_Results)
        {
            String time = searchResult.getText();
            timeList.add(time);
        }

        // ��ȡҳ����������������<a>��ǩ
        List<WebElement> search_aElement_Results = driver.findElements(By.className("fz14"));
        // ��Ŀ�б�
        List<String> titleList = new ArrayList<String>();
        // �����б�
        List<String> herfList = new ArrayList<String>();
        
        for(WebElement aElement: search_aElement_Results)
        {
            // ��ȡ��Ŀ
            String title = aElement.getText();
            titleList.add(title);
            
            // ��ȡ��������,herf����ֵ
            String herfValue = aElement.getAttribute("herf");
            herfList.add(herfValue);
            System.out.println(herfValue);
        }

        // ��ȡҳ��������������Ϣ
        List<WebElement> search_author_Results = driver.findElements(By.className("author"));
        List<String> authorList = new ArrayList<String>();

        for(WebElement authorElement: search_author_Results)
        {
            // ������ȡ����
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
        
        // 5.�˳������
        driver.quit();
    }
    private static void saveToDatabase( int total_num, String title, String author,String time, String link) 
    {
        // �������ݿ�����
        String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";

        // �������ݿ�����
        try (Connection conn = DriverManager.getConnection(url, username, password)) 
        {
            // ׼�� SQL �������
            String sql =    "INSERT INTO `paper` (`id`, `title`, `author`, `Publication Date`, `abstract`, `Link`) VALUES (?, ?, ?, ?, NULL, ?)";

            // ���� PreparedStatement ����
            PreparedStatement statement = conn.prepareStatement(sql);

            // ���ò���
            statement.setInt(1, total_num);
            statement.setString(2, title);
            statement.setString(3, author);
            statement.setString(4, time);
            statement.setString(5, link);

            // ִ�в������
            statement.executeUpdate();

            // �ر� PreparedStatement
            statement.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

// ��ȡ���ķ���ʱ��
// String time = driver.findElement(By.className("date")).getText();

// // ��λaԪ������
// WebElement aElement = driver.findElement(By.className("fz14"));

// String title = aElement.getText();
// // ��ȡ��������,herf����ֵ
// String hrefValue = aElement.getAttribute("href");

// // ��ȡ����
// String author = driver.findElement(By.className("author")).getText();

// // ������������
// driver.findElement(By.className("fz14")).click();

// WebElement titleElement = driver.findElement(By.className("wx-tit"));