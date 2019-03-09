package com.vehicle.movement.vehiclemovement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // enables WebSocket message handling, backed by a message broker.
//enables a configuration class to support WebSocket. This annotation supports message broker.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
  /**
   * two methods are defined: configureMessageBroker & registerStompEndpoints
   * 1. MessageBrokerRegistry > This class helps to register message broker
   *    enable simple broker for topic and queue conventionally:
   *    topic : Use topic when there are more than one subscribers for a message.
   *    queue : Use queue for peer to peer communication.
   *
   *    need to define an application destination name using which browser and server will communicate over WebSocket.
   *    Here we have defined app
   * @param config
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic");
    config.setApplicationDestinationPrefixes("/app");
  }

  /**
   * 2. StompEndpointRegistry
   * StompEndpointRegistry register an end point for the STOMP over WebSocket.
   * StompEndpointRegistry adds end point for STOMP communication, and enables SockJS.
   * In this way STOMP client will send the message via the URL /app/vehicle-websocket-endpoint
   * @param registry
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/vehicle-websocket-endpoint").withSockJS();
  }

}