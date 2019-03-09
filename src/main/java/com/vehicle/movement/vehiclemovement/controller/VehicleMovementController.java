package com.vehicle.movement.vehiclemovement.controller;

import com.vehicle.movement.vehiclemovement.model.Vehicle;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class VehicleMovementController {

  private static final Logger logger = Logger.getLogger(VehicleMovementController.class.getName());

  @Autowired
  ApplicationContext context;

  private static int threadNumber;

  Map<String, Vehicle> vehicles = new HashMap<>();

  static {
    threadNumber = 0;
  }

  @GetMapping(value = "/")
  public ModelAndView load() {
    return new ModelAndView("index");
  }

  /**
   * Once the request has reached to server over WebSocket, a calculation or any business logic is performed on the data and result is prepared
   * need the URL pattern where the result will be sent
   */
  @MessageMapping("/createVehicle")
  @SendTo("/topic/vehicle")
  public void createVehicle(Vehicle vehicle){
    threadNumber += 1;
    String threadName = "thread" + threadNumber;
    vehicle.setName(threadName);
    VehicleThread myThread = context.getBean(VehicleThread.class);
    myThread.createThread(vehicle);
    vehicles.put(threadName, vehicle);
  }

  @MessageMapping("/modifyVehicleDirection")
  @SendTo("/topic/vehicle")
  public void modifyVehicleDirection(Vehicle modifyVehicle){
    VehicleThread myThread = context.getBean(VehicleThread.class);
    String vehicleName = "thread" + modifyVehicle.getName();
    if (vehicles.containsKey(vehicleName)) {
      Vehicle vehicle = vehicles.get(vehicleName);
      vehicle.setDirection(modifyVehicle.getDirection());
      myThread.modifyVehicleDirection(vehicle);
      return;
    }
    logger.log(Level.INFO, "not found");
  }
}
