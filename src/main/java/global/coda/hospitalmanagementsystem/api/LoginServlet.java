package global.coda.hospitalmanagementsystem.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.coda.hospitalmanagementsystem.db.services.AuthenticationService;
import global.coda.hospitalmanagementsystem.model.User;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<Integer> userValues = new AuthenticationService().authenticateUser(username, password);

		if (userValues != null) {
			int userId = userValues.get(0);
			int roleId = userValues.get(1);
			int accountNumber = userValues.get(2);
			User user = new User();
			user.setAccountNumber(accountNumber);
			user.setRoleId(roleId);
			user.setUserId(userId);
			user.setUsername(username);

			request.setAttribute("username", username);
			request.setAttribute("userId", userId);
			request.setAttribute("roleId", roleId);
			request.setAttribute("accountNumber", accountNumber);
			HttpSession httpSession = request.getSession(true);
			httpSession.setAttribute("username", username);
			httpSession.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("details.jsp");
			dispatcher.forward(request, response);
		} else {
			// response.getWriter().print("Wrong Credentials");
			// RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
			// dispatcher.forward(request, response);
			response.sendRedirect("login.jsp");
		}
	}

}
