package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.models.Queue;
import com.example.myapplication.models.User;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateStaffUI extends AppCompatActivity {

    Button updateStaffBtn;

    EditText username, password, repassword, email, employeeNo;

    String id,usernameVal, passwordVal, repasswordVal, emailVal, employeeNoVal;
    private JasonPlaceHolderAPI jsonPlaceHolderAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_staff_ui);

        updateStaffBtn = findViewById(R.id.update_staff_btn);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        employeeNo = findViewById(R.id.employee_no);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.confirmation_password);

        updateStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameVal = username.getText().toString();
                emailVal = email.getText().toString();
                employeeNoVal = employeeNo.getText().toString();
                passwordVal = password.getText().toString();
                repasswordVal = repassword.getText().toString();

                if (!usernameVal.equals("") && !passwordVal.equals("") && !repasswordVal.equals("") && !emailVal.equals("") && !employeeNoVal.equals("")) {
                    if (!passwordVal.equals(repasswordVal)) {
                        Toast.makeText(UpdateStaffUI.this, "Passwords are not match", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    updateStaff(id, usernameVal, emailVal, employeeNoVal, passwordVal);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void updateStaff(String id, String username, String email, String employeeNo, String password ) {

        User user = new User(id, username, email, employeeNo, password);
        Call<User> call = jsonPlaceHolderAPI.updateUser(id,user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateStaffUI.this, "Update unsuccessful", Toast.LENGTH_LONG).show();
                    System.out.println(response);
                    return;
                }

                User user = (User) response.body();
                System.out.println("user");
                System.out.println(user.getId());
                System.out.println(user.getUserName());
                System.out.println(user.getEmail());
                System.out.println(user.getType());

                Toast.makeText(UpdateStaffUI.this, "Added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateStaffUI.this, QueueCrudUI.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t);
//                Toast.makeText(UpdateQueueUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
            }
        });

    }

}
