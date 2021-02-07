package frc.robot;
//imports>>>
  //general
  import edu.wpi.first.wpilibj.TimedRobot;
  import edu.wpi.first.wpilibj.Joystick;

//CODE>>>
public class Robot2 extends TimedRobot {
  //VARS
    int fl_chan = 0;
    int fr_chan = 1;
    int bl_chan = 2;
    int br_chan = 3;
    int joyport = 0;

  //Initializaion of components
    Joystick joystick = new Joystick(joyport);
    DriveBase drive = new DriveBase(0, 0, fl_chan, fr_chan, bl_chan, br_chan);
    double conx = joystick.getX();
    double cony = joystick.getY();
    drive.arcadeDrive(conx, cony);

    Camera cambruh = new Camera("cam", 0);
    cambruh.dashview();



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
    drive.arcadeDrive(conx, cony);
  }

  //when robot is in autonomous mode...
  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }
}