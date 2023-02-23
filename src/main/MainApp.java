package main;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;
import renderer.Window;
import java.nio.FloatBuffer;

public class MainApp {

    public static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data);
        buffer.flip();

        return buffer;
    }

    public static void main(String[] args) {

        //Create window
        Window window = new Window();
        window.createWindow(1280, 720, "New window");

        //Square vertex array
        float[] square_v =
                {
                        0.0f, 0.5f, 0f,
                        -1.0f, 0.5f, 0f,
                        -0.5f, -0.5f, 0f,

                        -1.0f, 0.5f, 0f,
                        -0.5f, -0.5f, 0f,
                        0.0f, -0.5f, 0f,

                };

        //Init vertex array object
        int vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);

        //Init vertex buffer object
        int vboId = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboId);



        FloatBuffer buffer = MainApp.storeDataInFloatBuffer(square_v);


        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, buffer, GL30.GL_STATIC_DRAW);
        MemoryUtil.memFree(buffer);
        GL30.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboId);


        GL30.glBindVertexArray(vaoId);


        while (!window.shouldClose()) {

            GL11.glClearColor(0, 1, 0, 1);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL30.glBindVertexArray(vaoId);
            GL30.glEnableVertexAttribArray(0);

            //Draw figure
            GL11.glDrawArrays(GL11.GL_POLYGON,0, square_v.length / 3);

            GL30.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(vaoId);

            //Update
            window.updateWindow();
        }

        window.closeWindow();
    }
}
