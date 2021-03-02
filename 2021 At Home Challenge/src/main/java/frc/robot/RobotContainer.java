package frc.robot;

import frc.robot.commands.AutoLine;
import frc.robot.commands.TeleopDrive;
import frc.robot.commands.groups.Slolom;
import frc.robot.commands.SensorDebug;
import frc.robot.commands.CancelAll;
import frc.robot.subsystems.ColorSense;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.UserInput;
import frc.robot.commands.DriveMode;

public class RobotContainer {
  //automated update functions
  public static Dynamics dynamics = new Dynamics();

  //subsystem instance creation
  public static DriveTrain db_main = new DriveTrain();
  public static ColorSense colorsrc = new ColorSense();
  public static UserInput input = new UserInput();
  
  //commmand declaration
  public static Slolom slolom;
  public static AutoLine linefollow;
  public static TeleopDrive teleop_drive;
  public static SensorDebug sense_periodic;
  public static CancelAll stop;
  public static DriveMode drivemode_left;
  public static DriveMode drivemode_right;

    public RobotContainer() {
      drivemode_left = new DriveMode(true, false);
      drivemode_right = new DriveMode(false, true);
      slolom = new Slolom(null);
      teleop_drive = new TeleopDrive();
      sense_periodic = new SensorDebug();
      linefollow = new AutoLine();
      stop = new CancelAll();
      // Configure the button bindings
      configureButtonBindings();
    }

  private void configureButtonBindings() {
    input.menubutton.whenHeld(stop);
    input.leftbutton.whenPressed(drivemode_left);
    input.rightbutton.whenPressed(drivemode_right);
  }
}
