package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerBoard {
    private int[] ports;

    public ControllerBoard(
        int[] ports  
    ){
        this.ports = ports;
    }
    
    public Joystick stick1 = new Joystick(ports[0]);
    public Joystick stick2 = new Joystick(ports[1]);
    public Joystick stick3 = new Joystick(ports[3]);
    Joystick[] sticks = {stick1, stick2, stick3};

    

}
