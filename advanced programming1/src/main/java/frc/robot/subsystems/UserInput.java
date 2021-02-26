// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public class UserInput extends SubsystemBase {
  //declare button assignments
  private static int controllerButton_A;
  private static int controllerButton_B;
  private static int controllerButton_X;
  private static int controllerButton_Y;
  private static int controllerButton_menu;
  private static int controllerButton_home;
  //can maybe change sticks back to being in Constants, as they might be the same -> test
  public static int controllerStick_ly;
  public static int controllerStick_lx;
  public static int controllerStick_ry;
  public static int controllerStick_rx;
  public static int controllerTrigger_l;
  public static int controllerTrigger_r;
  
  //setup controllers and buttons
  public XboxController Controller = new XboxController(Constants.controller_port);
  public JoystickButton Xbutton = new JoystickButton(Controller, controllerButton_X);
  public JoystickButton Ybutton = new JoystickButton(Controller, controllerButton_Y);
  public JoystickButton Abutton = new JoystickButton(Controller, controllerButton_A);
  public JoystickButton Bbutton = new JoystickButton(Controller, controllerButton_B);
  public JoystickButton menubutton = new JoystickButton(Controller, controllerButton_menu);
  public JoystickButton homebutton = new JoystickButton(Controller, controllerButton_home);

  /**
   * THIS FUNCTION MUST BE RUN EITHER IN THE CONSTRUCTOR OF THIS CLASS OR AT ROBOTINIT, otherwise controllerbuttons will not do anything
   */
  public void setcontrolmode(){
    if(Constants.controlmode.equals("xbox")){
      controllerButton_A = Constants.x_controllerButton_A;
      controllerButton_B = Constants.x_controllerButton_B;
      controllerButton_X = Constants.x_controllerButton_X;
      controllerButton_Y = Constants.x_controllerButton_Y;
      controllerButton_menu = Constants.x_controllerButton_menu;
      controllerButton_home = Constants.x_controllerButton_home;
      controllerStick_ly = Constants.x_controllerStick_ly;
      controllerStick_lx = Constants.x_controllerStick_lx;
      controllerStick_ry = Constants.x_controllerStick_ry;
      controllerStick_rx = Constants.x_controllerStick_rx;
      controllerTrigger_l = Constants.x_controllerTrigger_l;
      controllerTrigger_r = Constants.x_controllerTrigger_r;
    }else if(Constants.controlmode.equals("logitech")){
      controllerButton_A = Constants.l_controllerButton_A;
      controllerButton_B = Constants.l_controllerButton_B;
      controllerButton_X = Constants.l_controllerButton_X;
      controllerButton_Y = Constants.l_controllerButton_Y;
      controllerButton_menu = Constants.l_controllerButton_menu;
      controllerButton_home = Constants.l_controllerButton_home;
      controllerStick_ly = Constants.l_controllerStick_ly;
      controllerStick_lx = Constants.l_controllerStick_lx;
      controllerStick_ry = Constants.l_controllerStick_ry;
      controllerStick_rx = Constants.l_controllerStick_rx;
      controllerTrigger_l = Constants.l_controllerTrigger_l;
      controllerTrigger_r = Constants.l_controllerTrigger_r;
    }
  }

  /** Creates a new UserInput. */
  public UserInput() {
    setcontrolmode();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double ControllerAxis_raw(int axis){
    return (Controller.getRawAxis(axis));
  }

  public double ControllerAxis_offset(int axis, double offset){
    return (Controller.getRawAxis(axis))+(offset);
  }

  public double ControllerAxis_multiplier(int axis, double mult){
    return (Controller.getRawAxis(axis))*(mult);
  }
  
  public double ControllerAxis_exponential(int axis, int ex){
    double raw = Controller.getRawAxis(axis);
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
    double raw = Controller.getRawAxis(axis);
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
    double raw = Controller.getRawAxis(axis);
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
