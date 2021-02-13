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
    private DifferentialDrive robotdrive;

    public DriveBase(DifferentialDrive robot){
        this.robotdrive = robot;
    }

    public void forward(double speed){
        robotdrive.arcadeDrive(speed, 0.0);
    }
}
