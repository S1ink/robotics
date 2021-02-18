// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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
    return ret;
  }

  /**THE MOTHER OF ALL CONTROLLER ADJUSTMENT FUNCTIONS. Takes in the axis of the desired stick, 
   * the deadzone value (if this number is bigger than 1 it will automatically cut off the "mixed number part"so it will be less than 1), 
   * the multiplier value (if this is negative then it inverts the output), and the power (the number of times the raw stick input
   * will be multiplied by itself to simulate more fine control at lower values, but less control at higher ones). At the moment 
   * this fuction is set up so that instead of cutting off all output values (output) bellow the deadzone (input), the first value above the deadzone (output)
   * range will be very close to zero (simulating the "graph" of the function of the IO in this case being raised above the horizontal 
   * line that is the deadzone). Use this Desmos graph as a reference as a general representation of the behavior: https://www.desmos.com/calculator/hp7hs46fhj */
  public double ControllerAxis_opfunc(int axis, double deadzone, double multiplier, int power){
    double raw = controller1.getRawAxis(axis);
    double threshold = Math.abs(deadzone - (int)deadzone);
    double ex = 1;
    double exa = 1;
    for (int i=0; i<power; i++){
      ex *= raw;
    }
    for (int i=0; i<power; i++){
      exa *= threshold;
    }
    double ret = multiplier*(Math.copySign(Math.abs(ex), raw))-(Math.copySign(exa, raw));
    //double ret = Math.copySign(((Math.abs(multiplier))*(Math.copySign(Math.abs(ex), raw))-(Math.copySign(exa, raw))), multiplier);
    if (Math.abs(raw)<threshold){
      ret = 0.0;
    }
    return ret;
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
