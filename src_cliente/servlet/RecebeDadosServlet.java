package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet implementation class RecebeDadosServlet
 */
@WebServlet(name="Recebe Dados", urlPatterns="/RecebeDadosServlet")
public class RecebeDadosServlet extends HttpServlet {	
	private static final long serialVersionUID = -7891159096554766799L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public RecebeDadosServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
PrintWriter writer = response.getWriter();
writer.println("<h1>Nome inserido com sucesso!</h1>");
String nome = request.getParameter("nome");
writer.print("<h2>Olá, " + nome+"</h2>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
