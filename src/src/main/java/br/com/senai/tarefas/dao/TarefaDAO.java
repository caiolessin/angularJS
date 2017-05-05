package br.com.senai.tarefas.dao;

import java.util.List;

import br.com.senai.tarefas.exception.TarefasException;
import br.com.senai.tarefas.model.Tarefa;

public interface TarefaDAO {

	public Tarefa insert(Tarefa tarefa) throws TarefasException;
	
	public Tarefa update(Tarefa tarefa) throws TarefasException;
	
	public Tarefa delete(int id) throws TarefasException;
	
	public Tarefa findById(int id) throws TarefasException;
	
	public List<Tarefa> list(String param) throws TarefasException;

	public List<Tarefa> list() throws TarefasException;
}
