
public class Employee extends AbstractPerson {
    private final String department;
    private final int age;
    private final String phone;
    private final String position;
    private final String gender;

    public Employee(String name, String department, int age, String phone, String position, String gender) {
        super(name);
        this.department = department;
        this.age = age;
        this.phone = phone;
        this.position = position;
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getPosition() {
        return position;
    }

    public String getGender() {
        return gender;
    }


    @Override
    public String think() {
        return "I'm a employee";
    }
}
