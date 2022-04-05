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

public class GsMake extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            response.getWriter().print("<font font-size=100 color=red >请先完成人脸验证</font>");
        }
        else if(GetRe.isAdmin(session,true)){
            String gs=request.getParameter("gs");
            if(gs!=null){
            try {
                FileOutputStream writerStream = new FileOutputStream(new File(GetRe.getPath()+"gs.txt"));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
                writer.write(gs);
                writer.flush();
                writer.close();
                Logging.makeFormat(session,true);
                response.getWriter().print("<center><font font-size=100 color=\"#20b2aa\" >设置成功</font></center>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
            response.getWriter().print("<center><form method=\"get\" action=\"gs\"><table border=2>" +
                    "<tr>" +
                    "<td>"+"案例"+"</td>"+
                    "<td>"+"小学号+姓名+大学号+政治作业.docx————>01邓波2020024592政治作业.docx（保留原文件名请留空再点击修改）"+"</td>"+
                    "</tr>"+
                    "<tr>" +
                    "<td>你的格式</td>"+
                    "<td>"+"<input type=\"text\" name=\"gs\" style=\"width: 100%\">"+"</td>"+
                    "</tr>"+
                    "<tr align=\"center\">" +
                    "<td colspan=\"2\"><input type=\"submit\" value=\"确认修改\" style=\"width: 50%\" ></td>" +
                    "</tr>"+
                    "</center></form></table>"
            );
        }
        else {
            response.getWriter().print("<font font-size=100 color=red >"+users.getUsername()+"已检测到你非法登录，下不为例"+"</font>");
        }
    }
}