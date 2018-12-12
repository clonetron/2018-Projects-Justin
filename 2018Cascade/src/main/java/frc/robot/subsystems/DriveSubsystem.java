/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {

  public VictorSP fleftA;
  public VictorSP mleftA;
  public VictorSP rleftA;

  public VictorSP frightA;
  public VictorSP mrightA;
  public VictorSP rrightA;

  

  

  public SpeedControllerGroup leftSide;
  public SpeedControllerGroup rightSide;
  public SpeedControllerGroup wholeRobot;

  DifferentialDrive drive;


  public DriveSubsystem(int fleft, int mleft, int rleft, int fright, int mright, int rright){
    
    fleftA = new VictorSP(fleft);
    mleftA = new VictorSP(mleft);
    rleftA = new VictorSP(rleft);
    frightA = new VictorSP(fright);
    mrightA = new VictorSP(mright);
    rrightA = new VictorSP(rright);

    leftSide = new SpeedControllerGroup(fleftA, mleftA, rleftA);
    rightSide = new SpeedControllerGroup(frightA, mrightA, rrightA);
    

    drive = new DifferentialDrive(leftSide, rightSide);

   



  }

  

  public void tankDrive(double leftSide, double rightSide){

    drive.tankDrive(leftSide, rightSide);


  
  }


 

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
