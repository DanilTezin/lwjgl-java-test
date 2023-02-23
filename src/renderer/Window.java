package renderer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

public class Window {

    public long windowID;
    public static String name;

    public static void create(){

    }

    public void createWindow(int width, int height, String title){
        boolean initState = GLFW.glfwInit();

        if(!initState)throw new IllegalStateException("Not create GLFW");

        windowID = GLFW.glfwCreateWindow(width, height, title, 0, 0);
        if(windowID == 0) throw new IllegalStateException();


        GLFW.glfwMakeContextCurrent(windowID);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(windowID);
        GL.createCapabilities();

        glfwSetKeyCallback(windowID, (windowId, key, scancode, action, mods)->{
            if(key == GLFW_KEY_W){
                System.out.println("sdf");
            }
        });

    }

    public void updateWindow(){
        GLFW.glfwSwapBuffers(windowID);
        GLFW.glfwPollEvents();
        GLFW.glfwSwapInterval(1);
    }

    public void closeWindow(){
        GLFW.glfwDestroyWindow(windowID);
        GLFW.glfwTerminate();
    }

    public boolean shouldClose(){
        return GLFW.glfwWindowShouldClose(windowID);
    }
}
