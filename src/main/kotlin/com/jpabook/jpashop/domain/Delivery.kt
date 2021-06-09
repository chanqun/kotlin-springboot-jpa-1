package com.jpabook.jpashop.domain

import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
class Delivery(
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    var order: Order,

    @Embedded
    var address: Address,

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus,

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    var id: Long,
)