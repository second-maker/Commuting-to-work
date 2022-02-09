package adController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		String userId = request.getParameter("userId");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		
		
		RequestDispatcher rd = null;
		String message = null;
		
		
		if(userId.equals("") || password1.equals("") || password2.equals("") || name.equals("")) {
			
			message = "全ての項目を入力してください。";
			
			request.setAttribute("alert", message);
			
			rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			
		} else if(!(password1.equals(password2))) {
			
			message = "パスワードが一致しません。";
			
			request.setAttribute("alert", message);
			
			rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			
		} else {
			
			
			HttpSession session = request.getSession();
			
			session.setAttribute("userId", userId);
			session.setAttribute("password", password1);
			session.setAttribute("name", name);
			
			rd = request.getRequestDispatcher("checkRegister.jsp");
			rd.forward(request, response);
			
			
		}
		
		
		
		
	}

}
