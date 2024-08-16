package com.market.order;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderApplicationQueueConfig {

  @Value("${message.exchange}")
  private String exchange;

  @Value("${message.queue.product}")
  private String queueProduct;

  @Value("${message.queue.payment}")
  private String queuePayment;

  // exchange 생성
  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(exchange);
  }

  // queue.product 생성
  @Bean
  public Queue queueProduct() {
    return new Queue(queueProduct);
  }

  // queue.payment 생성
  @Bean
  public Queue queuePayment() {
    return new Queue(queuePayment);
  }

  // binding: exchange 에서 queue 로 이동하는 통로 / 큐와 바인딩의 이름(라우팅 키)을 일치시켜서 헷갈리지 않게 하자!
  @Bean
  public Binding bindingProduct() {
    // BindingBuilder 를 사용하여 바인딩 생성
    return BindingBuilder.bind(
            // 여기서 생성한 바인드를 어느 큐에 반환하는지
            queueProduct())
            // 큐와 연결할(바인딩 할) exchange(교환기) 설정
            .to(exchange())
            .with(queueProduct); // 라우팅 키: market.product 라는 이름으로 바인딩 생성
  }

  @Bean
  public Binding bindingPayment() {
    return BindingBuilder.bind(queuePayment())
            .to(exchange())
            .with(queuePayment);
  }
}