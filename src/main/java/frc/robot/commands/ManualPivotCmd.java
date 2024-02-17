// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PivotSubsystem;

public class ManualPivotCmd extends Command {

  PivotSubsystem pivotSub;
  DoubleSupplier moveSpeed;

  public ManualPivotCmd(PivotSubsystem pivotSubsystem, DoubleSupplier speed){
    pivotSub = pivotSubsystem;
    moveSpeed = speed;

    addRequirements(pivotSub);
  }

  @Override
  public void initialize(){
    pivotSub.disablePid();
  }

  @Override
  public void execute(){ //CHANGE THIS PLS!!!!
    if(moveSpeed.getAsDouble() > 0.4){
      pivotSub.stopMotor();
    }
    else{  
    pivotSub.setManualSpeed(moveSpeed.getAsDouble());
    }
  }

  @Override 
  public void end(boolean interrupted){
    
  }

  @Override
  public boolean isFinished(){
    return false;
  }
}