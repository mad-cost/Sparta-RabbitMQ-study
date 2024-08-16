package com.market.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductEndpoint {

  @Value("${spring.application.name}")
  public String appName;

  // RabbitMQ에서 메시지를 수신하기 위해 사용하는 기능 / 지정된 큐로부터 메시지를 받아 처리
  @RabbitListener(queues = "${message.queue.product}")
  public void receiveMessage(String orderId){ // 큐로부터 orderId 값을 받는다
    log.info("receive OrderId: {}, appName: {}", orderId, appName);
  }

}
