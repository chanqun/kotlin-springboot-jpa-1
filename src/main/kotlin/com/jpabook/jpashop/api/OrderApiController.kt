package com.jpabook.jpashop.api

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

            orderItems.forEach{
                o -> o.item!!.name
            }
        }

        return orders
    }
}