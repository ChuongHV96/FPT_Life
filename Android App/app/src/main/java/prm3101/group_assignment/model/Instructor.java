package prm3101.group_assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ASUS on 01/11/2017.
 */

public class Instructor implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("insCode")
    @Expose
    private String insCode;
    @SerializedName("insName")
    @Expose
    private String insName;
    @SerializedName("insPhone")
    @Expose
    private String insPhone;
    @SerializedName("insAddress")
    @Expose
    private String insAddress;
    @SerializedName("insGender")
    @Expose
    private String insGender;
    @SerializedName("insBirthDay")
    @Expose
    private String insBirthDay;
    @SerializedName("insStartDate")
    @Expose
    private String insStartDate;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInsCode() {
        return insCode;
    }

    public void setInsCode(String insCode) {
        this.insCode = insCode;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

    public String getInsPhone() {
        return insPhone;
    }

    public void setInsPhone(String insPhone) {
        this.insPhone = insPhone;
    }

    public String getInsAddress() {
        return insAddress;
    }

    public void setInsAddress(String insAddress) {
        this.insAddress = insAddress;
    }

    public String getInsGender() {
        return insGender;
    }

    public void setInsGender(String insGender) {
        this.insGender = insGender;
    }

    public String getInsBirthDay() {
        return insBirthDay;
    }

    public void setInsBirthDay(String insBirthDay) {
        this.insBirthDay = insBirthDay;
    }

    public String getInsStartDate() {
        return insStartDate;
    }

    public void setInsStartDate(String insStartDate) {
        this.insStartDate = insStartDate;
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

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", insCode='" + insCode + '\'' +
                ", insName='" + insName + '\'' +
                ", insPhone='" + insPhone + '\'' +
                ", insAddress='" + insAddress + '\'' +
                ", insGender='" + insGender + '\'' +
                ", insBirthDay='" + insBirthDay + '\'' +
                ", insStartDate='" + insStartDate + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
