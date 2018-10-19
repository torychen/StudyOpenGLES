package om.tory.airhockey1;

import android.content.Context;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import om.tory.util.ShaderHelper;
import om.tory.util.TextResourceReader;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

class MyRenderer implements GLSurfaceView.Renderer {
    private final Context mContext;
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int BYTES_PER_FLOAT = 4;
    private  FloatBuffer vertexData;

    public MyRenderer(Context context) {
        mContext = context;
    }

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

        String vertexShaderSource = TextResourceReader.readTextFileFromResource(mContext, R.raw.simple_vertex_shader);
        String fragmentShaderSource = TextResourceReader.readTextFileFromResource(mContext, R.raw.simple_fragment_shader);

        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmenShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);
        int program;
        if (vertexShader != 0 && fragmenShader != 0) {
            program = ShaderHelper.linkProgram(vertexShader, fragmenShader);
        }

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
