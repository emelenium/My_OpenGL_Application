package ru.anton.my_opengl_application;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class MyOpenGLView extends GLSurfaceView {/*создаём новый класс-View, наследуемый от GLSurfaceView, который
позволит рисовать с помощью OpenGL ES*/
    private final MyGLRenderer renderer;//переменная для регистрации рендера

    public MyOpenGLView(Context context) {//принимаем контекст, привязанный к жизненному циклу приложения
        super(context);
        initOpenGLView();
        // set the Renderer for drawing on the GLSuffaceView
        renderer = new MyGLRenderer();/*регистрация интерфейса слушателя GLSurfaceView.Renderer в GLSurfaceView*/
        setRenderer(renderer);
    }

    public MyOpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initOpenGLView();
        // set the Renderer for drawing on the GLSuffaceView
        renderer = new MyGLRenderer();
        setRenderer(renderer);
    }
    private void initOpenGLView(){
        //Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);

    }
}
