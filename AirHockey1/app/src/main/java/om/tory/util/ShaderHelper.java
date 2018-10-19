package om.tory.util;

import android.util.Log;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VALIDATE_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramInfoLog;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glValidateProgram;

public class ShaderHelper {
    private static final String TAG = "ShaderHelper";

    public static int compileVertexShader (String shaderCode) {
        return compileShader(GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader (String shaderCode) {
        return compileShader(GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compileShader(int type, String shaderCode) {
        final int shaderObjectID = glCreateShader(type);
        if (0 == shaderObjectID){
            if (LoggerConfig.ON) {
                Log.w(TAG, "compileShader: Could not create new shader", null);
            }

            return 0;
        }

        glShaderSource(shaderObjectID, shaderCode);

        glCompileShader(shaderObjectID);

        final int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjectID, GL_COMPILE_STATUS, compileStatus, 0);

        if (LoggerConfig.ON) {
            Log.d(TAG, "compileShader: Results of compiling source: " + "\n" + shaderCode + "\n:" +
                    glGetShaderInfoLog(shaderObjectID));
        }

        if (0 == compileStatus[0]) {
            glDeleteShader(shaderObjectID);

            if (LoggerConfig.ON) {
                Log.e(TAG, "compileShader: fail to compile shader source: ",null);
            }

            return 0;
        }

        return shaderObjectID;
    }

    public static int linkProgram(int vertexShaderID, int fragmentShaderID) {
        final int programObjectID = glCreateProgram();
        if (0 == programObjectID){
            if (LoggerConfig.ON) {
                Log.w(TAG, "compileShader: Could not create new program", null);
            }

            return 0;
        }

        glAttachShader(programObjectID, vertexShaderID);
        glAttachShader(programObjectID, fragmentShaderID);

        glLinkProgram(programObjectID);

        final int[] linkStatus = new int[1];
        glGetProgramiv(programObjectID, GL_LINK_STATUS, linkStatus, 0);
        if (LoggerConfig.ON) {
            Log.d(TAG, "compileShader: Results of link program: " + "\n" +
                    glGetProgramInfoLog(programObjectID));
        }

        if (0 == linkStatus[0]) {
            glDeleteProgram(programObjectID);

            if (LoggerConfig.ON) {
                Log.e(TAG, "compileShader: fail to link program: ",null);
            }

            return 0;
        }

        return programObjectID;

    }

    public static boolean validateProgram (int programObjectID) {
        glValidateProgram(programObjectID);
        final int[] validateStatus = new int[1];

        glGetProgramiv(programObjectID, GL_VALIDATE_STATUS, validateStatus, 0);

        Log.d(TAG, "validateProgram: Results of validating program: " + validateStatus[0] +
                "\nLog:" + glGetProgramInfoLog(programObjectID));

        return (validateStatus[0] != 0);
    }
}
