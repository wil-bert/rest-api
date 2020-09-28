package models;

public class Departments {
    private String departmentName;
    private String description;
    private int numberOfEmployees;
    private int departmentId;
    private int id;

    public Departments(String departmentName,String description, int numberOfEmployees, int departmentId){
        this.departmentName = departmentName;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getId() {
        return id;
    }


}
