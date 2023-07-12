package ch26_socket.simpleGUI.server.entity;

import java.util.List;

import ch26_socket.simpleGUI.server.ConnectedSocket;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Room {
	private String roomName;
	private String owner;
	private List<ConnectedSocket> userList;
}
