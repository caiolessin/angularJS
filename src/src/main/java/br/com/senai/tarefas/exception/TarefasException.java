package br.com.senai.tarefas.exception;

public class TarefasException extends Exception {

	public TarefasException() {
	}

	public TarefasException(String message) {
		super(message);
	}

	public TarefasException(String message, Throwable t) {
		super(message,t);
	}

	public TarefasException(Throwable t) {
		super(t);
	}
}
