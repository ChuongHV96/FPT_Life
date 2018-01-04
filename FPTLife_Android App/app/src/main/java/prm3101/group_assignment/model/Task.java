package prm3101.group_assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ASUS on 09/11/2017.
 */

public class Task implements Serializable{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("stu_id")
    @Expose
    private Integer stuId;
    @SerializedName("sub_id")
    @Expose
    private Integer subId;
    @SerializedName("taskType")
    @Expose
    private Integer taskType;
    @SerializedName("subCode")
    @Expose
    private String subCode;
    @SerializedName("subName")
    @Expose
    private String subName;
    @SerializedName("dueDate")
    @Expose
    private String dueDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("detail")
    @Expose
    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", subId=" + subId +
                ", taskType=" + taskType +
                ", subCode='" + subCode + '\'' +
                ", subName='" + subName + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
