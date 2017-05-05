package br.com.senai.tarefas.factory;

import br.com.senai.tarefas.dao.jpa.JPATarefaDAO;

public class DAOFactory {

	public static Object getInstance(Class<?> clazz){
		if("TarefaDAO".equals(clazz.getSimpleName())){
			return new JPATarefaDAO();
		}
		return null;
	}
}
