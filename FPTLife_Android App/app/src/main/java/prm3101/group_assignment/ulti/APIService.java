package prm3101.group_assignment.ulti;

import java.util.HashMap;
import java.util.List;

import prm3101.group_assignment.model.APIMessage;
import prm3101.group_assignment.model.Instructor;
import prm3101.group_assignment.model.Role;
import prm3101.group_assignment.model.Schedule;
import prm3101.group_assignment.model.Student;
import prm3101.group_assignment.model.Subject;
import prm3101.group_assignment.model.Task;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ASUS on 21/10/2017.
 */

public interface APIService {
    @POST("login")
    Call<List<Role>> login(@Body HashMap<String, String> loginInfo);

    @POST("getStudentInfo")
    Call<List<Student>> getStudentInfo(@Body HashMap<String, String> loginInfo);

    @POST("getInsInfo")
    Call<List<Instructor>> getInsInfo(@Body HashMap<String, String> loginInfo);

    @GET("getStudentScheduleByDay")
    Call<List<Schedule>> getStudentScheduleByDay(@Query("studentId") int studentId, @Query("date") String date);

    @GET("getTeacherScheduleByDay")
    Call<List<Schedule>> getTeacherScheduleByDay(@Query("insId") int insId, @Query("date") String date);

    @GET("getStudentScheduleByWeek")
    Call<List<Schedule>> getStudentScheduleByWeek(@Query("studentId") int studentId, @Query("startDate") String startDate,
                                                  @Query("endDate") String endDate);

    @GET("getTeacherScheduleByWeek")
    Call<List<Schedule>> getTeacherScheduleByWeek(@Query("insId") int insId, @Query("startDate") String startDate,
                                                  @Query("endDate") String endDate);

    @GET("getAllTaskOfStudent")
    Call<List<Task>> getAllTaskOfStudent(@Query("studentId") int studentId);

    @GET("getAllSubjectOfStudent")
    Call<List<Subject>> getAllSubjectOfStudent(@Query("studentId") int studentId);

    @POST("addTask")
    Call<List<APIMessage>> addTask(@Body HashMap<String, String> taskInfo);
}
