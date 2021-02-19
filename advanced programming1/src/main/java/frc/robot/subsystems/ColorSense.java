// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;
import frc.robot.Constants;

public class ColorSense extends SubsystemBase {
  //private
  private ColorSensorV3 colorsrc = new ColorSensorV3(Constants.colorsensor_port);
  private double magnitude(double red, double green, double blue)
      {return Math.sqrt(red*red + blue*blue + green*green);}
  private double Red = colorsrc.getRed();
  private double Green = colorsrc.getGreen();
  private double Blue = colorsrc.getBlue();
  private double cmag = magnitude(Red, Green, Blue);
  private double red1 = (Red/cmag);
  private double green1 = (Green/cmag);
  private double blue1 = (Blue/cmag);
  private double[] ycal = Constants.yellow;
  private double[] gcal = Constants.green;
  private double[] ccal = Constants.cyan;
  private double[] rcal = Constants.red;

  /** Creates a new ColorSense. */
  public ColorSense() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Get the raw colors from the colorsensor
   * @return an array of double, the first index being the red value, the second the green value, and the third being the blue value;
   */
  public double[] rawcolors(){
    double[] ret = {0.0, 0.0, 0.0};
    ret[0] = colorsrc.getRed();
    ret[1] = colorsrc.getGreen();
    ret[2] = colorsrc.getBlue();
    return ret;
  }

  //needs some work, and thought on what should be returned, where, and how this will integrate elsewhere
  //Unsure about if using the "double arrays" for the color calibration will work at this point, further testing needed
  public String singleColor(){
    if (red1<ycal[0] & red1>ycal[1] & green1<ycal[2] & green1>ycal[3] & blue1>ycal[4] & blue1<ycal[5]){
    return "Yellow";
    }else if (red1<gcal[0] & red1>gcal[1] & green1<gcal[2] & green1>gcal[3] & blue1<gcal[4] & blue1>gcal[5]){
    return "Green";
    }else if (red1<ccal[0] & red1>ccal[1] & green1<ccal[2] & green1>ccal[3] & blue1<ccal[4] & blue1>ccal[5]){
    return "Cyan";
    }else if (red1<rcal[0] & red1>rcal[1] & green1<rcal[2] & green1>rcal[3] & blue1<rcal[4] & blue1>rcal[5]){
    return "Red";
    }else{
    return "None";
    }
  }
}

