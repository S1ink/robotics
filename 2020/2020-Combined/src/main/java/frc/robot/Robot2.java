package frc.robot;

//imports>>>
//general
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

//CODE>>>
public class Robot2 extends TimedRobot {
  //VARS
    int fl_chan = 0;
    int fr_chan = 1;
    int bl_chan = 2;
    int br_chan = 3;
    int jport1 = 0;
    int jport2 = 1;

  //Initializaion of components
    Joystick Stick1 = new Joystick(jport1);
    Joystick Stick2 = new Joystick(jport2);
    SpeedController drivefl = new PWMVictorSPX(fl_chan);
    SpeedController drivebl = new PWMVictorSPX(bl_chan);
    SpeedController drivefr = new PWMVictorSPX(fr_chan);
    SpeedController drivebr = new PWMVictorSPX(br_chan);
    SpeedControllerGroup left = new SpeedControllerGroup(drivefl, drivebl);
    SpeedControllerGroup right = new SpeedControllerGroup(drivefr, drivebr);
    DifferentialDrive robotdrive = new DifferentialDrive(left, right);
    
    double S1X = Stick1.getX();
    double S1Y = Stick1.getY();
    double S1t = Stick1.getPort();

    DriveBase robot = new DriveBase(robotdrive);

    



//code that runs when during defined times>>>
  //when robot is started...
  @Override
  public void robotInit() {
    
  }

  @Override
  public void robotPeriodic() {
    
  }

  //when robot is in normal control mode...
  @Override
  public void teleopPeriodic() {
    
  }
    
  //when robot is in autonomous mode...
  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void testPeriodic(){
    System.out.println(S1t);
  }
}