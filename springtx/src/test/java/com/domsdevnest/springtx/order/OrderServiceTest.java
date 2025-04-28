package com.domsdevnest.springtx.order;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;


    @Test
    void complete() throws NotEnoughMoneyException {
        Order order = new Order();
        order.setOrderStatus("정상");

        orderService.order(order);

        Order findOrder = orderRepository.findById(order.getId()).get();
        assertThat(findOrder.getPayStatus()).isEqualTo("완료");
    }

    @Test
    void runtimeException() {
        Order order = new Order();
        order.setOrderStatus("예외");

        assertThatThrownBy(() -> orderService.order(order))
                .isInstanceOf(RuntimeException.class);

        Optional<Order> orderOptional = orderRepository.findById(order.getId());
        assertThat(orderOptional.isEmpty()).isTrue();
    }

    @Test
    void bizException() {
        Order order = new Order();
        order.setOrderStatus("잔고부족");

        try {
            orderService.order(order);
            fail("잔고부족 예외가 발생해야 합니다.");
        } catch (NotEnoughMoneyException e) {
            log.info("고객에게 잔고 부족을 알리고 별도의 계좌 안내");
        }

        Order findOrder = orderRepository.findById(order.getId()).get();
        assertThat(findOrder.getPayStatus()).isEqualTo("대기");
    }
}
