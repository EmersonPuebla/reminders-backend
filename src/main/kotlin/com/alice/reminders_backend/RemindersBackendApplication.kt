package com.alice.reminders_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RemindersBackendApplication

fun main(args: Array<String>) {
	runApplication<RemindersBackendApplication>(*args)
}
