package fun.china1.servlet;
import fun.china1.entity.Users;
import fun.china1.service.GetRe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class ShowRz extends HttpServlet {
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
            String result = "";
        try {
            FileReader fileReader = new FileReader(GetRe.getPath()+"日志记录.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(bufferedReader.ready()){
                result += bufferedReader.readLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.getWriter().print("<center><table border=2 align=\"center\">" +
                "<tr align=\"center\">" +
                "<td>"+"命令对象"+"</td>"+
                "<td>"+"操作时间"+"</td>"+
                "<td>"+"操作内容"+"</td>"+
                "<td>"+"操作结果"+"</td>"+
                "</tr>"+ result+"</table></center>");

        }
        else {
            response.getWriter().print("<font font-size=100 color=red >"+users.getUsername()+"已检测到你非法登录，下不为例"+"</font>");
        }
    }
}
