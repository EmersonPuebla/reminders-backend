package com.alice.reminders_backend.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
/**
 * Additional optional media fields:
 * - voiceName / voiceData: optional voice note (name + blob)
 * - attachmentName / attachmentData: optional attachment (name + blob)
 */
/**
 * Reminder entity. Contains optional lists of voice notes and attachments.
 */

@Entity
@Table(name = "reminders")
data class Reminder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank
    @Column(nullable = false)
    val title: String,

    @Column(columnDefinition = "TEXT")
    val description: String? = null,

    @field:NotNull
    @Column(nullable = false)
    val notify: Boolean = false,

    val notifyDate: LocalDate? = null,

    @field:NotNull
    @Column(nullable = false)
    val date: LocalDate
    ,
    
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "reminder_id")
    val voiceNotes: MutableList<VoiceNote> = mutableListOf(),
    
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "reminder_id")
    val attachments: MutableList<Attachment> = mutableListOf()
)
