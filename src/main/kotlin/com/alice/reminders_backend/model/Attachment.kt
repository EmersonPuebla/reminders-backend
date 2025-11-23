package com.alice.reminders_backend.model

import jakarta.persistence.*

@Entity
@Table(name = "attachments")
data class Attachment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String? = null,

    @Lob
    @Basic(fetch = FetchType.LAZY)
    val data: ByteArray? = null
)
