package com.vehicle.movement.vehiclemovement.controller;

import com.vehicle.movement.vehiclemovement.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;


@Service
@Scope(value = "prototype")
public class VehicleThread implements Runnable {
  private static final Logger logger = Logger.getLogger(VehicleThread.class.getName());

  private volatile boolean execute;

  @Autowired
  SimpMessagingTemplate template;

  Vehicle vehicle;

  public void createThread(Vehicle vehicle) {
    this.vehicle = vehicle;
    new Thread(this, vehicle.getName()).start();
  }


  public void moveVehicle() {
    int x = vehicle.getX();
    int y = vehicle.getY();
    String direction = vehicle.getDirection();

    if (x == 1165) {
      x = 130;
    }
    if (x == 125) {
      x = 1165;
    }

    if (y == 780) {
      y = 280;
    }
    if (y == 270) {
      y = 780;
    }

    switch (direction) {
      case "north":
        vehicle.setX(x);
        vehicle.setY(y - 5);
        break;
      case "south":
        vehicle.setX(x);
        vehicle.setY(y + 5);
        break;
      case "east":
        vehicle.setX(x + 5);
        vehicle.setY(y);
        break;
      case "west":
        vehicle.setX(x - 5);
        vehicle.setY(y);
        break;
      default:
        logger.log(Level.INFO, "default");
    }
  }

  @Override
  public void run() {
    this.execute = true;
    while (this.execute) {
      try {
        moveVehicle();
        this.template.convertAndSend("/topic/vehicle", this.vehicle);
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        logger.log(Level.WARNING, "Interrupted!", e);
        Thread.currentThread().interrupt();
        this.execute = false;
      }
    }
  }

  public void modifyVehicleDirection(Vehicle modifyVehicle) {
    this.vehicle.setDirection(modifyVehicle.getDirection());
  }
}
