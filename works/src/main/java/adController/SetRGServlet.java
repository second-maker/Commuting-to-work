package adController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDBM;

/**
 * Servlet implementation class SetRGServlet
 */
@WebServlet("/SetRGServlet")
public class SetRGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetRGServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		RequestDispatcher rd = null;
		
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("userId");
		String password = (String)session.getAttribute("password");
		String name = (String)session.getAttribute("name");
		
		
		AdminDBM adbm = new AdminDBM();
		
		adbm.setRegist(userId, password, name);
		
		adbm.setDate(userId);
		
		session.removeAttribute("userId");
		session.removeAttribute("password");
		session.removeAttribute("name");
		
		
		rd = request.getRequestDispatcher("setRegister.jsp");
		rd.forward(request, response);
		
		
	}

}
