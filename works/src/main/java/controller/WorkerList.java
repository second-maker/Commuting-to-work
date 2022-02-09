package controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class WorkerList
 */
@WebServlet("/WorkerList")
public class WorkerList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerList() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		// String message = null;
		
		// 今日の日にち取得
		//Calendar calendar = Calendar.getInstance();
		//SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		//String date = d.format(calendar.getTime());
		
		
		DBManager dbm = new DBManager();
		
		// ArrayList<String> now = new ArrayList<>();
		ArrayList<UserDTO> ulist = dbm.getUserList();
		ArrayList<WorkDTO> wlist = dbm.getWorkList();
		
		
		
		request.setAttribute("ulist", ulist);
		request.setAttribute("wlist", wlist);
		// request.setAttribute("now", now);
		
		rd = request.getRequestDispatcher("workerList.jsp");
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
