package adController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDBM;
import dto.WorkDTO;

/**
 * Servlet implementation class CalcUserServlet
 */
@WebServlet("/CalcUserServlet")
public class CalcUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcUserServlet() {
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
		
		String userName = request.getParameter("userName");
		
		RequestDispatcher rd = null;
		String message = null;
		
		
		if(userName.equals("")) {
			
			message = "未入力です。";
			
			request.setAttribute("alert", message);
			
			rd = request.getRequestDispatcher("calcSelect.jsp");
			rd.forward(request, response);
			
		} else {
			
			AdminDBM adbm = new AdminDBM();
			
			ArrayList<WorkDTO> wlist = adbm.getWorksList(userName);
			
			if(wlist != null) {
				
				
				HttpSession session = request.getSession();
				
				session.setAttribute("userName", userName);
				
				session.setAttribute("wlist", wlist);
				
				
				rd = request.getRequestDispatcher("calcPeriod.jsp");
				rd.forward(request, response);
				
				
			} else {
				
				message = "名前が違います。";
				
				request.setAttribute("alert", message);
				
				rd = request.getRequestDispatcher("calcSelect.jsp");
				rd.forward(request, response);
				
			}
			
			
		}
		
	}

}
