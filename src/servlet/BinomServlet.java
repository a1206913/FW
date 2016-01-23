package servlet;

import logic.Binomial;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BinomServlet
 */
@WebServlet("/BinomServlet")
public class BinomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BinomServlet() {
        super();
    }
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String rechner = request.getParameter("rechner");
		if (rechner != null && rechner.equalsIgnoreCase("Berechne")) {
			System.out.println("action: " + rechner);
			try {
				int time = 0;
				if (request.getParameter("T").isEmpty() || request.getParameter("T").contains(" ")) {
					time = 3;
				}
				else 
					time = Integer.parseInt(request.getParameter("T"));
					System.out.println(time);
					
				double basisWert = 0;
				if (request.getParameter("basisWert").isEmpty() || request.getParameter("basisWert").contains(" ")) {
					basisWert = 100;
				}
				else
					basisWert = Double.parseDouble(request.getParameter("basisWert"));
				
				double strike = 0;
				if (request.getParameter("strike").isEmpty() || request.getParameter("strike").contains(" ")) {
					strike = 102;
				}
				else
					strike = Double.parseDouble(request.getParameter("strike"));
				
				double uFactor = 0;
				if (request.getParameter("uFactor").isEmpty() || request.getParameter("uFactor").contains(" ")) {
					uFactor = 1.10;
				}
				else
					strike = Double.parseDouble(request.getParameter("uFactor"));
					
				double sFactor = 0;
				if (request.getParameter("sFactor").isEmpty() || request.getParameter("sFactor").contains(" ")) {
					sFactor = 0.95;
				}
				else
					strike = Double.parseDouble(request.getParameter("sFactor"));
				
				double bZins = 0;
				if (request.getParameter("bZins").isEmpty() || request.getParameter("bZins").contains(" ")) {
					bZins = 0.95;
				}
				else
					bZins = Double.parseDouble(request.getParameter("bZins"));
				
				Binomial b = new Binomial(time, basisWert, strike, uFactor, sFactor, bZins);

				if (b.validityCheck().equals("true")) {
					
//					start the calculation
					request.setAttribute("aktienPreis", b.aktienPreis());
					request.setAttribute("callOption", b.callOption());
					
//					response.sendRedirect("/Binom/result.jsp");
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/result.jsp");
					rd.include(request, response);
				}
				
				else
					out.println("<h3 style='color:red';>" + b.validityCheck() + "</h3>");
				
			}
			catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
		}	
		else 
		response.sendRedirect("/Binom/index.jsp");
	}
		
}
