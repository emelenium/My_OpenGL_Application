package ru.anton.my_opengl_application.Objects;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import ru.anton.my_opengl_application.MyGLRenderer;

public class    Triangle {
    private final int mProgram;
    private final String vertexShaderCode =
            "attribute vec4 vPosition;"+
            "void main(){" +
            " gl_Position = vPosition;"+
            "}";
    private final String fragmentShaderCode =
            "precision mediump float;"+
            "uniform vec4 vColor;"+
            "    gl_FragColor = vColor;"+
            "}";

    private FloatBuffer vertexBuffer;
    //number of coordinates per vertex in this array
    static final int COORS_PER_VERTEX = 3;
    static float triangleCoors[]={//in counterclockwise other
            0.0f, 0.5f, 0.0f, //Top
            0.5f, 0.0f, 0.0f, //Bottom right
           -0.5f, 0.0f, 0.0f //Bottom left
    };
    //Set color of the Triangle with red, green, blue and alpha (opacity) value
    float TriangleColor[]={0.0f, 1.0f, 0.0f, 1.0f};
    public Triangle(){


        //initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                //number of coordinate value * 4 bytes per float
                triangleCoors.length*4
        );
        //use the device hardware's native byte other
        bb.order(ByteOrder.nativeOrder());

        //create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        //add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoors);
        //set the buffer to read the first coordinate
        vertexBuffer.position(0);
        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        //create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        //add the fragment shader to program
        GLES20.glAttachShader(mProgram,fragmentShader);

        //create OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
    }
    private int positionHandle;
    private int colorHandle;
    private final int vertexCount = triangleCoors.length / COORS_PER_VERTEX;
    private final int vertexStride = COORS_PER_VERTEX * 4;//4 Bytes per vertex

    public void draw(){
        //Add program to OpenGl ES envirament
        GLES20.glUseProgram(mProgram);
        //get a hendle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle);
        //Enable handle to the triangle vertices;
        GLES20.glVertexAttribPointer(positionHandle,COORS_PER_VERTEX,GLES20.GL_FLOAT,false,vertexStride,vertexBuffer);
        //get handle to fragment shader's v Color member
        colorHandle = GLES20.glGetUniformLocation(mProgram,"vColor");
        //set the Triangle color
        GLES20.glUniform4fv(colorHandle,1,TriangleColor,0);
        //Draw the Triangles
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vertexCount);
        //Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
    }
}
