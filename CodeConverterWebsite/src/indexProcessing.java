

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class indexProcessing
 */
@WebServlet("/indexProcessing")
public class indexProcessing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String leftDropBoxIndex = request.getParameter("leftDropBox");
		String rightDropBoxIndex = request.getParameter("rightDropBox");
		String input = request.getParameter("input");
		String output = request.getParameter("output");
		
		System.out.println(leftDropBoxIndex + " " + rightDropBoxIndex + " " + input + " " + output);
		
		request.getSession().setAttribute("output", output);
	}

}
