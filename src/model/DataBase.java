package model;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DataBase {
    private List<Employee> employees;
    private Connection connection;

    public DataBase() {
        this.employees = new LinkedList<Employee>();
    }

    private void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public void connect() throws SQLException {
        if (connection != null)
            return;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:sqlite:db/staff.db";
        connection = DriverManager.getConnection(url);
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void count() throws SQLException {
        String sql = "SELECT count(*) from employee";
        PreparedStatement sqlStmt = connection.prepareStatement(sql);
        ResultSet checkResult = sqlStmt.executeQuery();

        checkResult.next();
        int count = checkResult.getInt(1);

        System.out.println("Count for person is " + count + ".");

        sqlStmt.close();
    }

    public void getFromDatabase() {
        employees.clear();

        String sql = "SELECT * FROM employee";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                Employee temp = new Employee(set.getString("name"), set.getString("department"), set.getInt("age"),
                        set.getString("phone"), set.getString("position"), set.getString("gender"));
                addEmployee(temp);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployeeToDatabase(Employee employee) {
        String name = employee.getName();
        int age = employee.getAge();
        String department = employee.getDepartment();
        String phone = employee.getPhone();
        String position = employee.getPosition();
        String gender = employee.getGender();

        String sql = "Insert into employee(name,department,age,phone,position,gender) values ('"+name+"','"+department+"','"+age+"','"+phone+"','"+position+"','"+gender+"')";
        System.out.println("SQL = " + sql);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getFromDatabase();
    }
}
