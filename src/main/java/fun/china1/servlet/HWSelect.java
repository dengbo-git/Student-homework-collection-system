package fun.china1.servlet;

import fun.china1.dao.UserDao;
import fun.china1.entity.Users;
import fun.china1.service.GetRe;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HWSelect extends HttpServlet {

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
            UserDao userDao=new UserDao();
            List<Users> list=userDao.userFind();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            printWriter.print("<center><table border=2>" +
                    "<tr>" +
                    "<td>"+"学生学号"+"</td>"+
                    "<td>"+"学生姓名"+"</td>"+
                    "<td>"+"学生性别"+"</td>"+
                    "<td>"+"完成情况"+"</td>"+
                    "<td>"+"删除作业"+"</td>"+
                    "</tr>" );
            for (Users user:list) {
                String gender=user.getGender()==1?"男":"女";
                String finish=user.getFinish()==0? "未完成":"完成";
                String color=user.getFinish()==0?"red":"black";
                String delete=user.getFinish()==0?"":"<a href=hmd?username="+user.getUsername()+"&personid="+user.getNum()+">删除作业</a>";
                printWriter.print("<tr>" +
                        "<td>"+user.getNum()+"</td>"+
                        "<td>"+user.getUsername()+"</td>"+
                        "<td>"+gender+"</td>"+
                        "<td>"+"<font color="+color+" size=3px>"+finish+"</font>"+"</td>"+
                        "<td>"+delete+"</td>"+
                        "</tr>" );
            }
            printWriter.print("<td colspan=\"4\" align=\"center\"><a href=daochu>导出作业</a></td>" +
                    "<td><a href=hmd?username=all&personid=all>删除所有作业</a><td>");
            printWriter.print("</table></center>");
        }
        else {
            response.getWriter().print("<font font-size=100 color=red >"+users.getUsername()+"已检测到你非法登录，下不为例"+"</font>");
        }
    }
}
