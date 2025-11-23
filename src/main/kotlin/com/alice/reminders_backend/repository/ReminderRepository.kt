package com.alice.reminders_backend.repository

import com.alice.reminders_backend.model.Reminder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReminderRepository : JpaRepository<Reminder, Long>
