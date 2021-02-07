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
    int jport1 = 0;
    int jport2 = 1;

  //Initializaion of components
    Joystick Stick1 = new Joystick(jport1);
    Joystick Stick2 = new Joystick(jport2);
    DriveBase robotdrive = new DriveBase(0, 1, 2, 3);
    
    

    



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
    double S1X = Stick1.getX();
    double S1Y = Stick1.getY();
    robotdrive.arcade_drive(S1X, S1Y);
  }
    
  //when robot is in autonomous mode...
  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }
}