package ch26_socket.simpleGUI.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class leaveRoom<T> {
	private String resource;
	private T body;
	
}