package br.com.senai.tarefas.rest;

public class RestResponse {

	private int codigo;
	private String status;
	private String mensagem;
	private Object data;
	
	public RestResponse(Exception e){
		codigo = 500;
		status = "error";
		mensagem = "Não foi possível processar sua requisição: " + e.getMessage();
	}

	public RestResponse(int codigo, String status, String mensagem, Object data) {
		super();
		this.codigo = codigo;
		this.status = status;
		this.mensagem = mensagem;
		this.data = data;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestResponse other = (RestResponse) obj;
		if (codigo != other.codigo)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RestResponse [codigo=" + codigo + ", status=" + status + ", mensagem=" + mensagem + ", data=" + data
				+ "]";
	}
}
