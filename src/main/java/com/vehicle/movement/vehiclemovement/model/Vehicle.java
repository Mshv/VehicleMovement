package com.vehicle.movement.vehiclemovement.model;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {

  private int x;
  private int y;
  private String direction;
  private String name;

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
