package hu.kirdev.webchat.usecase.chat

import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ChatController(
    private val messaging: SimpMessagingTemplate
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/chat")
    fun chatIndex(): String {
        return "chat"
    }

    // in:  /app/send (message: ChatMessage)
    // out: /topic/messages (ChatMessage)
    fun handleMessage(@Header("simpSessionId") sessionId: String) {
    }

    // in:  /app/join (join: JoinMessage)
    // out: /user/topic/status (ChatMessage)
    fun handleJoins() {
    }

}