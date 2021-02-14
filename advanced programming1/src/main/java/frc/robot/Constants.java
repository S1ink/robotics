// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

public final class Constants {
    //controller 1 (xbox)
    public static final int controller1_port = 0;
	public static final int c1_left_Y = 1;
    public static final int c1_right_Y = 5;
	
	//drivebase motor channels
    public static int front_left_chan = 0;
    public static int front_right_chan = 1;
    public static int back_left_chan = 2;
    public static int back_right_chan = 3;

    //colorsensor 
    public static final I2C.Port colorsensor_port = I2C.Port.kOnboard;
    public static double[] yellow = {0.582, 0.382, 0.957, 0.757, 0.082, 0.282};     //calibration arrays are in the form of the 
    public static double[] green = {0.49, 0.49, 0.86, 0.79, 0.38, 0.17};            //lowest followed by the highest 
    public static double[] cyan = {0.48, 0.18, 0.8, 0.67, 0.75, 0.4};               //values of the individual RGB('s) that make 
    public static double[] red = {0.82, 0.48, 0.8, 0.54, 0.75, 0.18};               //up the color that they are calibrating

}

//Project notes>>>
/*
- use any created command and a ".schedule()" inside the wanted robot "mode" to run the command
- Some "CommandScheduler" methods that can be run inside Robot.java include: run(), cancelAll(), etc..
*/

//Subsystems (also known by all files inside the subsystem folder):
    //DriveControl - The drive base

//Commands (also known by all the files inside the commands folder):
    //DB_TankDrive - Tankdrive command that takes in controller input from two axis
