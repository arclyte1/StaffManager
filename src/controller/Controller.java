package controller;

import model.DataBase;
import model.Employee;

import java.sql.SQLException;
import java.util.List;

public class Controller {
    DataBase db = new DataBase();

    public Controller() {
        try {
            db.connect();
            db.getFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee employee) {
        db.addEmployeeToDatabase(employee);
    }

    public List<Employee> getEmployees() {
        return db.getEmployees();
    }
}
