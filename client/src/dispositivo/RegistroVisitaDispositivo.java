package dispositivo;

public class RegistroVisitaDispositivo {

	private String local;
	
	private String data;
	
	private String hora;	

	public RegistroVisitaDispositivo() {
		super();
	}

	public RegistroVisitaDispositivo(String local, String data,	String hora ) {
		super();	
		this.local = local;
		this.data = data;
		this.hora = hora;		
	}	

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		RegistroVisitaDispositivo registroEmArquivo = this;
		return registroEmArquivo.getLocal() + "|" + registroEmArquivo.getData() + "|"	+ registroEmArquivo.getHora();
	}
}
