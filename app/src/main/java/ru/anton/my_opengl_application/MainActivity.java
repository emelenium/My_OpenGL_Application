package ru.anton.my_opengl_application;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    MyOpenGLView myOpenGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        myOpenGLView = new MyOpenGLView(this);/*передаем контекст, привязанный к жизненному циклу этого приложения,
        содержит установки состояния приложения. Это позволяет вновь созданным объектам понять, что вообще происходит
        Кроме того, Context является проводником в систему, содержит информацию о среде приложения*/
        setContentView(myOpenGLView);
    }

}