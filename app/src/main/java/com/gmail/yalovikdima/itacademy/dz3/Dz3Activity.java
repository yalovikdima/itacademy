package com.gmail.yalovikdima.itacademy.dz3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.gmail.yalovikdima.itacademy.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class Dz3Activity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;
    Button buttonLoad;
    EditText link;
    ProgressBar progressBar;

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz3Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz3);
        init();
    }

    private void init(){
        imageView = findViewById(R.id.imageView);
        buttonLoad = findViewById(R.id.buttonLoad);
        link = findViewById(R.id.link);
        progressBar = findViewById(R.id.progressBar);
        buttonLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);
        Picasso.get().load(link.getText().toString()).transform(new CircularTransformation()).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                imageView.setImageResource(R.drawable.ic_error_black);
            }
        });
    }

    public class CircularTransformation implements Transformation {

        public CircularTransformation() {
        }

        @Override
        public Bitmap transform(final Bitmap source) {
            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

            final Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            final Canvas canvas = new Canvas(output);
            canvas.drawCircle(source.getWidth() / 2, source.getHeight() / 2, Math.min(source.getWidth() / 2, source.getHeight() / 2), paint);

            if (source != output)
                source.recycle();

            return output;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
}
