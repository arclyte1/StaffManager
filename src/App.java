import gui.EmployeeAddForm;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        EmployeeAddForm employeeAddForm = new EmployeeAddForm();
        JFrame frame = new JFrame("EmployeeFormTest");
        frame.setContentPane(employeeAddForm.getEmployeeAddPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
