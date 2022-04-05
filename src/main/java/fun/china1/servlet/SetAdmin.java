package fun.china1.servlet;

import fun.china1.dao.UserDao;
import fun.china1.entity.Users;
import fun.china1.service.Logging;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num=request.getParameter("userId");
        String admin=request.getParameter("admin");
        String adminname=request.getParameter("username");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        UserDao userDao=new UserDao();
        HttpSession session= request.getSession();
        if("1".equals(admin)){
            userDao.addAdmin(num,"1");
            response.getWriter().print("<font size=40px,color=red>"+"设置管理员成功"+"</font>");
            Logging.addAdmin(session,adminname,num,true,1);
        }
        else if ("2".equals(admin)){
            userDao.addAdmin(num,"2");
            response.getWriter().print("<font size=40px,color=red>"+"设置超级管理员成功"+"</font>");
            Logging.addAdmin(session,adminname,num,true,2);
        } else {
            userDao.addAdmin(num,"0");
            response.getWriter().print("<font size=40px,color=red>"+"取消管理员成功"+"</font>");
            Logging.addAdmin(session,adminname,num,true,0);
        }
    }
}
