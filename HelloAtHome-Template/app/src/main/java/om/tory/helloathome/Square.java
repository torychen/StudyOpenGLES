//这是OpenGL ES 简明教程中的例子，用的是 OpenGL ES 1.0
//在 OpenGL ES 2.0 中不工作。
//2018.10.18
package om.tory.helloathome;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Square {
    // Our vertices.
    private float vertices[] = {
            -1.0f, 1.0f, 0.0f, // 0, Top Left
            -1.0f, -1.0f, 0.0f, // 1, Bottom Left
            1.0f, -1.0f, 0.0f, // 2, Bottom Right
            1.0f, 1.0f, 0.0f, // 3, Top Right
    };
    // The order we like to connect them.
    private short[] indices = { 0, 1, 2, 0, 2, 3 };

    // Our vertex buffer.
    private FloatBuffer vertexBuffer;
    // Our index buffer.
    private ShortBuffer indexBuffer;

    public Square () {
        //A float is 4 bytes thus multiply 4.
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);

        vbb.order(ByteOrder.nativeOrder());

        vertexBuffer = vbb.asFloatBuffer();

        vertexBuffer.put(vertices);

        vertexBuffer.position(0);

        ByteBuffer ibb
                = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);

        gl.glEnable(GL10.GL_CULL_FACE);

        gl.glCullFace(GL10.GL_BACK);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                GL10.GL_UNSIGNED_SHORT, indexBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);








    }
}
