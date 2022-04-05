package fun.china1.servlet;
import fun.china1.dao.UserDao;
import fun.china1.service.GetRe;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class WelcomeRs extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      String ip=request.getParameter("ip");
      String databaseName=request.getParameter("sqlname");
      String databasenum=request.getParameter("accountnum");
      String dbcode=request.getParameter("sqlcode");
      String dkh=request.getParameter("dkh");
        FileWriter fileWriter=new FileWriter(GetRe.getPath()+"mysql.txt");
        fileWriter.write(ip+System.getProperty("line.separator")+databaseName+System.getProperty("line.separator")+databasenum+System.getProperty("line.separator")+dbcode+System.getProperty("line.separator")+dkh+System.getProperty("line.separator"));
        fileWriter.flush();
        fileWriter.close();
        UserDao userDao=new UserDao();
        userDao.welcomers();
        response.sendRedirect("welcome3.html");
    }
}
