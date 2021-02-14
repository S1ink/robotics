// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
