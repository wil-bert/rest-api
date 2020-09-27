package models;

public class Departments {
    private String departmentName;
    private String description;
    private int numberOfEmployees;
    private int departmentId;

    public Departments(String departmentName,String description, int numberOfEmployees, int departmentId){
        this.departmentName = departmentName;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
        this.departmentId = departmentId;
    }


}
