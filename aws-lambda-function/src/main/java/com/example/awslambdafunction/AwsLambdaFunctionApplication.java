package com.example.awslambdafunction;

import com.example.awslamdafunction.model.OrderDTO;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication
public class AwsLambdaFunctionApplication {

    @Autowired
    private OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(AwsLambdaFunctionApplication.class, args);
    }

    @Bean
    public Function<String, Object> reverse() {
        return (input) -> new StringBuilder(input).reverse().toString();
    }

    @Bean
    public Supplier<List<OrderDTO>> orders() {
        return () -> orderRepository.orders();
    }

    @Bean
    public Function<String, List<OrderDTO>> findOrderByName() {
        return (input) -> orderRepository.orders().stream().filter(orderDTO -> orderDTO.getName().equals(input)).collect(Collectors.toList());
    }

}
