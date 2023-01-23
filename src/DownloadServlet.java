import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	
		public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
		{
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();
			String fileLocation = "/demo.jsp"; //change the path according to you
			File file = new File("E:\\");
			FileInputStream fis = new FileInputStream(file);
			ServletOutputStream sos = res.getOutputStream();
			res.setContentType("application/octet-stream");
			res.setHeader("Content-Disposition", "attachment;filename=" + fileLocation);
			byte[] buffer = new byte[4096];
			 
			while((fis.read(buffer, 0, 4096)) != -1){
			sos.write(buffer, 0, 4096);
			}
			fis.close();
			}
		
}
