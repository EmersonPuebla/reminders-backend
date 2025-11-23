package com.alice.reminders_backend.controller.v1

import com.alice.reminders_backend.dto.ReminderFullResponseDto
import com.alice.reminders_backend.dto.ReminderRequestDto
import com.alice.reminders_backend.dto.ReminderResponseDto
import com.alice.reminders_backend.mapper.toDto
import com.alice.reminders_backend.mapper.toEntity
import com.alice.reminders_backend.mapper.toFullDto
import com.alice.reminders_backend.response.StandardResponse
import com.alice.reminders_backend.service.interfaces.IReminderService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/reminders")
class ReminderCtrlV1(private val service: IReminderService) {
	@GetMapping
	fun all(): ResponseEntity<StandardResponse<List<ReminderResponseDto>>> {
		val items = service.findAll().map { it.toDto() }
		val msg = "Retrieved ${items.size} reminder(s)"
		return buildOk(items, msg)
	}

	@GetMapping("/{id}")
	fun getById(@PathVariable id: Long): ResponseEntity<StandardResponse<ReminderFullResponseDto>> {
		val opt = service.findById(id)
		return opt.map { e -> buildOk(e.toFullDto(), "Reminder retrieved") }
			.orElseGet { buildNotFound("Reminder with id=$id not found") }
	}

	@PostMapping
	fun create(@Valid @RequestBody dto: ReminderRequestDto): ResponseEntity<StandardResponse<ReminderResponseDto>> {
		val saved = service.create(dto.toEntity())
		return buildCreated(saved.toDto(), "Reminder created")
	}

	@PutMapping("/{id}")
	fun update(
		@PathVariable id: Long,
		@Valid @RequestBody dto: ReminderRequestDto
	): ResponseEntity<StandardResponse<ReminderResponseDto>> {
		return service.update(id, dto.toEntity()).map { e ->
			buildOk(e.toDto(), "Reminder updated")
		}.orElseGet { buildNotFound("Reminder with id=$id not found") }
	}

	@DeleteMapping("/{id}")
	fun delete(@PathVariable id: Long): ResponseEntity<StandardResponse<Unit>> {
		val exists = service.findById(id)
		return if (exists.isPresent) {
			service.delete(id)
			buildOk(null, "Reminder deleted")
		} else {
			buildNotFound("Reminder with id=$id not found")
		}
	}

	// --- Response helpers to keep controller readable ---
	private fun <T> buildOk(data: T?, message: String): ResponseEntity<StandardResponse<T>> =
		ResponseEntity.ok(StandardResponse(success = true, message = message, data = data))

	private fun <T> buildCreated(data: T, message: String): ResponseEntity<StandardResponse<T>> =
		ResponseEntity.status(HttpStatus.CREATED).body(StandardResponse(success = true, message = message, data = data))

	private fun <T> buildNotFound(message: String): ResponseEntity<StandardResponse<T>> =
		ResponseEntity.status(HttpStatus.NOT_FOUND).body(StandardResponse(success = false, message = message))

}
