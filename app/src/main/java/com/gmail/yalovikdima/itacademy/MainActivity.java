package com.gmail.yalovikdima.itacademy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gmail.yalovikdima.itacademy.cw.LoginActivity;
import com.gmail.yalovikdima.itacademy.dz1.Dz1Activity;
import com.gmail.yalovikdima.itacademy.dz2.Dz2Activity;
import com.gmail.yalovikdima.itacademy.dz3.Dz3Activity;
import com.gmail.yalovikdima.itacademy.dz4.Dz4Activity;
import com.gmail.yalovikdima.itacademy.dz5.Dz5Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        findViewById(R.id.dz1Button).setOnClickListener(this);
        findViewById(R.id.dz2Button).setOnClickListener(this);
        findViewById(R.id.dz3Button).setOnClickListener(this);
        findViewById(R.id.dz4Button).setOnClickListener(this);
        findViewById(R.id.dz5Button).setOnClickListener(this);
        findViewById(R.id.loginPageButton).setOnClickListener(this);
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
            case R.id.dz5Button: {
                startActivity(Dz5Activity.getIntent(this));
                break;
            }
            case R.id.loginPageButton: {
                startActivity(LoginActivity.getIntent(this));
                break;
            }

        }
    }


}
