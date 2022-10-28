package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.database.DBHandler;
import com.example.myapplication.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginUI extends AppCompatActivity {

    Button LoginBtn;
    TextView RegisterRedirectBtn;
    TextView ForgotPasswordBtn;
    EditText username, password;

    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ui);

        LoginBtn = findViewById(R.id.login_btn);
        RegisterRedirectBtn = findViewById(R.id.regiter_link);
        ForgotPasswordBtn = findViewById(R.id.forgot_pwd);

        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);

        dbHandler = new DBHandler(LoginUI.this);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameVal = username.getText().toString();
                String pwdVal = password.getText().toString();

                if (!usernameVal.matches("") && !pwdVal.matches("")) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();

                    jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                    getUserLogin(usernameVal);


                } else{
                    Toast.makeText(LoginUI.this, "Fields are empty", Toast.LENGTH_LONG).show();
                }
            }
        });

        RegisterRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUI.this, RegisterUI.class);
                startActivity(intent);
            }
        });

        ForgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUI.this, ForgotPassword1UI.class);
                startActivity(intent);
            }
        });
    }

    private void getUserLogin(String un) {
        System.out.println(un);
        System.out.println("-------------------------");
        Call<User> call = jsonPlaceHolderAPI.getUserByID(un.trim());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginUI.this, "Username or Password Incorrect", Toast.LENGTH_LONG).show();
                    return;
                }

                User user = response.body();

               dbHandler.addUserToDB(user);

               System.out.println(user.getType());

                Toast.makeText(LoginUI.this, "Logged In", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginUI.this, HomeUI.class);
                intent.putExtra("username", user.getUserName());
                intent.putExtra("type", user.getType());
                System.out.println(user.getType());
                System.out.println("user.getType()");
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t);
                Toast.makeText(LoginUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
            }



        });
    }


    //Get-ALL Method
//    private void getUserAll() {
//        Call<List<User>> call = jsonPlaceHolderAPI.getUsers();
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(LoginUI.this, "Error" + response.code(), Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                List<User> user = response.body();
//                Toast.makeText(LoginUI.this, "called " +response.code(), Toast.LENGTH_LONG).show();
//                for( User userx :user){
//                    System.out.println(userx.getId());
//                    System.out.println(userx.getEmail());
//                    System.out.println(userx.getUserName());
//                    System.out.println(userx.getFuelType());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                System.out.println(t);
//                Toast.makeText(LoginUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
//            }
//        });
//    }


}