// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

public class RobotContainer {
  //Define controllers here
  XboxController controller1 = new XboxController(Constants.controller1_port);

  public double ControllerAxis_raw(int axis){
    return (controller1.getRawAxis(axis));
  }

  public double ControllerAxis_offset(int axis, double offset){
    return (controller1.getRawAxis(axis))+(offset);
  }

  public double ControllerAxis_multiplier(int axis, double mult){
    return (controller1.getRawAxis(axis))*(mult);
  }
  
  public double ControllerAxis_exponential(int axis, int ex){
    double raw = controller1.getRawAxis(axis);
    double ret = 1;
    for (int i=0; i<ex; i++){
      ret *= raw;
    }
    return raw;
  }
  
  
  
  







//Junk?
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
}
