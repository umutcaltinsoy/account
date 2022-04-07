package com.altinsoy.account.model

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Transaction(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String?,
    val transactionType: TransactionType? = TransactionType.INITIAL,
    val amount: BigDecimal?,
    val transactionDate: LocalDateTime?,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account

) {

    constructor(amount: BigDecimal, transactionDate: LocalDateTime, account: Account) : this(
        id = null,
        amount = amount,
        transactionDate = transactionDate,
        transactionType = TransactionType.INITIAL,
        account = account
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Transaction

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , transactionType = $transactionType , amount = $amount , transactionDate = $transactionDate )"
    }

}

enum class TransactionType {
    INITIAL, TRANSFER
}