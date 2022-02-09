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
import dto.AdminDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String adminId = request.getParameter("adminId");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;
		String message = null;
		
		
		if(adminId.equals("") || password.equals("")) {
			
			message = "ユーザーIDとパスワードは必須入力です。";
			
			request.setAttribute("alert", message);
			
			rd = request.getRequestDispatcher("login.jsp"); 
			rd.forward(request, response);
			
		} else {
			
			AdminDBM adbm = new AdminDBM();
			
			AdminDTO admin = adbm.getLoginUser(adminId, password);
			
			if(admin != null) {
				
				
				HttpSession session = request.getSession();
				
//				if(null == session) {
//					session = request.getSession();
//					
//				}
				
				session.setAttribute("admin", admin);
				
				rd = request.getRequestDispatcher("menu.jsp");
				rd.forward(request, response);
				
				
			} else {
				
				message = "ユーザーIDかパスワードが違います。";
				
				request.setAttribute("alert", message);
				
				rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				
			}
			
			
			
		}
		
		
	}

}
