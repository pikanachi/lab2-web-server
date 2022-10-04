package es.unizar.webeng.lab2

// Import the type LocalDateTime
import java.time.LocalDateTime

// Import the annotations
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

data class TimeDTO(
    val time: LocalDateTime
)

interface TimeProvider {
    fun now(): LocalDateTime
}

@Service
class TimeService: TimeProvider {
    override fun now() = LocalDateTime.now()
}

fun LocalDateTime.toDTO() = TimeDTO(time = this)

@RestController 
public class TimeController(val service: TimeProvider) {
    // Exposes the rest service at /time 
    @GetMapping("/time")
    fun time() = service.now().toDTO()
}