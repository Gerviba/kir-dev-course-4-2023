package hu.kirdev.webchat.usecase.dice

import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.time.Instant

@Service
class DiceService(
    private val messaging: SimpMessagingTemplate
) {

    private val secureRandom = SecureRandom()

    @Scheduled(fixedRate = 1000 * 5)
    fun sendDicePeriodically() {
        messaging.convertAndSend("/topic/dice", D6Status(
            value = secureRandom.nextInt(1, 7),
            time = Instant.now().toString())
        )
    }

}