package com.jpabook.jpashop.repository

import com.jpabook.jpashop.domain.OrderStatus

class OrderSearch(
    val memberName: String? = null,
    val orderStatus: OrderStatus? = null
)
