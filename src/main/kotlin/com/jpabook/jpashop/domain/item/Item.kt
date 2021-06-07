package com.jpabook.jpashop.domain.item

import com.jpabook.jpashop.domain.Category
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
open class Item (
    var name: String? = null,
    var print: Int? = null,
    var stockQuantity: Int? = null,

    @ManyToMany(mappedBy = "items")
    var categories: MutableList<Category> = mutableListOf(),

    @Id @GeneratedValue
    @Column("item_id")
    var id: Long? = null
)
