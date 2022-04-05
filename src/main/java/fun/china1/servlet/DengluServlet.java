package fun.china1.servlet;
import com.alibaba.fastjson.JSONObject;
import fun.china1.dao.UserDao;
import fun.china1.entity.Users;
import fun.china1.service.GetMap;
import fun.china1.service.GetRe;
import fun.china1.service.Logging;
import fun.china1.service.GetMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


public class DengluServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String img=request.getParameter("image");
        JSONObject jo=new JSONObject();
        Users users=GetMap.sibie(img);
        UserDao userDao=new UserDao();
        HttpSession session = request.getSession();
        session.setAttribute("user", users);
        session.setMaxInactiveInterval(30*60);
        if(null!=users&&userDao.isexit(users.getNum())){
            if(GetRe.isAdmin(session,false)){
                Logging.dengLu(session,true);
                File file = new File(GetRe.getPath() + users.getUsername());
                if (!file.exists()) {//如果文件夹不存在
                    file.mkdir();//创建文件夹
                }
                jo.put("flag","登录成功,欢迎管理员"+users.getUsername()+":admin.html");
            }
            else {
                Logging.dengLu(session,true);
                jo.put("flag", "登录成功,欢迎"+users.getUsername()+":up.html");
                File file = new File(GetRe.getPath() + users.getUsername());
                if (!file.exists()) {//如果文件夹不存在
                    file.mkdir();//创建文件夹
                }
            }
        }
        else{
            jo.put("flag","验证失败:fail.html");
        }
        response.getWriter().write(jo.toJSONString());
    }
}
