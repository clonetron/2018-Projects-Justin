/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autonomousCommands.Default;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static DriveSubsystem driveSubsystem = new DriveSubsystem(RobotMap.fleftMotor, RobotMap.mleftMotor, RobotMap.rleftMotor,
                                                                 RobotMap.frightMotor, RobotMap.mrightMotor, RobotMap.rrightMotor);

                                                                



  /* Drive Variables */

  double speed;
  double turn;
  double leftValue;
  double rightValue;
  double forwardSpeed;
  double reverseSpeed;
  public static Encoder leftEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB);
  public static Encoder rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB);
  


  
  

  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

 
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.addDefault("Default Auto", new Default());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

 

  @Override
  public void robotPeriodic() {
  }


  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {


    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    System.out.println("Hello");

    /* Rumble Feature */
    if(OI.xbox.getRawAxis(3) > 0 || OI.xbox.getRawAxis(2) > 0){
      OI.xbox.setRumble(RumbleType.kLeftRumble, 0.3 );
      OI.xbox.setRumble(RumbleType.kRightRumble, 0.3);
    }
    else{
      OI.xbox.setRumble(RumbleType.kLeftRumble, 0 );
      OI.xbox.setRumble(RumbleType.kRightRumble, 0);
    }














    /* Drive */
    forwardSpeed = OI.xbox.getRawAxis(3);
    reverseSpeed = OI.xbox.getRawAxis(2);
    speed = forwardSpeed - reverseSpeed;
    turn = OI.xbox.getRawAxis(0);
    leftValue = speed + turn;
    rightValue = speed - turn;
    driveSubsystem.tankDrive(leftValue, rightValue);

    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
