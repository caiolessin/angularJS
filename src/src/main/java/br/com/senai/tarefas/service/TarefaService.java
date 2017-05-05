package br.com.senai.tarefas.service;

import java.util.List;

import br.com.senai.tarefas.dao.TarefaDAO;
import br.com.senai.tarefas.exception.TarefasException;
import br.com.senai.tarefas.factory.DAOFactory;
import br.com.senai.tarefas.model.Tarefa;

public class TarefaService {

	public Tarefa save(Tarefa tarefa) throws TarefasException {
		TarefaDAO tarefaDAO = (TarefaDAO) DAOFactory.getInstance(TarefaDAO.class);
		return tarefaDAO.insert(tarefa);
	}

	public Tarefa update(int id, Tarefa tarefa) throws TarefasException {
		tarefa.setId(id);
		TarefaDAO tarefaDAO = (TarefaDAO) DAOFactory.getInstance(TarefaDAO.class);
		return tarefaDAO.update(tarefa);
	}

	public Tarefa remove(int id) throws TarefasException {
		TarefaDAO tarefaDAO = (TarefaDAO) DAOFactory.getInstance(TarefaDAO.class);
		return tarefaDAO.delete(id);
	}

	public Tarefa findById(int id) throws TarefasException {
		TarefaDAO tarefaDAO = (TarefaDAO) DAOFactory.getInstance(TarefaDAO.class);
		return tarefaDAO.findById(id);
	}

	public List<Tarefa> search(String param) throws TarefasException {
		TarefaDAO tarefaDAO = (TarefaDAO) DAOFactory.getInstance(TarefaDAO.class);
		if (param != null && !param.trim().equals("")) {
			return tarefaDAO.list(param);
		} else {
			return tarefaDAO.list();
		}
	}
}
