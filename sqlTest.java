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

            while(rs.next()){
                int employeeId = rs.getInt("id");
                String employeeName = rs.getString("name");

                System.out.println("社員ID: " + employeeId);
                System.out.println("社員名: " + employeeName);
            }

            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}