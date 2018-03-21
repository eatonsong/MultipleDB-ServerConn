package org.ssm.model;

import java.io.Serializable;
import java.util.List;

public class ShellMessage implements Serializable{
	private static final long serialVersionUID = 4435368487928624575L;
	private List<String> commands;//携带命令
	private List<String> messages;//控制台输出
	public List<String> getCommands() {
		return commands;
	}
	public void setCommands(List<String> commands) {
		this.commands = commands;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	
}
