// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoMove;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Slolom extends SequentialCommandGroup {
  //private double half = 0.5, full = 1, quat = 0.25, three = 0.75;
  
  /** Creates a new Slolom. */
  public Slolom(DriveTrain db_main) {
    // Add your commands in the addCommands() call, e.g.
    // TO MAKE THE ROBOT MOVE, SIMPLY ADD A NEW "AutoMove(left speed, right speed)" AND SPECIFY THE WHEEL SPEEDS. THEN ADD ".withTimeout(seconds)" TO MAKE IT LAST FOR HOWEVER LONG
    addCommands(
      //move forward (start line)
      new AutoMove(0.5, 0.5).withTimeout(2.5), 
      //turn left (arc not pivot)
      new AutoMove(0.25, 0.75).withTimeout(2),
      //turn right (arc not pivot)
      new AutoMove(0.75, 0.25).withTimeout(2),
      //move forward
      new AutoMove(0.75, 0.75).withTimeout(4),
      //turn right (arc not pivot)
      new AutoMove(0.75, 0.25).withTimeout(5),
      //move forward 
      new AutoMove(0.75, 0.75).withTimeout(4),
      //turn righ (arc not pivot)
      new AutoMove(0.75, 0.25).withTimeout(2),
      //turn left (arc not pivot)
      new AutoMove(0.25, 0.75).withTimeout(2),
      //move forward (finish line)
      new AutoMove(0.5, 0.5).withTimeout(2.5)
    );
  }
}
