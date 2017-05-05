package br.com.senai.tarefas.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senai.tarefas.model.Tarefa;
import br.com.senai.tarefas.service.TarefaService;

@Path("tarefas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TarefaRest extends UtilRest{

	@POST
	public Response save(String json){
		TarefaService service = new TarefaService();
		try{
			Tarefa tarefa = getObjectMapper().readValue(json, Tarefa.class);
			return buildSaveResponse(service.save(tarefa));
		}catch(Exception e){
			e.printStackTrace();
			return buildErrorResponse(e);
		}
	}
	
	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") int id, String json){
		TarefaService service = new TarefaService();
		try{
			Tarefa tarefa = getObjectMapper().readValue(json, Tarefa.class);
			return buildUpdateResponse(service.update(id,tarefa));
		}catch(Exception e){
			e.printStackTrace();
			return buildErrorResponse(e);
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response remove(@PathParam("id") int id){
		TarefaService service = new TarefaService();
		try{
			return buildRemoveResponse(service.remove(id));
		}catch(Exception e){
			e.printStackTrace();
			return buildErrorResponse(e);
		}
	}
	
	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") int id){
		TarefaService service = new TarefaService();
		try{
			return buildGetResponse(service.findById(id));
		}catch(Exception e){
			e.printStackTrace();
			return buildErrorResponse(e);
		}
	}
	
	@GET
	public Response search(@QueryParam("search") String param){
		TarefaService service = new TarefaService();
		try{
			return buildGetResponse(service.search(param));
		}catch(Exception e){
			e.printStackTrace();
			return buildErrorResponse(e);
		}
	}
}
