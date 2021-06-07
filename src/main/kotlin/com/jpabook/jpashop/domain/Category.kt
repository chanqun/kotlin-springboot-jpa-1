package com.jpabook.jpashop.domain

import com.jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
class Category(
    var name: String,

    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id"))
    var items: MutableList<Item> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @Id @GeneratedValue
    var parent: Category,

    @OneToMany(mappedBy = "parent")
    var child: MutableList<Category> = mutableListOf(),

    @Column(name = "category_id")
    var id: Long
)