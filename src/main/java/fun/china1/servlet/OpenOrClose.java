package fun.china1.servlet;

import fun.china1.entity.Users;
import fun.china1.service.GetRe;
import fun.china1.service.Logging;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class OpenOrClose extends HttpServlet {

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
            if(GetRe.isAdminSuper(session)){
                String isOpen=request.getParameter("isopen");
                BufferedWriter writer = null;
                if("1".equals(isOpen)){
                    try {
                        FileOutputStream writerStream = new FileOutputStream(new File(GetRe.getPath() + "agreers.txt"));
                         writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
                        writer.write(isOpen);
                        writer.flush();
                        Logging.makeFormat(session, true);
                        response.getWriter().print("<center><font font-size=100 color=\"#20b2aa\" >注册通道已打开</font></center>");
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        if(writer!=null){
                            writer.close();
                        }
                    }
                }
                else {
                    try {
                        FileOutputStream writerStream = new FileOutputStream(new File(GetRe.getPath() + "agreers.txt"));
                        writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
                        writer.write(isOpen);
                        writer.flush();
                        Logging.makeFormat(session, true);
                        response.getWriter().print("<center><font font-size=100 color=\"#20b2aa\" >注册通道已关闭</font></center>");
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        if(writer!=null){
                            writer.close();
                        }
                    }
                }
            }
            else {
                response.getWriter().print("<font font-size=100 color=red >"+users.getUsername()+"普通管理员权限不足，请联系超级管理员"+"</font>");
            }
        }
        else {
            response.getWriter().print("<font font-size=100 color=red >"+users.getUsername()+"已检测到你非法登录，下不为例"+"</font>");
        }
    }
}
