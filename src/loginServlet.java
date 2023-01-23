import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		String name = req.getParameter("username");
		String pass = req.getParameter("password");
		LoginBean bean=new LoginBean();
		bean.setUsername(name);
		bean.setPassword(pass);
		if (EmpDao.loginValidate(bean)) {
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.forward(req, res);

		} else {
			out.print("Sorry username or password incorrect");
			RequestDispatcher rd = req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
		out.close();
	}
}
