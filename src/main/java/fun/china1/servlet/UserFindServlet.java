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

public class UserFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            response.getWriter().print("<font font-size=100 color=red >请先完成人脸验证</font>");
        }
        else if(GetRe.isAdmin(session,true)){
            UserDao userDao=new UserDao();
            List<Users> list=userDao.userFind();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            String show="";
            if(GetRe.isAdminSuper(session)){
                show="<td>"+"删除用户"+"</td>"+
                     "<td>"+"管理员"+"</td>"+
                     "<td>"+"超级管理"+"</td>"+
                     "<td>"+"取消管理员"+"</td>"
                ;
            }
            printWriter.print("<center><table border=2>" +
                    "<tr>" +
                    "<td>"+"学生学号"+"</td>"+
                    "<td>"+"学生姓名"+"</td>"+
                    "<td>"+"学生性别"+"</td>"+
                    "<td>"+"学生类别"+"</td>"+
                    show+
                    "</tr>" );
            for (Users users:list) {
                String sadmin="";
                if(GetRe.isAdminSuper(session)){
                    if(users.getAdmin()==0) {
                        sadmin = "<td><a href='/user/delete?userId=" + users.getNum() + "&username=" + users.getUsername() + "'>删除用户</a></td>" +
                                "<td><a href='admin?userId=" + users.getNum() + "&username=" + users.getUsername() + "&admin=1" + "'>设置管理员</a></td>" +
                                "<td><a href='admin?userId=" + users.getNum() + "&username=" + users.getUsername() + "&admin=2" + "'>设置超级管理员</a></td>" +
                                "<td></td>";
                    }
                    else if(users.getAdmin()==1) {
                        sadmin = "<td><a href='/user/delete?userId=" + users.getNum() + "&username=" + users.getUsername() + "'>删除用户</a></td>" +
                                "<td></td>" +
                                "<td><a href='admin?userId=" + users.getNum() + "&username=" + users.getUsername() + "&admin=2" + "'>设置超级管理员</a></td>" +
                                "<td><a href='admin?userId=" + users.getNum() + "&username=" + users.getUsername() + "&admin=0" + "'>取消管理员</a></td>";
                    }
                    else {
                        if(user.getNum().equals(users.getNum())&&user.getUsername().equals(users.getUsername())){
                            sadmin = "<td><a href='/user/delete?userId=" + users.getNum() + "&username=" + users.getUsername() + "'>删除自己</a></td>" +
                                    "<td></td>" +
                                    "<td></td>" +
                                    "<td><a href='admin?userId=" + users.getNum() + "&username=" + users.getUsername() + "&admin=0" + "'>取消我的管理员</a></td>";
                        }else {
                            sadmin = "<td></td>" +
                                    "<td></td>" +
                                    "<td></td>" +
                                    "<td></td>";
                        }
                    }
                }
                String category=users.getAdmin()==0?"普通用户":(users.getAdmin()==1?"管理员":"超级管理员");
                String gender=users.getGender()==1?"男":"女";
                printWriter.print("<tr>" +
                        "<td>"+users.getNum()+"</td>"+
                        "<td>"+users.getUsername()+"</td>"+
                        "<td>"+gender+"</td>"+
                        "<td>"+category+"</td>"+
                        sadmin+
                        "</tr>" );
            }
            printWriter.print("</table></center>");
        }
        else {
            response.getWriter().print("<font font-size=100 color=red >"+user.getUsername()+"已检测到你非法登录，下不为例"+"</font>");
        }
    }
}
