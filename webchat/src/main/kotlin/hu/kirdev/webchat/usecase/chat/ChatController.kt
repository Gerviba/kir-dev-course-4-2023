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

    @MessageMapping("/send")    // in:  /app/send
    @SendTo("/topic/messages")  // out: /topic/messages
    fun handleMessage(message: ChatMessage, @Header("simpSessionId") sessionId: String): ChatMessage {
        log.info("New message from {} ({}): {}", message.from, sessionId, message.content)
        return message
    }

    @MessageMapping("/join")     // in:  /app/join
    @SendToUser("/topic/status") // out: /user/topic/status
    fun handleJoins(join: JoinMessage): ChatMessage {
        log.info("New join from {}", join.name)

        messaging.convertAndSend("/topic/messages",
            ChatMessage("SYSTEM", "${join.name} joined the chat!")
        )

        return ChatMessage("SYSTEM", "Hey ${join.name}! Welcome to the chat demo app!")
    }

}