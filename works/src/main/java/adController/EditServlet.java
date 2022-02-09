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
import dto.WorkDTO;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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
		
		String date = request.getParameter("date");
		String strCategory = request.getParameter("category");
		String time = request.getParameter("time");
		String send = request.getParameter("send");
		
		RequestDispatcher rd = null;
		String message = null;
		
		//String[] categories = {"start_time","end_time","break_time","end_break_time"};
		
//		int intCategory = Integer.parseInt(strCategory);
//		String category = categories[intCategory];
		
		
		if(send.equals("change")) {
			
			if(date.equals("") || strCategory.equals("") || time.equals("")) {
				
				message = "全ての項目を入力してください。";
				
				request.setAttribute("alert", message);
				
				rd = request.getRequestDispatcher("editList.jsp");
				rd.forward(request, response);
				
				
			} else {
				
//				int intCategory = Integer.parseInt(strCategory);
//				String category = categories[intCategory];
				
				
				AdminDBM adbm = new AdminDBM();
				
				WorkDTO work = adbm.getSelectWork(userName, date);
				
				if(work != null) {
					
					
					if(strCategory.equals("0")) {
						
						adbm.changeWork1(time, date, work);
						
					} else if(strCategory.equals("1")) {
						
						adbm.changeWork2(time, date, work);
						
					} else if(strCategory.equals("2")) {
						
						adbm.changeWork3(time, date, work);
						
					} else if(strCategory.equals("3")) {
						
						adbm.changeWork4(time, date, work);
						
					}
					
					
					session.removeAttribute("userName");
					
					
					rd = request.getRequestDispatcher("editChange.jsp");
					rd.forward(request, response);
					
					
				} else {
					
					message = "正しく入力されていません。";
					
					request.setAttribute("alert", message);
					
					rd = request.getRequestDispatcher("editList.jsp");
					rd.forward(request, response);
					
				}
				
			}
			
		} else if(send.equals("delete")) {
			
			
			if(date.equals("") || strCategory.equals("") || !(time.equals(""))) {
				
				message = "必要個所だけ入力してください。";
				
				request.setAttribute("alert", message);
				
				rd = request.getRequestDispatcher("editList.jsp");
				rd.forward(request, response);
				
				
			} else {
				
//				int intCategory = Integer.parseInt(strCategory);
//				String category = categories[intCategory];
				
				AdminDBM adbm = new AdminDBM();
				
				WorkDTO work = adbm.getSelectWork(userName, date);
				
				if(work != null) {
					
					
					if(strCategory.equals("0")) {
						
						adbm.deleteWork1(date, work);
						
					} else if(strCategory.equals("1")) {
						
						adbm.deleteWork2(date, work);
						
					} else if(strCategory.equals("2")) {
						
						adbm.deleteWork3(date, work);
						
					} else if(strCategory.equals("3")) {
						
						adbm.deleteWork4(date, work);
						
					}
					
					session.removeAttribute("userName");
					
					rd = request.getRequestDispatcher("editDelete.jsp");
					rd.forward(request, response);
					
					
					
				} else {
					
					message = "正しく入力されていません。";
					
					request.setAttribute("alert", message);
					
					rd = request.getRequestDispatcher("editList.jsp");
					rd.forward(request, response);
					
					
				}
				
				
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
		}
		
		
		
		
		
		
		
	}

}
