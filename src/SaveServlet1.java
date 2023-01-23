import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveServlet")
public class SaveServlet1 extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String sid = req.getParameter("id");
		int id = Integer.parseInt(sid);
		String name = req.getParameter("name");
		String pass = req.getParameter("password");
		String email = req.getParameter("email");
		String country = req.getParameter("country");

		Emp emp = new Emp();
		emp.setId(id);
		emp.setName(name);
		emp.setPassword(pass);
		emp.setEmail(email);
		emp.setCountry(country);
		int status = EmpDao.save(emp);
		if (status > 0) {
			out.print("<p>Record saved successfully!</p>");
			req.getRequestDispatcher("index.html").include(req, res);
		} else {
			out.println("Sorry! unable to save record");
		}
		out.close();
	}
}