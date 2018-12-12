/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomousCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.NewDriveCommand;

public class Default extends CommandGroup {

  public Default() {

    addSequential(new NewDriveCommand());

   
  }
}
