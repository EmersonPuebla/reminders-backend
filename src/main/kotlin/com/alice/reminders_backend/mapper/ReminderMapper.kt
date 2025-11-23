package com.alice.reminders_backend.mapper

import com.alice.reminders_backend.dto.AttachmentFullDto
import com.alice.reminders_backend.dto.AttachmentRequestDto
import com.alice.reminders_backend.dto.AttachmentResponseDto
import com.alice.reminders_backend.dto.ReminderFullResponseDto
import com.alice.reminders_backend.dto.ReminderRequestDto
import com.alice.reminders_backend.dto.ReminderResponseDto
import com.alice.reminders_backend.dto.VoiceNoteFullDto
import com.alice.reminders_backend.dto.VoiceNoteRequestDto
import com.alice.reminders_backend.dto.VoiceNoteResponseDto
import com.alice.reminders_backend.model.Attachment
import com.alice.reminders_backend.model.Reminder
import com.alice.reminders_backend.model.VoiceNote

fun ReminderRequestDto.toEntity(): Reminder =
    Reminder(
        title = this.title,
        description = this.description,
        notify = this.notify,
        notifyDate = this.notifyDate,
        date = this.date,
        voiceNotes = this.voiceNotes?.map { VoiceNote(name = it.name, data = it.data) }?.toMutableList()
            ?: mutableListOf(),
        attachments = this.attachments?.map { Attachment(name = it.name, data = it.data) }?.toMutableList()
            ?: mutableListOf()
    )

fun Reminder.toDto(): ReminderResponseDto =
    ReminderResponseDto(
        id = this.id,
        title = this.title,
        description = this.description,
        notify = this.notify,
        notifyDate = this.notifyDate,
        date = this.date,
        voiceNotes = if (this.voiceNotes.isNotEmpty()) this.voiceNotes.map { VoiceNoteResponseDto(name = it.name) } else null,
        attachments = if (this.attachments.isNotEmpty()) this.attachments.map { AttachmentResponseDto(name = it.name) } else null
    )

fun Reminder.toFullDto(): ReminderFullResponseDto =
    ReminderFullResponseDto(
        id = this.id,
        title = this.title,
        description = this.description,
        notify = this.notify,
        notifyDate = this.notifyDate,
        date = this.date,
        voiceNotes = if (this.voiceNotes.isNotEmpty()) this.voiceNotes.map { VoiceNoteFullDto(name = it.name, data = it.data) } else null,
        attachments = if (this.attachments.isNotEmpty()) this.attachments.map { AttachmentFullDto(name = it.name, data = it.data) } else null
    )
