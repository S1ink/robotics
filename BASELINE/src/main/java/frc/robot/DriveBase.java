package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Joystick;

/*
Purpose:
The "DriveBase" class works as a general object that can be called and contains methods that correlate to actions that the drivebase will do when active
- additionally helps with organization as it is wasy to visualize the actions that the drivebase will do
*/

public class DriveBase{
    public int fl_chan;
    public int fr_chan;
    public int bl_chan;
    public int br_chan;

    public SpeedController drivefl = new PWMVictorSPX(fl_chan);
    public SpeedController drivebl = new PWMVictorSPX(bl_chan);
    public SpeedController drivefr = new PWMVictorSPX(fr_chan);
    public SpeedController drivebr = new PWMVictorSPX(br_chan);
    public SpeedControllerGroup left = new SpeedControllerGroup(drivefl, drivebl);
    public SpeedControllerGroup right = new SpeedControllerGroup(drivefr, drivebr);
    public DifferentialDrive robotdrive = new DifferentialDrive(left, right);

    //takes 4 motor/motor controller channels as input and creates a drivebase object
    public DriveBase(
        int front_left, 
        int front_right, 
        int back_left, 
        int back_right
        ){
        this.fl_chan = front_left;
        this.fr_chan = front_right;
        this.bl_chan = back_left;
        this.br_chan = back_right;
    }

    public void arcade_drive(double speed, double rotation){
        robotdrive.arcadeDrive(speed, rotation);
    }

}
