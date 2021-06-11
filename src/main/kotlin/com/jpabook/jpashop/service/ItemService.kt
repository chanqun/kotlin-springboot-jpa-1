package com.jpabook.jpashop.service

import com.jpabook.jpashop.domain.item.Item
import com.jpabook.jpashop.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ItemService @Autowired constructor(
    private val itemRepository: ItemRepository
) {

    @Transactional
    fun saveItem(item: Item) {
        itemRepository.save(item)
    }

    fun findItems(): MutableList<Item> {
        return itemRepository.findAll()
    }

    fun findOne(itemId: Long): Item {
        return itemRepository.findById(itemId).get()
    }
    
}