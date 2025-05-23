package com.domsdevnest.springtx.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String orderStatus; //정상, 예외, 잔고부족
    private String payStatus; //대기 , 완료


}
