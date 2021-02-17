// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.analog.adis16470.frc.ADIS16470_IMU;
//import edu.wpi.first.wpilibj.AnalogGyro;
//import frc.robot.Constants;

public class IMU_Gyro extends SubsystemBase {
  private ADIS16470_IMU imu = new ADIS16470_IMU();
  //private AnalogGyro imu_gyro = new AnalogGyro(Constants.gyro_port);      //<<< not sure if necessary
  
  

  /** Creates a new IMU_Gyro. */
  public IMU_Gyro() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**THIS METHOD IS MARKED AS V1 AS IT WAS COPIED FROM PREXISTING CODE AND MAY NEED CHANGES TO WORK PROPERLY IN SUBSYSTEM FORMAT    
  Returns array of doubles correstponding to the distance moved within the time period. 
  The parameter "period_time" refers to the time between periods which can be gotten with "getPeriod()" (Unable to use this method outside of Robot.java). */
  public double[] accelDist_v1(double period_time){
    double[] pos = {0.0, 0.0, 0.0};
    double accelX[] = {0,0,0,0,0,0,0,0,0,0};
    double accelY[] = {0,0,0,0,0,0,0,0,0,0};
    double accelZ[] = {0,0,0,0,0,0,0,0,0,0};
    int i = 0;
    double distanceX = 0;
    double distanceY = 0;
    double distanceZ = 0;
    double sum;
    double dt = period_time;

    //run a loop of ten
    while (i<=9){
      //fill up array with values from imu (there are ten spots and the loop runs 10 times)
      accelX[i] += imu.getAccelInstantX() * 9.8;
      accelY[i] += imu.getAccelInstantY() * 9.8;
      accelZ[i] += imu.getAccelInstantZ() * 9.8;
      //add one
      i ++;
    }

    //X-axis -> average all values and store in distanceX 
    sum = 0;
    for(int j=0; j<10; j++){
      sum += accelX[j];
    }
    sum /= 10;
    distanceX += sum * dt;

    //Y-axis -> average all values and store in distanceY
    sum = 0;
    for(int j=0; j<10; j++){
      sum += accelY[j];
    }
    sum /= 10;
    distanceY += sum * dt;

    //Z-axis -> average all values and store in disanceZ
    sum = 0;
    for(int j=0; j<10; j++){
      sum += accelZ[j];
    }
    sum /= 10;
    distanceZ += sum * dt;

    //set places in the array that will be returned equal to the values gotten above
    pos[0] = distanceX;
    pos[1] = distanceY;
    pos[2] = distanceZ;  

    //return positions
    return pos;
  }

}
