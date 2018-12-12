/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.omg.CORBA.portable.UnknownException;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends Command {

  double targetDirection;
  double targetDistance;
  double errorDirection;
  double errorDistance;
  double currentDistance;
  double currentDirection;

  double pDistance = 4;
  double iDistance = 0.0;
  double dDistance = 0.0;

  double leftPidOutput;
  double rightPidOutput;

  PIDOutput leftSide;
  PIDOutput rightSide;

  

  PIDSourceType LeftEncoder;
  PIDSourceType rightEncoder;
  
  PIDSource pidSourceRight;
  PIDSource pidSourceLeft;
  




  PIDController PIDLeftDistance;
  PIDController PIDRightDistance;

  

  public DriveCommand() {
    
    requires(Robot.driveSubsystem);
    
  }

  public DriveCommand(double Distance, double Direction){

    
    this.targetDistance = Distance;
    this.targetDirection = Direction;

    LeftEncoder = Robot.leftEncoder.getPIDSourceType();
    rightEncoder = Robot.rightEncoder.getPIDSourceType();

   
    pidSourceRight = new PIDSource(){
      PIDSourceType m_sourceType = PIDSourceType.kDisplacement;
    
      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
        m_sourceType = pidSource;
      }
    
      @Override
      public double pidGet() {
        return Robot.rightEncoder.getDistance();
      }
    
      @Override
      public PIDSourceType getPIDSourceType() {
        return m_sourceType;
      }
    };

    pidSourceLeft  = new PIDSource(){
      PIDSourceType m_sourceType = PIDSourceType.kDisplacement;
  
      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
        m_sourceType = pidSource;
        
      }
    
      @Override
      public double pidGet() {
        return Robot.leftEncoder.getDistance();
      }
    
      @Override
      public PIDSourceType getPIDSourceType() {
        return m_sourceType;
      }
    };
    

    leftSide = new PIDOutput(){
  
      @Override
      public void pidWrite(double output) {
        Robot.driveSubsystem.leftSide.set(output);
      }
  
    };

    rightSide = new PIDOutput(){
  
      @Override
      public void pidWrite(double output) {
        Robot.driveSubsystem.rightSide.set(output);
        
      }
  
    };
   
  
    
   PIDLeftDistance = new PIDController(pDistance, iDistance, dDistance, pidSourceLeft , leftSide);
   PIDRightDistance = new PIDController(pDistance, iDistance, dDistance, pidSourceRight, rightSide);

   PIDLeftDistance.setSetpoint(Distance);
   PIDRightDistance.setSetpoint(Distance);
   PIDLeftDistance.setAbsoluteTolerance(0.01);
   PIDRightDistance.setAbsoluteTolerance(0.01);
   PIDLeftDistance.setOutputRange(0.0, 1.0);
   PIDRightDistance.setOutputRange(0.0, 2.0);


  }
  @Override
  protected void initialize() {

    
    Robot.leftEncoder.reset();
    Robot.rightEncoder.reset();

    PIDLeftDistance.enable();
    PIDRightDistance.enable();

  }


  @Override
  protected void execute() {

   

    // currentDistance = (Robot.leftEncoder.getDistance() + Robot.rightEncoder.getDistance()) / 2;

    // errorDistance = Math.abs(targetDistance - currentDistance);

    
    // PIDLeftDistance.setSetpoint(targetDistance);
    // PIDRightDistance.setSetpoint(targetDistance);
    
    // System.out.println(PIDLeftDistance.getSetpoint());

    System.out.println("Running");

    
    System.out.println(leftPidOutput);
    System.out.println(rightPidOutput);

    

    


    




  }

  @Override
  protected boolean isFinished() {
    return PIDLeftDistance.onTarget() && PIDRightDistance.onTarget();
    
  }

  @Override
  protected void end() {
    Robot.driveSubsystem.leftSide.disable();
    Robot.driveSubsystem.rightSide.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
