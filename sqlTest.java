import java.sql.*;

public class sqlTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/ems2604";
        String user = "root";
        String password = "my-secret-pw";

        String sql = "SELECT * FROM practice";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                System.out.println("SQL実行成功: " + rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}