package main_cliente;

public class MainCliente {

	public static void main(String[] args) {
		MainCliente cm = new MainCliente();
		cm.chamaMetodo();
	}
	
	public void chamaMetodo (){
		MenuPrincipalCliente mp = new MenuPrincipalCliente();
		mp.menuPrincipalCliente();
		
	}
}
