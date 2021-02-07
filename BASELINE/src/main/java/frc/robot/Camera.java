package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Camera {
        private String name;
        private int id;
        private UsbCamera camera = new UsbCamera(name, id);

    public Camera(String name, int id){
        this.name = name;
        this.id = id;
    }

    public void dashview() {
        CameraServer.getInstance().startAutomaticCapture(camera);
    }
}
