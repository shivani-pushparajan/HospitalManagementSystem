package global.coda.hospitalmanagementsystem.api;

import static global.coda.hospitalmanagementsystem.db.constants.DatabaseQueryConstants.*;
import static global.coda.hospitalmanagementsystem.db.constants.DatabaseConnectionConstants.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final ResourceBundle QUERY_BUNDLE = ResourceBundle.getBundle(DB_QUERIES);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect("login.jsp");
		} else {
			String userId = request.getParameter("userid");
			String username = request.getParameter("username");
			String roleId = request.getParameter("roleid");
			try {
				Class.forName(DRIVER);
				Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_BUNDLE.getString(HDB001UQ));
				preparedStatement.setInt(1, Integer.parseInt(userId));
				preparedStatement.setString(2, username);

				preparedStatement.setInt(3, Integer.parseInt(roleId));
				preparedStatement.setInt(4, Integer.parseInt(userId));
				int result = preparedStatement.executeUpdate();
				if (result == 1) {
					request.getRequestDispatcher("update.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("details.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
