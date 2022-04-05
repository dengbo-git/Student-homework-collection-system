package fun.china1.servlet;

import fun.china1.dao.UserDao;
import fun.china1.entity.Users;
import fun.china1.service.GetRe;
import fun.china1.service.Logging;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class HMDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("user");
        if(users==null){
            response.getWriter().print("<font font-size=100 color=red >请先完成人脸验证</font>");
        }
        else if(GetRe.isAdmin(session,true)){
            String userName = request.getParameter("username");
            String personId = request.getParameter("personid");
            UserDao userDao = new UserDao();
            response.setContentType("text/html;charset=utf-8");
            Integer result = userDao.isFinish(userName, personId, 0);
            PrintWriter printWriter = response.getWriter();
            if (result != 0) {
                printWriter.print("<font font-size=100 color=red >删除成功</font>");
                Logging.deleteHM(session,userName,personId,true);
            } else {
                printWriter.print("<font font-size=100 color=red >删除失败</font>");
                Logging.deleteHM(session,userName,personId,false);
            }
        }
        else {
            response.getWriter().print("<font font-size=100 color=red >"+users.getUsername()+"已检测到你非法登录，下不为例"+"</font>");
        }
    }
}
