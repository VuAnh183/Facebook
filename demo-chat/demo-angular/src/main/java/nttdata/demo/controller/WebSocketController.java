package nttdata.demo.controller;

import nttdata.demo.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate template;

    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/chat.send")
    //@SendToUser("/queue/messages")
    public void sendMessage(@Payload ChatMessage message) {
        try {
            message.setType(1);
            template.convertAndSend("/queue/messages/"+ message.getRecipient(), message);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void publishMessage(ChatMessage message) {
        template.convertAndSendToUser(message.getRecipient(), "/queue/messages", message);
    }
}
