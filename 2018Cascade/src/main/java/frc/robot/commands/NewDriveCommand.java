/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

public class NewDriveCommand extends Command {

  PIDController pid;
  public double drive;
  VictorSP victor;
  Encoder encoder;
  
  public NewDriveCommand() {
    requires(Robot.driveSubsystem);

    victor = new VictorSP(8);
    encoder = new Encoder(2,3);

    pid = new PIDController(4, 0, 0, encoder, victor);


    pid.setAbsoluteTolerance(0.01);
    pid.setSetpoint(300);
    pid.setOutputRange(-1, 1);
    encoder.setPIDSourceType(PIDSourceType.kDisplacement);
    

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    pid.enable();
    encoder.reset();
    pid.setOutputRange(0.1, 0.7);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {


    


    System.out.println(encoder.pidGet());
    



  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return pid.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    victor.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
