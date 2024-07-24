package nttdata.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private int type;
    private String sender;
    private String recipient;
    private String content;
}
