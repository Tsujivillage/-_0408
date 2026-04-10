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

    public Employee getEmployee(){
        return 
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

    public Employee searchEmployeeData(int targetId){
        Employee searchedED = er.searchEmployeeData(targetId);
        return searchedED;
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
        boolean IsDuplication = false;
        Employee targetED = er.searchEmployeeData(inputId);

        if (inputId != targetED.getEmployeeId(targetED)){
            IsDuplication = true;
        } else {
            System.out.println("同じIDの社員が既に登録されています");
        }
        
        return IsDuplication;
    }
}

class EmployeeRepository{
    private String url = "jdbc:mysql://127.0.0.1:3306/ems2604";
    private String user = "root";
    private String password = "my-secret-pw";
    private String sql;
    private Employee employeeSearchResult;
  
    public Employee getEmployeeSearchResult(Employee employeeSearchResult){
        return employeeSearchResult;
    }

    public void registryEmployeeData(int inputId, String inputName){
        System.out.println("登録完了 ID: " + inputId + ",名前: " + inputName);
    }

    public Employee searchEmployeeData(int targetId){
        sql = "SELECT * FROM practice WHERE id = " + targetId;

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while(rs.next()){
                int resultId = rs.getInt("id");
                String resultName = rs.getString("name");

                employeeSearchResult = new Employee(resultId, resultName);
            }

            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getEmployeeSearchResult(employeeSearchResult);
    }
}