package com.gmail.yalovikdima.itacademy;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bnt_change;
    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnt_change = findViewById(R.id.button_change);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        bnt_change.setOnClickListener(clickListener);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String temp = textView1.getText().toString();
                textView1.setText(textView2.getText().toString());
                textView2.setText(temp);

                Drawable drawable_bnt = bnt_change.getBackground();
                Drawable drawable_tw1 = textView1.getBackground();

                bnt_change.setBackground(drawable_tw1);
                textView1.setBackground(drawable_bnt);
            }
        });

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String temp = textView1.getText().toString();
            textView1.setText(textView2.getText().toString());
            textView2.setText(temp);

            Drawable drawable_tw1 = textView1.getBackground();
            Drawable drawable_tw2 = textView2.getBackground();

            textView1.setBackground(drawable_tw2);
            textView2.setBackground(drawable_tw1);
        }
    };


    @Override
    public void onClick(View view) {
        String temp = textView1.getText().toString();
        textView1.setText(textView2.getText().toString());
        textView2.setText(temp);

        Drawable drawable_bnt = bnt_change.getBackground();
        Drawable drawable_tw2 = textView2.getBackground();

        bnt_change.setBackground(drawable_tw2);
        textView2.setBackground(drawable_bnt);
    }
}