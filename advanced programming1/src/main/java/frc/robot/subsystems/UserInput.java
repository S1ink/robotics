// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public class UserInput extends SubsystemBase {
  //setup controllers and buttons
  public XboxController xbox1 = new XboxController(Constants.xbox1_port);
  public JoystickButton xbutton = new JoystickButton(xbox1, Constants.xbox1_xbutton);
  public JoystickButton ybutton = new JoystickButton(xbox1, Constants.xbox1_ybutton);
  public JoystickButton abutton = new JoystickButton(xbox1, Constants.xbox1_abutton);
  public JoystickButton bbutton = new JoystickButton(xbox1, Constants.xbox1_bbutton);
  public JoystickButton menubutton = new JoystickButton(xbox1, Constants.xbox1_menubutton);
  public JoystickButton homebutton = new JoystickButton(xbox1, Constants.xbox1_homebutton);


  /** Creates a new UserInput. */
  public UserInput() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double ControllerAxis_raw(int axis){
    return (xbox1.getRawAxis(axis));
  }

  public double ControllerAxis_offset(int axis, double offset){
    return (xbox1.getRawAxis(axis))+(offset);
  }

  public double ControllerAxis_multiplier(int axis, double mult){
    return (xbox1.getRawAxis(axis))*(mult);
  }
  
  public double ControllerAxis_exponential(int axis, int ex){
    double raw = xbox1.getRawAxis(axis);
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
   public double OPControllerFunc1(int axis, double deadzone, double multiplier, int ex_power){
    double raw = xbox1.getRawAxis(axis);
    double threshold = Math.abs(deadzone - (int)deadzone);
    double ex = 1;
    double exa = 1;
    for (int i=0; i<ex_power; i++){
      ex *= raw;
      exa *= threshold;
    }
    double ret = Math.copySign(((Math.abs(multiplier))*(Math.copySign(Math.abs(ex), raw))-(Math.copySign(exa, raw))), multiplier*raw);
    if (Math.abs(raw)<threshold){
      ret = 0.0;
    }
    return ret;
  }

  /**THE MOTHER OF ALL CONTROLLER ADJUSTMENT FUNCTIONS. This function accomplishes the same thing as "OPControllerFunc1", 
   * except that instead of the function starting just above the deadzone, (the origin of the function is moved to where 
   * the deadzone ends) the function starts at (0,0), and all outputs that would correlate to inputs below the deadzone 
   * are just cut out. This function should be used if deadzones are wanted but the maximum output does not want to be altered.
   * Use this Desmos graph as a reference as a general representation of the behavior: https://www.desmos.com/calculator/hp7hs46fhj 
   * (to visualize, only move the "y=" slider, but not the slider for addition in the main funciton)*/
  public double OPControllerFunc2(int axis, double deadzone, double multiplier, int ex_power){
    double raw = xbox1.getRawAxis(axis);
    double threshold = Math.abs(deadzone - (int)deadzone);
    double ex = 1;
    for (int i=0; i<ex_power; i++){
      ex *= raw;
    }
    double ret = multiplier*(Math.copySign(Math.abs(ex), raw));
    if (Math.abs(raw)<threshold){
      ret = 0.0;
    }
    return ret;
  }
}