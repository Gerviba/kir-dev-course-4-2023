package hu.kirdev.webchat.usecase.minecraft

import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MinecraftController(
    private val messaging: SimpMessagingTemplate
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/minecraft")
    fun minecraftIndex() = "minecraft"

    // in:  /app/minecraft/web-send (message: MinecraftChatMessage)
    // out: /topic/minecraft/mc-messages (message: MinecraftChatMessage)
    // out: /topic/minecraft/web-messages (message: MinecraftChatMessage)
    fun receiveFromWeb() {
    }

    // in:  /app/minecraft/mc-send (message: MinecraftChatMessage)
    // out: /topic/minecraft/web-messages (message: MinecraftChatMessage)
    fun receiveFromMinecraft() {
    }

    // in:  /app/minecraft/web-command (command: MinecraftCommand)
    // out: /topic/minecraft/mc-control (command: MinecraftCommand)
    fun sendCommand() {
    }

}