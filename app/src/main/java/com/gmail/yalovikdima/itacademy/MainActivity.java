package com.gmail.yalovikdima.itacademy;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonChange;
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonChange = findViewById(R.id.button_change);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        buttonChange.setOnClickListener(clickListener);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                changeText();
                changeColorBtnAndTxtV(textView1);
            }
        });

    }

    @Override
    public void onClick(View view) {
        changeText();
        changeColorBtnAndTxtV(textView2);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            changeText();
            changeColorTxtVAndTextV();
        }
    };


    private void changeText(){
        String temp = textView1.getText().toString();
        textView1.setText(textView2.getText().toString());
        textView2.setText(temp);
    }

    private void changeColorBtnAndTxtV(TextView textView){
        Drawable drawable_bnt = buttonChange.getBackground();
        Drawable drawable_tw1 = textView.getBackground();
        buttonChange.setBackground(drawable_tw1);
        textView.setBackground(drawable_bnt);

    }

    private  void changeColorTxtVAndTextV(){
        Drawable drawable_tw1 = textView1.getBackground();
        Drawable drawable_tw2 = textView2.getBackground();
        textView1.setBackground(drawable_tw2);
        textView2.setBackground(drawable_tw1);
    }
}
