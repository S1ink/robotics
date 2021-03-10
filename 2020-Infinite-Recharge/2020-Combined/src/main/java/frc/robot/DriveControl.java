//CLASS DriveControl by Sam Richter - started 2/13/21


/*Motor Map
            Front
        _____________
        |           |
        |   0   1   |
left    |           |   right
        |   2   3   |
        |___________|

            Back
*/


package frc.robot;

//imports (why is this defined lol)
//import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/*
Purpose:
The "DriveControl" class works as a general object that can be called and contains methods that correlate to actions that the drivebase will do when active
- additionally helps with organization as it is wasy to visualize the actions that the drivebase will do
*/

public class DriveControl{
//class variables>>
    private int fl_chan;
    private int fr_chan;
    private int bl_chan;
    private int br_chan;
    public SpeedController SPX_0 = new PWMVictorSPX(fl_chan);
    public SpeedController SPX_1 = new PWMVictorSPX(fr_chan);
    public SpeedController SPX_2 = new PWMVictorSPX(bl_chan);
    public SpeedController SPX_3 = new PWMVictorSPX(br_chan);
    public SpeedControllerGroup left = new SpeedControllerGroup(SPX_0, SPX_2);
    public SpeedControllerGroup right = new SpeedControllerGroup(SPX_1, SPX_3);
    public DifferentialDrive drive_main = new DifferentialDrive(left, right);

    //main constructor - defines parameters and where they go within the class
    public DriveControl(
        int front_left_channel,
        int front_right_channel, 
        int back_left_channel, 
        int back_right_channel
    ){
        this.fl_chan = front_left_channel;
        this.fr_chan = front_right_channel;
        this.bl_chan = back_left_channel;
        this.br_chan = back_right_channel;
    }

    //initiate this method in the "teleopPeriodic part of the main program"
    public void dualDrive(double left_power, double right_power){
        drive_main.tankDrive(left_power, right_power);
    }

    public void forward(double speed){
        drive_main.tankDrive(speed, speed);
    }

    public void backward(double speed){
        double invert = speed * -1;
        drive_main.tankDrive(invert, invert);
    }

    public void turn(double angle, double distance){
        //uhh...
    }
}
