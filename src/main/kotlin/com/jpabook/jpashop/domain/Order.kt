package com.jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "orders")
class Order(
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: MutableList<OrderItem> = mutableListOf(),

    @OneToOne(fetch = LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery,

    var orderDate: LocalDateTime, // 주문시간

    @Enumerated(EnumType.STRING)
    var status: OrderStatus, // 주문상태

    @Id @GeneratedValue
    @Column(name = "order_id")
    var id: Long
) {
    //연관관계 메서드
    fun addMember(member: Member) {
        this.member = member
        member.orders.add(this)
    }

    fun addOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
        orderItem.order = this
    }

    fun addDelivery(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }
}
