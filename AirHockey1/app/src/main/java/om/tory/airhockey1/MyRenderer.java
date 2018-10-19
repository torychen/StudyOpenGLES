package om.tory.airhockey1;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

class MyRenderer implements GLSurfaceView.Renderer {
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int BYTES_PER_FLOAT = 4;
    private  FloatBuffer vertexData;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        float[] tableVertices = {
              0f, 0f,
              0f, 14f,
              9f, 14f,
              9f, 0f
        };

        float[] tableVerticesWithTriangle = {
                //Triangle 1
                0f, 0f,
                9f, 14f,
                0f, 14f,
                //Triangle 2
                0f, 0f,
                9f, 0f,
                9f, 14f,
                //Line 1
                0f, 7f,
                9f, 7f,
                //Mallets
                4.5f, 2f,
                4.5f, 12
        };

        vertexData = ByteBuffer
                .allocateDirect(tableVerticesWithTriangle.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        vertexData.put(tableVerticesWithTriangle);

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        glViewport(0,0,width,height);

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
