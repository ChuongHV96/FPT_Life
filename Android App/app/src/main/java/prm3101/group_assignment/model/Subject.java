package prm3101.group_assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ASUS on 09/11/2017.
 */

public class Subject implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subName")
    @Expose
    private String subName;
    @SerializedName("subCode")
    @Expose
    private String subCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subName='" + subName + '\'' +
                ", subCode='" + subCode + '\'' +
                '}';
    }
}
