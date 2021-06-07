package com.jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,

    @OneToMany(mappedBy = "order")
    var orderItems: MutableList<OrderItem> = mutableListOf(),

    @OneToOne
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery,

    var orderDate: LocalDateTime, // 주문시간

    @Enumerated(EnumType.STRING)
    var status: OrderStatus, // 주문상태

    @Id @GeneratedValue
    @Column(name = "order_id")
    var id: Long
)
