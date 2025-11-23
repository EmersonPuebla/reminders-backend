package com.alice.reminders_backend.service.implementation

import com.alice.reminders_backend.model.Reminder
import com.alice.reminders_backend.repository.ReminderRepository
import com.alice.reminders_backend.service.interfaces.IReminderService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReminderImpl(private val repo: ReminderRepository) : IReminderService {
    override fun findAll(): List<Reminder> = repo.findAll()

    override fun findById(id: Long): Optional<Reminder> = repo.findById(id)

    override fun create(reminder: Reminder): Reminder = repo.save(reminder)

    override fun update(id: Long, updated: Reminder): Optional<Reminder> {
        return repo.findById(id).map { existing ->
            val merged = existing.copy(
                title = updated.title,
                description = updated.description,
                notify = updated.notify,
                notifyDate = updated.notifyDate,
                date = updated.date
            )
            repo.save(merged)
        }
    }

    override fun delete(id: Long) = repo.deleteById(id)
}
