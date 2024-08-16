package com.market.order;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  @Value("${message.queue.product}")
  private String productQueue;

  @Value("${message.queue.payment}")
  private String paymentQueue;

  // RabbitTemplate: RabbitMQ로 요청을 보낼 때 사용
  private final RabbitTemplate rabbitTemplate;

  public void createOrder(String orderId) {
    // marker.product 큐로 orderId 전송
    rabbitTemplate.convertAndSend(productQueue, orderId);
    // marker.payment 큐로 orderId 전송
    rabbitTemplate.convertAndSend(paymentQueue, orderId);
  }

}