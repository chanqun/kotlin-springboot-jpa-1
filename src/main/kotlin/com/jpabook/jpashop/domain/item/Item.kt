package com.jpabook.jpashop.domain.item

import com.jpabook.jpashop.domain.Category
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
open class Item (
    open var name: String? = null,
    open var print: Int? = null,
    open var stockQuantity: Int? = null,

    @ManyToMany(mappedBy = "items")
    open var categories: MutableList<Category> = mutableListOf(),

    @Id @GeneratedValue
    @Column(name = "item_id")
    open var id: Long? = null
)
