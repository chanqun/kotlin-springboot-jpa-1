package com.jpabook.jpashop.controller

import com.jpabook.jpashop.domain.Address
import com.jpabook.jpashop.domain.Member
import com.jpabook.jpashop.service.MemberService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/new")
    fun createForm(model: Model): String {
        model["memberForm"] = MemberForm()
        return "members/createMemberForm"
    }

    @PostMapping("/new")
    fun create(form: MemberForm, result: BindingResult): String {
        // TODO kotlin @Valid 찾아보기
        if (result.hasErrors()) return "members/createMemberForm"

        val address = Address(form.city, form.street, form.zipcode)

        val member = Member(form.name!!, address)

        memberService.join(member)
        return "redirect:/"
    }
}