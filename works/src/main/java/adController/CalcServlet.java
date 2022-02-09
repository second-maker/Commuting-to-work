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
 * Servlet implementation class CalcServlet
 */
@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcServlet() {
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
		
		HttpSession session = request.getSession();
		
		String userName = (String)session.getAttribute("userName");
		
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		RequestDispatcher rd = null;
		String message = null;
		
		
		if(start.equals("") || end.equals("")) {
			
			message = "全ての項目を入力してください。";
			
			request.setAttribute("alert", message);
			
			rd = request.getRequestDispatcher("calcPeriod.jsp");
			rd.forward(request, response);
			
			
		} else {
			
			
			AdminDBM adbm = new AdminDBM();
			
			ArrayList<WorkDTO> calcList = adbm.getCalcList(userName, start, end);
			
			if(calcList != null) {
				
				int sum = 0;
				
				
				for(int i = 0; i < calcList.size(); i ++) {
					
					String strStartTime = calcList.get(i).getStartTime();
					String strEndTime = calcList.get(i).getEndTime();
					
					sum += adbm.CalcSalary(strStartTime,strEndTime);
					
					
				}
				
				String userId = adbm.getUserId(userName);
				
				adbm.setSalary(userId, sum);
				
				
				session.removeAttribute("userName");
				
				rd = request.getRequestDispatcher("calcDone.jsp");
				rd.forward(request, response);
				
				
			} else {
				
				message = "範囲内での出勤はありません。";
				
				request.setAttribute("alert", message);
				
				rd = request.getRequestDispatcher("calcPeriod.jsp");
				rd.forward(request, response);
				
				
			}
			
			
			
			
			
		}
		
		
		
		
	}

}
