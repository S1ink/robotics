/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * This sample program shows how to control a motor using a joystick. In the
 * operator control part of the program, the joystick is read and the value is
 * written to the motor.
 *
 * <p>Joystick analog values range from -1 to 1 and speed controller inputs also
 * range from -1 to 1 making it easy to work together.
 */
public class Robot extends TimedRobot {
  private static final int kJoystickPort = 1;
  private Joystick m_joystick;
  TalonSRX mytalon = new TalonSRX(11);
  TalonSRX mytalon2 = new TalonSRX(12);

  @Override
  public void robotInit() {
    m_joystick = new Joystick(kJoystickPort);
    mytalon.set(ControlMode.PercentOutput, 0);
    mytalon2.set(ControlMode.PercentOutput, 0);
    m_joystick.setYChannel(1);
  }

  @Override
  public void teleopPeriodic() {
    mytalon.set(ControlMode.PercentOutput, -m_joystick.getY());
    mytalon2.set(ControlMode.PercentOutput, m_joystick.getY());
    
  }

  @Override
  public void robotPeriodic() {
    
  }
}
