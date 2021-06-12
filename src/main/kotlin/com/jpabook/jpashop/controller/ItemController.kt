package com.jpabook.jpashop.controller

import com.jpabook.jpashop.domain.item.Book
import com.jpabook.jpashop.service.ItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/items")
class ItemController(
    private val itemService: ItemService
) {
    @GetMapping("/new")
    fun createFrom(model: Model): String {
        model["form"] = BookForm()
        return "items/createItemForm"
    }

    @PostMapping("/new")
    fun create(form: BookForm): String {
        val book = Book.createBook(form)
        itemService.saveItem(book)

        return "redirect:/items"
    }

    @GetMapping
    fun list(model: Model): String {
        val items = itemService.findItems()
        model["items"] = items
        return "items/itemList"
    }
}