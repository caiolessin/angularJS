package br.com.senai.tarefas.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.senai.tarefas.dao.TarefaDAO;
import br.com.senai.tarefas.exception.TarefasException;
import br.com.senai.tarefas.model.Tarefa;

public class JPATarefaDAO extends JPAConnection implements TarefaDAO {

	@Override
	public Tarefa insert(Tarefa tarefa) throws TarefasException {
		try {
			EntityManager em = getEntityManager();
			beginTransaction();
			em.persist(tarefa);
			commit();
		} catch (Exception e) {
			rollback();
			throw new TarefasException(e);
		} finally {
			closeEntityManager();
		}
		return tarefa;
	}

	@Override
	public Tarefa update(Tarefa tarefa) throws TarefasException {
		try {
			EntityManager em = getEntityManager();
			beginTransaction();
			tarefa = em.merge(tarefa);
			commit();
		} catch (Exception e) {
			rollback();
			throw new TarefasException(e);
		} finally {
			closeEntityManager();
		}
		return tarefa;
	}

	@Override
	public Tarefa delete(int id) throws TarefasException {
		Tarefa tarefa = null;
		try {
			EntityManager em = getEntityManager();
			beginTransaction();
			tarefa = em.find(Tarefa.class, id);
			em.remove(tarefa);
			commit();
		} catch (Exception e) {
			rollback();
			throw new TarefasException(e);
		} finally {
			closeEntityManager();
		}
		return tarefa;
	}

	@Override
	public Tarefa findById(int id) throws TarefasException {
		Tarefa tarefa = null;
		try {
			EntityManager em = getEntityManager();
			tarefa = em.find(Tarefa.class, id);
		} catch (Exception e) {
			throw new TarefasException(e);
		} finally {
			closeEntityManager();
		}
		return tarefa;
	}

	@Override
	public List<Tarefa> list() throws TarefasException {
		List<Tarefa> tarefas = null;
		try {
			EntityManager em = getEntityManager();
			TypedQuery<Tarefa> query = em.createQuery("SELECT t FROM Tarefa t", Tarefa.class);
			tarefas = query.getResultList();
		} catch (Exception e) {
			throw new TarefasException(e);
		} finally {
			closeEntityManager();
		}
		return tarefas;
	}

	@Override
	public List<Tarefa> list(String param) throws TarefasException {
		List<Tarefa> tarefas = null;
		try {
			EntityManager em = getEntityManager();
			TypedQuery<Tarefa> query = em.createQuery("SELECT t FROM Tarefa t WHERE upper(t.resumo) LIKE upper(:resumo) OR upper(t.descricao) LIKE upper(:descricao)", Tarefa.class);
			param = "%" + param + "%";
			query.setParameter("resumo", param);
			query.setParameter("descricao", param);
			tarefas = query.getResultList();
		} catch (Exception e) {
			throw new TarefasException(e);
		} finally {
			closeEntityManager();
		}
		return tarefas;
	}

}
