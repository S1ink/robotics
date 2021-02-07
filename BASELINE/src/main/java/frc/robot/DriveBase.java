package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PWMVictorSPX;

public class DriveBase extends DifferentialDrive{
    public int fl_chan;
    public int fr_chan;
    public int bl_chan;
    public int br_chan;

    private SpeedController drivefl = new PWMVictorSPX(fl_chan);
    private SpeedController drivebl = new PWMVictorSPX(bl_chan);
    private SpeedController drivefr = new PWMVictorSPX(fr_chan);
    private SpeedController drivebr = new PWMVictorSPX(br_chan);
    private SpeedControllerGroup left = new SpeedControllerGroup(drivefl, drivebl);
    private SpeedControllerGroup right = new SpeedControllerGroup(drivefr, drivebr);
    public DifferentialDrive robotdrive = new DifferentialDrive(left, right);

    public DriveBase(
        SpeedControllerGroup left, 
        SpeedControllerGroup right,
        int front_left, 
        int front_right, 
        int back_left, 
        int back_right
        ){
        super(left, right);
        this.fl_chan = front_left;
        this.fr_chan = front_right;
        this.bl_chan = back_left;
        this.br_chan = back_right;
    }

    // public DriveBase(
    //     int front_left, 
    //     int front_right, 
    //     int back_left, 
    //     int back_right//, 
    //     //DifferentialDrive drive
    // ){
    //     this.fl_chan = front_left;
    //     this.fr_chan = front_right;
    //     this.bl_chan = back_left;
    //     this.br_chan = back_right;
    //     //this.robotdrive = drive;
    //}

}
