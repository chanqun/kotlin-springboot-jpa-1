package com.jpabook.jpashop.repository

import com.jpabook.jpashop.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long>