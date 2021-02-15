// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.analog.adis16470.frc.ADIS16470_IMU;
import edu.wpi.first.wpilibj.AnalogGyro;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.TimedRobot;

public class IMU_Gyro extends SubsystemBase {
  private ADIS16470_IMU imu = new ADIS16470_IMU();
  private AnalogGyro imu_gyro = new AnalogGyro(Constants.gyro_port);
  private double dt;
  
  

  /** Creates a new IMU_Gyro. */
  public IMU_Gyro() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public void accelDist_v1(){
    double[] pos = {0.0, 0.0, 0.0};
    double accelX[] = {0,0,0,0,0,0,0,0,0,0};
    double accelY[] = {0,0,0,0,0,0,0,0,0,0};
    double accelZ[] = {0,0,0,0,0,0,0,0,0,0};
    dt = TimedRobot.getPeriod();
    int i = 0;
    double velocityX = 0;
    double velocityY = 0;
    double velocityZ = 0;
    double distanceX = 0;
    double distanceY = 0;
    double distanceZ = 0;
    
      accelX[i] += imu.getAccelInstantX() * 9.8;
      accelY[i] += imu.getAccelInstantY() * 9.8;
      accelZ[i] += imu.getAccelInstantZ() * 9.8;

      //X-axis
      double sum = 0;
      for(int j=0; j<10; j++){
        sum += accelX[j];
      }
      sum /= 10;
      velocityX += sum * dt;

      //Y-axis
      sum = 0;
      for(int j=0; j<10; j++){
        sum += accelY[j];
      }
      sum /= 10;
      velocityY += sum * dt;

      //Z-axis
      sum = 0;
      for(int j=0; j<10; j++){
        sum += accelZ[j];
      }
      sum /= 10;
      velocityZ += sum * dt;

      //keep running loops of ten
      i ++;
      if(i>9) i = 0;

      //set places in the array that will be returned equal to the values gotten above
      pos[0] = velocityX;
      pos[1] = velocityY;
      pos[2] = velocityZ;

    return pos;
  }

}
