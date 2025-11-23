package com.alice.reminders_backend.service.interfaces

import com.alice.reminders_backend.model.Reminder
import java.util.*

interface IReminderService {
    fun findAll(): List<Reminder>
    fun findById(id: Long): Optional<Reminder>
    fun create(reminder: Reminder): Reminder
    fun update(id: Long, updated: Reminder): Optional<Reminder>
    fun delete(id: Long)
}
