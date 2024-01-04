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
        //����url����һ��serverTimezone�Ĳ�����ֵ������GMT-8��Ҳ������Asia/Shanghai����ΪGMT-8��Ҫת�����ַ�"-"������ת�ƺ���GMT%2B8
        String username = "root";//username
        String password = "root";//password
        String targetUrl = "https://ssr1.scrape.center/";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // ���ӵ����ݿ�
            Connection connection = DriverManager.getConnection(url, username, password);
            
            // ʹ��Jsoup���ӵ�Ŀ����ҳ����ȡ����
            Document document = Jsoup.connect(targetUrl).get();

            // ������ҳ���ݣ���ȡ������Ϣ
            Elements paperElements = document.select("body");//your_paper_selector

            for (Element paperElement : paperElements) 
            {
                String title = paperElement.select("m-b-sm").text();//your_title_selector
                String author = paperElement.select("m-v-sm info").text();//your_author_selector
                String email = paperElement.select("span[data-v-7f856186]").text();//your_email_selector
                
                
                // ��������Ϣ�������ݿ�
                insertPaperIntoDatabase(title, author, email, connection);
            }

            connection.close();
            System.out.println("��ȡ���������ݳɹ�!");
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
        //sql��䣬��paper������������
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
