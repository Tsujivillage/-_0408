// import java.io.FileWriter;
// import java.io.PrintWriter;
// import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        EmployeeController ec = new EmployeeController();

        ec.registryEmployeeData(1,"BBB");
    }
}

class Employee{
    public int employeeId;
    public String employeeName;

    Employee(int employeeId, String employeeName){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
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
        } else {
            System.out.println("エラーだけどエラー機能は作り途中");
        }
    }

    public Employee searchEmployeeData(int targetId){
        Employee searchedED = er.searchEmployeeData(targetId);
        return searchedED;
    }

    public boolean validateEmployeeData(int inputId, String inputName){
        boolean Isvalitation = false;

        if (inputId > 0 && !inputName.isEmpty()){
            System.out.println("空文字チェック合格");
            Isvalitation = true;
        } else {
            System.out.println("IDか名前が空文字になっているようです。");
        }

        return Isvalitation;
    }

    private boolean checkDuplicationData(int inputId){
        Employee targetED = searchEmployeeData(inputId);
        boolean IsDuplication = false;

        if (inputId != targetED.employeeId){
            System.out.println("文字重複チェック合格");
            IsDuplication = true;

        } else {
            System.out.println("同じIDの社員が既に登録されています");
        }
        
        return IsDuplication;
    }
}

class EmployeeRepository{
    public void registryEmployeeData(int inputId, String inputName){
        System.out.println(inputId + " " + inputName);
    }

    public Employee searchEmployeeData(int targetId){
        //仮のEmployee型のデータを作ってます
        Employee ed = new Employee(0,"AAA");
        return ed;
    }
}