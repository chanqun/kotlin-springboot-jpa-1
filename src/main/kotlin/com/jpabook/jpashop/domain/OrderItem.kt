package com.jpabook.jpashop.domain

import com.jpabook.jpashop.domain.item.Item
import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
class OrderItem(
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    var order: Order,

    var orderPrice: Int,
    var count: Int,

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long
)