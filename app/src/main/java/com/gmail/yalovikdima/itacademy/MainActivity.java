package com.gmail.yalovikdima.itacademy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gmail.yalovikdima.itacademy.cw.LoginActivity;
import com.gmail.yalovikdima.itacademy.dz1.Dz1Activity;
import com.gmail.yalovikdima.itacademy.dz2.Dz2Activity;
import com.gmail.yalovikdima.itacademy.dz3.Dz3Activity;
import com.gmail.yalovikdima.itacademy.dz4.Dz4Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonDz1;
    private Button buttonDz2;
    private Button buttonDz3;
    private Button buttonDz4;
    private Button buttonLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        buttonDz1 = findViewById(R.id.dz1Button);
        buttonDz1.setOnClickListener(this);
        buttonDz2 = findViewById(R.id.dz2Button);
        buttonDz2.setOnClickListener(this);
        buttonDz3 = findViewById(R.id.dz3Button);
        buttonDz3.setOnClickListener(this);
        buttonDz4 = findViewById(R.id.dz4Button);
        buttonDz4.setOnClickListener(this);
        buttonLogin = findViewById(R.id.loginPageButton);
        buttonLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dz1Button: {
                startActivity(Dz1Activity.getIntent(this));
                break;
            }
            case R.id.dz2Button: {
                startActivity(Dz2Activity.getIntent(this));
                break;
            }
            case R.id.dz3Button: {
                startActivity(Dz3Activity.getIntent(this));
                break;
            }
            case R.id.dz4Button: {
                startActivity(Dz4Activity.getIntent(this));
                break;
            }
            case R.id.loginPageButton: {
                startActivity(LoginActivity.getIntent(this));
                break;
            }

        }
    }


}
