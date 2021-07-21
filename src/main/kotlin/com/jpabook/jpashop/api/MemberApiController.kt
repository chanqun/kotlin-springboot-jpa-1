package com.jpabook.jpashop.api

import com.jpabook.jpashop.api.dto.CreateMemberRequest
import com.jpabook.jpashop.api.dto.CreateMemberResponse
import com.jpabook.jpashop.api.dto.UpdateMemberRequest
import com.jpabook.jpashop.api.dto.UpdateMemberResponse
import com.jpabook.jpashop.domain.Member
import com.jpabook.jpashop.service.MemberService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class MemberApiController(
    private val memberService: MemberService
) {

    @PostMapping("/api/v1/members")
    fun saveMemberV1(@RequestBody @Valid member: Member): CreateMemberResponse {
        //entity에 valid를 걸어 놓으면 spec이 변경되거나 할 때 변경 될 부분이 많다.
        //api spec이 변경 될 수 있음
        //entity가 변한다고 api spec이 변경 된다는 것은 말도 안돼 -> 따로 DTO를 만들자
        val id = memberService.join(member)

        return CreateMemberResponse(id)
    }

    @PostMapping("/api/v2/members")
    fun saveMemberV2(@RequestBody @Valid req: CreateMemberRequest): CreateMemberResponse {
        val member = Member(req.name)

        val id = memberService.join(member)

        return CreateMemberResponse(id)
    }

    @PutMapping("/api/v2/members/{id}")
    fun updateMember(@PathVariable id: Long, @RequestBody @Valid req: UpdateMemberRequest): UpdateMemberResponse {
        memberService.update(id, req.name)
        
        val findMember = memberService.findOne(id)

        return UpdateMemberResponse(findMember.id!!, findMember.name)
    }
}
