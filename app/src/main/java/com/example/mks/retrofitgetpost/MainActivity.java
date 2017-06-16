package com.example.mks.retrofitgetpost;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mks.retrofitgetpost.API.APIService;
import com.example.mks.retrofitgetpost.API.APIurl;
import com.example.mks.retrofitgetpost.model.Students;
import com.google.gson.GsonBuilder;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName,etDepartment;
    private Button btInsert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName= (EditText) findViewById(R.id.nameEt);
        etDepartment= (EditText) findViewById(R.id.departmentEt);
        btInsert= (Button) findViewById(R.id.insertBt);

        btInsert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        signupstudents();
    }

    private void signupstudents(){
        //defining  a progress dialog to show while signup
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.show();

        //getting user values
        String name=etName.getText().toString().trim();
        String department=etDepartment.getText().toString().trim();

        //create rettrofit object
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIurl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //define retrofit api service
        APIService apiService=retrofit.create(APIService.class);

        //create user object to pass it with the call
        Students students=new Students(name,department);

        //define call
        Call<Result>call=apiService.createStudent(
                students.getName(),
                students.getDepartment()
        );

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //close progress dialog
                progressDialog.dismiss();
                //display message
                Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}
