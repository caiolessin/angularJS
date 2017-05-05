package br.com.senai.tarefas.rest;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.senai.tarefas.exception.TarefasException;

public class UtilRest {

	protected ObjectMapper getObjectMapper(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return new ObjectMapper()
				.setDateFormat(dateFormat)
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
				.configure(SerializationFeature.INDENT_OUTPUT, true)
				.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	}
	
	protected Response buildSaveResponse(Object obj){
		return  buildResponse(new RestResponse(
					Response.Status.CREATED.getStatusCode(),
					"success",
					"Registro inserido com sucesso",
					obj
				));
	}
	
	protected Response buildUpdateResponse(Object obj){
		return  buildResponse(new RestResponse(
					Response.Status.OK.getStatusCode(),
					"success",
					"Registro atualizado com sucesso",
					obj
				));
	}
	
	protected Response buildRemoveResponse(Object obj){
		return  buildResponse(new RestResponse(
					Response.Status.OK.getStatusCode(),
					"success",
					"Registro excluido com sucesso",
					obj
				));
	}
	
	protected Response buildGetResponse(Object obj){
		return  buildResponse(new RestResponse(
					Response.Status.OK.getStatusCode(),
					"success",
					null,
					obj
				));
	}
	
	protected Response buildResponse(Object obj){
		String json = null;
		try {
			json = getObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return buildErrorResponse(e);
		}
		return Response.ok(json).build();
	}

	protected Response buildErrorResponse(TarefasException e) {
		return buildErrorResponse(e, Response.Status.BAD_REQUEST);
	}

	protected Response buildErrorResponse(Exception e) {
		return buildErrorResponse(e, Response.Status.INTERNAL_SERVER_ERROR);
	}

	protected Response buildErrorResponse(Exception e, Response.Status status) {
		String json = null;
		try {
			json = getObjectMapper().writeValueAsString(new RestResponse(e));
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
			return null;
		}
		return Response.status(status)
				.entity(json)
				.build();
	}
}
