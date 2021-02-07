/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * This is a sample program demonstrating how to use an ultrasonic sensor and
 * proportional control to maintain a set distance from an object.
 */

public class Robot extends TimedRobot {

  private static final int kUltrasonicPort = 0;

  // median filter to discard outliers; filters over 10 samples
  private final MedianFilter m_filter = new MedianFilter(10);
  public Ultrasonic ultra = new Ultrasonic(1,2);
  private final AnalogInput m_ultrasonic = new AnalogInput(kUltrasonicPort);

  @Override
  public void teleopPeriodic() {
    // sensor returns a value from 0-4095 that is scaled to inches
    // returned value is filtered with a rolling median filter, since ultrasonics
    // tend to be quite noisy and susceptible to sudden outliers
    double currentDistance = m_filter.calculate(m_ultrasonic.getValue());
    double currentDistanceCM = m_ultrasonic.getVoltage() / 0.0048828125;
    double currentDistanceIN = currentDistanceCM/2.54;
    SmartDashboard.putNumber("Inches distance",currentDistanceIN);
    SmartDashboard.putNumber("Centimeters distance",currentDistanceCM);

  }
}