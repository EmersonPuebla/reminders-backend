package com.alice.reminders_backend.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

// Request DTOs (include binary data)
data class VoiceNoteRequestDto(
    val name: String? = null,
    val data: ByteArray? = null
)

data class AttachmentRequestDto(
    val name: String? = null,
    val data: ByteArray? = null
)

data class ReminderRequestDto(
    @field:NotBlank
    val title: String,
    val description: String? = null,
    @field:NotNull
    val notify: Boolean = false,
    val notifyDate: LocalDate? = null,
    @field:NotNull
    val date: LocalDate,
    val voiceNotes: List<VoiceNoteRequestDto>? = null,
    val attachments: List<AttachmentRequestDto>? = null
)

// Response DTOs (do NOT include binary data)
data class VoiceNoteResponseDto(
    val name: String? = null
)

data class AttachmentResponseDto(
    val name: String? = null
)

data class ReminderResponseDto(
    val id: Long,
    val title: String,
    val description: String? = null,
    val notify: Boolean,
    val notifyDate: LocalDate? = null,
    val date: LocalDate,
    val voiceNotes: List<VoiceNoteResponseDto>? = null,
    val attachments: List<AttachmentResponseDto>? = null
)

// Full response DTO used for GET by id (includes binary data)
data class VoiceNoteFullDto(
    val name: String? = null,
    val data: ByteArray? = null
)

data class AttachmentFullDto(
    val name: String? = null,
    val data: ByteArray? = null
)

data class ReminderFullResponseDto(
    val id: Long,
    val title: String,
    val description: String? = null,
    val notify: Boolean,
    val notifyDate: LocalDate? = null,
    val date: LocalDate,
    val voiceNotes: List<VoiceNoteFullDto>? = null,
    val attachments: List<AttachmentFullDto>? = null
)
