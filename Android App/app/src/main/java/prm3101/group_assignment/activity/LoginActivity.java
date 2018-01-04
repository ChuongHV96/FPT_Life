package prm3101.group_assignment.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import prm3101.group_assignment.R;
import prm3101.group_assignment.model.Instructor;
import prm3101.group_assignment.model.Role;
import prm3101.group_assignment.model.Student;
import prm3101.group_assignment.ulti.APIService;
import prm3101.group_assignment.ulti.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;
    private Button loginButton, test;
    private APIService mService;
    private List<Role> mRoles;
    private List<Student> mStudents;
    private List<Instructor> mInstructors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        test = (Button) findViewById(R.id.btn_login_v2);

        //Call Api
        mService = APIUtils.getAPIService();

        // login button event
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> loginInfo = new HashMap<String, String>();
//                loginInfo.put("username", username.getText().toString());
//                loginInfo.put("password", password.getText().toString());
//                loginInfo.put("username", "PhuongLHK");
                loginInfo.put("username", "ChuongHVSE62038");
                loginInfo.put("password", "123456");
                mService.login(loginInfo).enqueue(new Callback<List<Role>>() {
                    @Override
                    public void onResponse(Call<List<Role>> call, Response<List<Role>> response) {
                        if (response.isSuccessful()) {
                            mRoles = response.body();
                            if (mRoles.get(0).getIsTeacher() == 0) {
                                //student
                                HashMap<String, String> loginInfo = new HashMap<String, String>();
                                loginInfo.put("username", mRoles.get(0).getName());
                                loginInfo.put("password", mRoles.get(0).getPassword());
                                mService.getStudentInfo(loginInfo).enqueue(new Callback<List<Student>>() {
                                    @Override
                                    public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                                        if (response.isSuccessful()) {
                                            mStudents = response.body();
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            intent.putExtra("ROLE", (Serializable) 0);
                                            intent.putExtra("USER_INFO", (Serializable) mStudents);
                                            startActivity(intent);
                                        } else {
                                            int statusCode = response.code();
                                            // handle request errors depending on status code
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<Student>> call, Throwable t) {
                                        Log.e("LoginActivity - Fail", t.toString());
                                    }
                                });
                            } else {
                                //teacher
                                HashMap<String, String> loginInfo = new HashMap<String, String>();
                                loginInfo.put("username", mRoles.get(0).getName());
                                loginInfo.put("password", mRoles.get(0).getPassword());
                                mService.getInsInfo(loginInfo).enqueue(new Callback<List<Instructor>>() {
                                    @Override
                                    public void onResponse(Call<List<Instructor>> call, Response<List<Instructor>> response) {
                                        if (response.isSuccessful()) {
                                            mInstructors = response.body();
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            intent.putExtra("ROLE", (Serializable) 1);
                                            intent.putExtra("USER_INFO", (Serializable) mInstructors);
                                            startActivity(intent);
                                        } else {
                                            int statusCode = response.code();
                                            // handle request errors depending on status code
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<Instructor>> call, Throwable t) {
                                        Log.e("LoginActivity - Fail", t.toString());
                                    }
                                });
                            }
                        } else {
                            int statusCode = response.code();
                            // handle request errors depending on status code
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Role>> call, Throwable t) {
                        //Dialog
                        final Dialog dialog = new Dialog(LoginActivity.this); // Context, this, etc.
                        dialog.setContentView(R.layout.dialog_fail);
                        dialog.setTitle("Fail");
                        dialog.show();
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                username.setText("");
                                password.setText("");
                            }
                        });
                        Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.hide();
                                username.setText("");
                                password.setText("");
                            }
                        });
                        Log.e("LoginActivity - Fail", t.toString());
                    }
                });
            }
        });
    }



}
