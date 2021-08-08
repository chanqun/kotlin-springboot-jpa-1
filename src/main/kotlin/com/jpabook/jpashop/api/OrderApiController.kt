package com.jpabook.jpashop.api

import com.jpabook.jpashop.api.dto.OrderDto
import com.jpabook.jpashop.api.dto.OrderItemDto
import com.jpabook.jpashop.domain.Order
import com.jpabook.jpashop.repository.OrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderApiController(
    private val orderRepository: OrderRepository
) {

    //@GetMapping("/api/v1/orders")
    fun ordersV1(): List<Order> {
        //양방향은 꼭 찾아서 json ignore 추가 해주어야한다.
        val orders = orderRepository.findAll()

        orders.forEach {
            it.member!!.name
            it.delivery!!.address
            val orderItems = it.orderItems

            orderItems.forEach { o ->
                o.item!!.name
            }
        }

        return orders
    }

    @GetMapping("/api/v2/orders")
    fun ordersV2(): List<OrderDto> {
        val orders = orderRepository.findAll()

        return orders.map {
            OrderDto(
                it.id!!,
                it.member!!.name,
                it.orderDate,
                it.status!!,
                it.delivery!!.address!!,
                OrderItemDto.of(it.orderItems)
            )
        }
    }

    @GetMapping("/api/v3/orders")
    fun ordersV3(): List<OrderDto> {
        // 1대다에서 다에 맞추기 때문에 데이터를 뻥튀기해서 준다.
        // distinct를 넣어주면 중복이 제거 된다.
        // db에서 가져온 결과는 똑같지만 jpa에서 자체적으로 order가 같은 값이면 중복을 제거해준다.
        //
        // 1대다를 fetch join 하면
        // 단점!!!! - 페이징 불가능 -> 전부 올리고 페이징을 처리한다.;;;;;
        // 컬렉션 페치 조인은 하나만 사용하자
        val orders = orderRepository.findAllWithItem()

        return orders.map {
            OrderDto(
                it.id!!,
                it.member!!.name,
                it.orderDate,
                it.status!!,
                it.delivery!!.address!!,
                OrderItemDto.of(it.orderItems)
            )
        }
    }
}
