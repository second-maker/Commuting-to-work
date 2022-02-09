package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;
import dto.WorkDTO;

/**
 * Servlet implementation class GoWork
 */
@WebServlet("/GoWork")
public class GoWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoWork() {
        super();
        
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
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		RequestDispatcher rd = null;
		String message = null;
		
		if(userId.equals("") || password.equals("")) {
			
			message = "ユーザーIDとパスワードは必須入力です。";
			
			request.setAttribute("alert", message);
			
			rd = request.getRequestDispatcher("work.jsp");
			rd.forward(request, response);
			
		}else {
			
			DBManager dbm = new DBManager();
			
			UserDTO user = dbm.getWorkUser(userId, password);
			
			if(user != null) {
				
				// 出勤の登録
				dbm.setStartTime(user);
				
				WorkDTO work = dbm.getWorkDTO(user);
				
				// 情報をリクエストスコープに保存
				request.setAttribute("user", user);
				request.setAttribute("work", work);
				
				rd = request.getRequestDispatcher("StartDone.jsp");
				rd.forward(request, response);
				
				
			} else {
				
				message = "ユーザーIDかパスワードが違います。";
				
				request.setAttribute("alert", message);
				
				rd = request.getRequestDispatcher("work.jsp");
				rd.forward(request, response);
				
			}
			
			
		}
		
		
		
	}

}
