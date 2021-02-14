// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it contains the code
 * necessary to operate a robot with tank drive.
 */
public class Robot3 extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;

  private SpeedControllerGroup m_left_drive;
  private SpeedController m_front_left;
  private int front_left_channel = 0;
  private SpeedController m_back_left;
  private int back_left_channel = 1;
  
  private SpeedControllerGroup m_right_drive;
  private SpeedController m_front_right;
  private int front_right_channel = 2;
  private SpeedController m_back_right;
  private int back_right_channel = 3;


  
  @Override
  public void robotInit() {

    //Setup the parameters for the drive motors.
    m_front_left = new PWMVictorSPX(front_left_channel);
    m_back_left = new PWMVictorSPX(back_left_channel);
    m_front_right = new PWMVictorSPX(front_right_channel);
    m_back_right = new PWMVictorSPX(back_right_channel);
    m_left_drive = new SpeedControllerGroup(m_front_left, m_back_left);
    m_right_drive = new SpeedControllerGroup(m_front_right, m_back_right);
    m_myRobot = new DifferentialDrive(m_left_drive, m_right_drive);
    m_leftStick = new Joystick(0);
    m_left_drive.setInverted(true);

  }

  @Override
  public void teleopPeriodic() {
    //deadzone and drive program
    double a = m_leftStick.getRawAxis(1);
    double b = m_leftStick.getRawAxis(3);
    if(a < 0.14 && a > -0.14)
    {
      a = 0;
    }
    if(b < 0.14 && b > -0.14)
    {
      b = 0;
    }
    m_myRobot.tankDrive(a, b);
  }
}
