package adController;

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
 * Servlet implementation class WorkerList2
 */
@WebServlet("/WorkerList2")
public class WorkerList2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerList2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = null;
		

		DBManager dbm = new DBManager();
		
		
		ArrayList<UserDTO> ulist = dbm.getUserList();
		ArrayList<WorkDTO> wlist = dbm.getWorkList();
		
		
		
		request.setAttribute("ulist", ulist);
		request.setAttribute("wlist", wlist);
		
		
		rd = request.getRequestDispatcher("workerList2.jsp");
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
