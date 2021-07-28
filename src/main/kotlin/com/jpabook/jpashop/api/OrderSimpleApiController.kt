package com.jpabook.jpashop.api

import com.jpabook.jpashop.api.dto.SimpleOrderDto
import com.jpabook.jpashop.domain.Order
import com.jpabook.jpashop.repository.OrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * xToOne(ManyToOne, OneToOne0
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
class OrderSimpleApiController(
    private val orderRepository: OrderRepository
) {

    //@GetMapping("/api/v1/simple-orders")
    fun ovdersV1(): List<Order> {
        //무한 루프에 빠짐
        //한 방향은 json ignore 해줘야한다.
        //지연로딩이기 때문에 hibernate에서 proxy member를 생성해서 넣어주는데
        //hibernate 5 module (jackson datatype hibernate5) 쓸 수 있는데 쓰지마라
        //엔티티 노출 ㄴ
        return orderRepository.findAll()
    }

    @GetMapping("/api/v2/simple-orders")
    fun ordersV2(): List<SimpleOrderDto> {
        //lazy loading으로 인해 데이터를 너무 많이 조회함 order - delivery - member - address
        //ORDER 2개
        //N + 1 -> 1 + 회원 N + 배송 N  --> 그렇다고 EAGER을 쓰면 안 된다.
        return orderRepository.findAll().map {
            SimpleOrderDto(it.id, it.member!!.name, it.orderDate, it.status, it.delivery!!.address)
        }
    }
}
