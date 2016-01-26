package servlet;

import logic.Binomial;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String rechner = request.getParameter("rechner");
		if (rechner != null && rechner.equalsIgnoreCase("Berechne")) {
			System.out.println("action: " + rechner);
			int time = 0;
			try {
				if (request.getParameter("T").isEmpty() || request.getParameter("T").contains(" ")) {
					time = 3;
				}
				else
					time = Integer.parseInt(request.getParameter("T"));
			}
			catch (NumberFormatException ex) {
				out.println("<h3 style='color:red';>" + "Im 'Time'-Feld is ein Ganzzahl erwartet" + "</h3>");
				ex.printStackTrace();
			}
			
		try {			
			double basisWert = 0;
				if (request.getParameter("basisWert").isEmpty() || request.getParameter("basisWert").contains(" ")) {
					basisWert = 100;
				}
				else
					basisWert = Double.parseDouble(request.getParameter("basisWert"));
				
				double strike = 0;
				if (request.getParameter("strike").isEmpty() || request.getParameter("strike").contains(" ")) {
					System.out.println("request.getParameter(strike): " + request.getParameter("strike"));
					strike = 102;
				}
				else {
					System.out.println("in the else: request.getParameter(strike): " + request.getParameter("strike"));
					strike = Double.parseDouble(request.getParameter("strike"));
				}	
				
				double uFactor = 0;
				if (request.getParameter("uFactor").isEmpty() || request.getParameter("uFactor").contains(" ")) {
					uFactor = 1.10;
				}
				else
					uFactor = Double.parseDouble(request.getParameter("uFactor"));
					
				double dFactor = 0;
				if (request.getParameter("dFactor").isEmpty() || request.getParameter("dFactor").contains(" ")) {
					dFactor = 0.95;
				}
				else
					dFactor = Double.parseDouble(request.getParameter("dFactor"));
				
				double bZins = 0;
				if (request.getParameter("bZins").isEmpty() || request.getParameter("bZins").contains(" ")) {
					bZins = 0.95;
				}
				else {
					bZins = Double.parseDouble(request.getParameter("bZins"));
				}
				 
				Binomial b = new Binomial(time, basisWert, strike, uFactor, dFactor, bZins);

				if (b.validityCheck().equals("true")) {
					
//					start the calculation
					ArrayList<double[]> aktienList = b.aktienPreis();
					ArrayList<double[]> call = b.callOption(aktienList);
					request.setAttribute("aktienPreis", aktienList);
					request.setAttribute("callOption", call);
					
//					System.out.println("from the servlet: " + b.toString());
					
//					response.sendRedirect("/Binom/result.jsp");
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/result.jsp");
					rd.forward(request, response);
				}
				
				else
					out.println("<h3 style='color:red';>" + b.validityCheck() + "</h3>");
				
			}
			catch (NumberFormatException ex) {
				out.println("<h3 style='color:red';>" + "Die Werte müssen Zahlen sein und als Dezimaltrennzeichen wird Punkt akzeptiert" + "</h3>");
				ex.printStackTrace();
			}
		}	
		else 
		response.sendRedirect("/Binom/index.jsp");
	}
		
}
