import java.sql.*;

public class Main {
    public static void main(String[] args) {
        EmployeeController ec = new EmployeeController();

        ec.registryEmployeeData(1,"AAA");
    }
}

class Employee{
    private int employeeId;
    private String employeeName;

    Employee(int employeeId, String employeeName){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public int getEmployeeId(){
        return this.employeeId;
    }
}

class EmployeeController{
    EmployeeService es = new EmployeeService();

    public void registryEmployeeData(int inputId, String inputName){
        es.registryEmployeeData(inputId, inputName);
    }
}

class EmployeeService{
    EmployeeRepository er = new EmployeeRepository();

    public void registryEmployeeData(int inputId, String inputName){
        if (validateEmployeeData(inputId, inputName) && checkDuplicationData(inputId)){
            er.registryEmployeeData(inputId, inputName);
        }
    }

    public boolean validateEmployeeData(int inputId, String inputName){
        boolean Isvalitation = false; 

        if (inputId != 0 && !inputName.isEmpty()){
            System.out.println("空文字チェック合格");
            Isvalitation = true;
        } else if (inputId == 0) {
            System.out.println("IDが空文字になっているか、0より大きい数字を設定してください");
        } else if (inputName.isEmpty()) {
            System.out.println("名前が空文字になっています。");
        }

        return Isvalitation;
    }

    public boolean checkDuplicationData(int inputId){
        boolean IsUniqueId = false;
        Integer targetED = er.searchEmployeeDataById(inputId);

        if (inputId != targetED || targetED == null){
            IsUniqueId = true;
        } else {
            System.out.println("同じIDの社員が既に登録されています");
        }
        
        return IsUniqueId;
    }
}

class EmployeeRepository{
    private String url = "jdbc:mysql://127.0.0.1:3306/ems2604";
    private String user = "root";
    private String password = "my-secret-pw";
    private String sql;
    private int resultId;

    public void registryEmployeeData(int inputId, String inputName){
        System.out.println("登録完了 ID: " + inputId + ",名前: " + inputName);
    }

    public int searchEmployeeDataById(int targetId){
        sql = "SELECT * FROM practice WHERE id = " + targetId;

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            resultId = rs.getInt("id");
        
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultId;
    }
}