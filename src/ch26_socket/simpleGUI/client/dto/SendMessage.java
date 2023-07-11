package ch26_socket.simpleGUI.client.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SendMessage {
	private String fromUsername;
	private String toUsername;
	private String messageBody;
}