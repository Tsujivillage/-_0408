import java.sql.*;

public class sqlTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/mysql?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo";
        String user = "root";
        String password = "my-secret-pw";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("接続成功: " + conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}