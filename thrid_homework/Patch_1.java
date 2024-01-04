import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;

public class Patch_1 
{
    
    public static void main(String[] args) 
    {
        
        String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=Asia/Shanghai";
        //useSSL = false;
        //连接url设置一个serverTimezone的参数，值可以是GMT-8，也可以是Asia/Shanghai，因为GMT-8需要转义连字符"-"，所以转移后变成GMT%2B8
        String username = "root";//username
        String password = "root";//password
        String targetUrl = "https://ssr1.scrape.center/";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 连接到数据库
            Connection connection = DriverManager.getConnection(url, username, password);
            
            // 使用Jsoup连接到目标网页并获取内容
            Document document = Jsoup.connect(targetUrl).get();

            // 解析网页内容，提取论文信息
            Elements paperElements = document.select("body");//your_paper_selector

            for (Element paperElement : paperElements) 
            {
                String title = paperElement.select("m-b-sm").text();//your_title_selector
                String author = paperElement.select("m-v-sm info").text();//your_author_selector
                String email = paperElement.select("span[data-v-7f856186]").text();//your_email_selector
                
                
                // 将论文信息插入数据库
                insertPaperIntoDatabase(title, author, email, connection);
            }

            connection.close();
            System.out.println("爬取并插入数据成功!");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    private static void insertPaperIntoDatabase(String title, String author, String email, Connection connection) throws SQLException 
    {
        //sql语句，在paper表中新增数据
        String sql = "INSERT INTO paper (title, author, email) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, email);

            statement.executeUpdate();
        }
    }

}
