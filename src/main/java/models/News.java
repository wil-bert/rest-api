package models;

public class News {
    private String generalNews;
    private String departmentNews;
    private int id;

    public News(String generalNews, String departmentNews){
        this.generalNews = generalNews;
        this.departmentNews = departmentNews;

    }

    public String getDepartmentNews() {
        return departmentNews;
    }

    public String getGeneralNews() {
        return generalNews;
    }
}
