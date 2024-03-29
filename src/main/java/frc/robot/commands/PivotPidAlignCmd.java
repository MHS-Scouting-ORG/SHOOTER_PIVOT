// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PivotSubsystem;

public class PivotPidAlignCmd extends Command {
 PivotSubsystem pivotSubs;

  public PivotPidAlignCmd(PivotSubsystem pivotsub) {
    pivotSubs = pivotsub;
    addRequirements(pivotSubs);
  }

  @Override
  public void initialize(){
    pivotSubs.init();
    pivotSubs.enablePid();
  }

  @Override
  public void execute(){
    if(pivotSubs.returnHorizontalDist() < 1.7){
      pivotSubs.changeSetpoint(45);
    }
    else if(pivotSubs.returnHorizontalDist() > 1.7 && pivotSubs.returnHorizontalDist() < 2){
      pivotSubs.changeSetpoint(40);
    }
    else{
      pivotSubs.changeSetpoint((int)(pivotSubs.returnCalcAngle()));
    }
  }

  @Override
  public void end(boolean interrupted){
    pivotSubs.stopMotor();
  }

  @Override
  public boolean isFinished() {
    return pivotSubs.returnEncoder() == 360 - pivotSubs.returnCalcAngle();
  }
}
