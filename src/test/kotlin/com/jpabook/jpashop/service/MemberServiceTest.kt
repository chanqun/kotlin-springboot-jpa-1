package com.jpabook.jpashop.service

import com.jpabook.jpashop.domain.Member
import com.jpabook.jpashop.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class MemberServiceTest @Autowired constructor(
    val memberService: MemberService,
    val memberRepository: MemberRepository
) {
    @Test
    fun `회원가입`() {
        var member = Member("sung")

        var saveId = memberService.join(member)

        assertThat(member.id).isEqualTo(saveId)
    }

    @Test
    fun `중복 회원 예외`() {
        var member = Member("sung")

        memberService.join(member)

        assertThatThrownBy{ memberService.join(member) }
            .isInstanceOf(IllegalStateException::class.java)
    }
}