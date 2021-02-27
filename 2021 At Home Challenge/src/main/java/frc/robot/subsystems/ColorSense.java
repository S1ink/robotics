// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;
import frc.robot.Constants;

public class ColorSense extends SubsystemBase {
  //colorsensors
  private ColorSensorV3 colorsrc = new ColorSensorV3(Constants.colorsensor_port);
  //private ColorSensorV3 colorsrc2 = new ColorSensorV3(Constants.colorsensor2_port);     //WILL NEED FIXING IF THERE ARE ACTUALLY TWO COLORSENSORS

  /** Creates a new ColorSense. */
  public ColorSense() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private double magnitude(double red, double green, double blue){
    return Math.sqrt(red*red + blue*blue + green*green);
  }

  public double red(int mode){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double ret = 0;
    if(mode == 0){ret = red;}
    else if(mode == 1){ret = red/cmag;}
    return ret;
  }

  public double green(int mode){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double ret = 0;
    if(mode == 0){ret = green;}
    else if(mode == 1){ret = green/cmag;}
    return ret;  
  }

  public double blue(int mode){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double ret = 0;
    if(mode == 0){ret = blue;}
    else if(mode ==1){ret = blue/cmag;}
    return ret;
  }

  /**
   * Get the raw colors from the colorsensor
   * @param - can be 0 or 1, mode 0 returns the raw output from the colorsensor, while mode 1 returns the output after dividing the colors by the magnitude
   * @return an array of doubles, the first index being the red value, the second the green value, and the third being the blue value;
   */
  public double[] rawcolors(int mode){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double[] ret = {0.0, 0.0, 0.0};
    if(mode == 0){
    ret[0] = red;
    ret[1] = green;
    ret[2] = blue;
    }else if(mode == 1){
    ret[0] = red/cmag;
    ret[1] = green/cmag;
    ret[2] = blue/cmag;
    }
    return ret;
  }

  //WILL NEED FIXING IF THERE ARE ACTUALLY TWO COLORSENSORS
  // public double[] rawcolors2(){
  //   double[] ret = {0.0, 0.0, 0.0};
  //   ret[0] = colorsrc2.getRed();
  //   ret[1] = colorsrc2.getGreen();
  //   ret[2] = colorsrc2.getBlue();
  //   return ret;
  // }
}

