package com.jpabook.jpashop.repository

import com.jpabook.jpashop.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

interface OrderRepository : JpaRepository<Order, Long>

@Repository
class OrderRepositoryImpl(
    private val entityManager: EntityManager
) {
    //1. string으로 다 만드는 방법
    fun findAll2(orderSearch: OrderSearch): List<Order> {
        return entityManager.createQuery(
            "select o from Order o join o.member m" +
                    " where o.status = :status " +
                    " and m.name like :name", Order::class.java
        ).setParameter("status", orderSearch.orderStatus)
            .setParameter("name", orderSearch.memberName)
            .setMaxResults(1000)
            .resultList
    }

    //2. JPA Criteria

    //3. Query DSL
    fun findAll(orderSearch: OrderSearch) {
        //val order
    }
}