package models;

public class User {
    private String name;
    private String position;
    private String role;
    private String department;
    private int id;

    public User(String name, String position, String role, String department){
        this.name = name;
        this.position = position;
        this.role = role;
        this.department = department;
    }

    public String getName() {
        return name;
    }
}
