package prm3101.group_assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ASUS on 19/10/2017.
 */

public class Student implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("stuCode")
    @Expose
    private String stuCode;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("stuName")
    @Expose
    private String stuName;
    @SerializedName("stuPhone")
    @Expose
    private String stuPhone;
    @SerializedName("stuAddress")
    @Expose
    private String stuAddress;
    @SerializedName("stuGender")
    @Expose
    private String stuGender;
    @SerializedName("stuBirthday")
    @Expose
    private String stuBirthday;
    @SerializedName("stuEndDate")
    @Expose
    private String stuEndDate;
    @SerializedName("stuStartDate")
    @Expose
    private String stuStartDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuCode() {
        return stuCode;
    }

    public void setStuCode(String stuCode) {
        this.stuCode = stuCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public String getStuGender() {
        return stuGender;
    }

    public void setStuGender(String stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(String stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public String getStuEndDate() {
        return stuEndDate;
    }

    public void setStuEndDate(String stuEndDate) {
        this.stuEndDate = stuEndDate;
    }

    public String getStuStartDate() {
        return stuStartDate;
    }

    public void setStuStartDate(String stuStartDate) {
        this.stuStartDate = stuStartDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuCode='" + stuCode + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuAddress='" + stuAddress + '\'' +
                ", stuGender='" + stuGender + '\'' +
                ", stuBirthday='" + stuBirthday + '\'' +
                ", stuEndDate='" + stuEndDate + '\'' +
                ", stuStartDate='" + stuStartDate + '\'' +
                '}';
    }
}
