package ru.anton.my_opengl_application;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ru.anton.my_opengl_application.Objects.Triangle;

public class MyGLRenderer implements GLSurfaceView.Renderer {/* реализация интерфейса слушателя GLSurfaceView.Renderer в отднльном потоке,
который использкет три метода*/
    private Triangle mTriangle;
    public static int loadShader(int type, String shaderCode){

        //create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        //or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        //add the source code to the shader and compile it
        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        /* Метод вызывается каждый раз, когда создается GLSurfaceView. В первый раз это происходит, когда мы запускаем Activity, а затем каждый раз,
когда мы возвращаемся к Activity из режима паузы. Данный метод использует два параметра: экземпляр GL10 и EGLConfig. Экземпляр GL10 позволяет давать команды
OpenGL ES. В свою очередь, EGLConfig просто сообщает нам атрибуты поверхности, например глубину цвета. Как правило, эта информация игнорируется. Мы устано-
вим цвет фона фрэйма*/
        GLES20.glClearColor(1.0f,0.0f,1.0f,0.0f);
    //initialize a triangle
        mTriangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        /* вызывается каждый раз, когда изменяется размер поверхности. Мы получаем новую ширину и высоту поверхности в пикселах, а также
экземпляр класса GL10, если хотим дать OpenGL ES какие-либо команды*/
        GLES20.glViewport(0,0,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        /* вызывается как можно чаще потоком визуализации, который установил для нас GLSurfaceView*/
        //Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        mTriangle.draw();
    }
}
