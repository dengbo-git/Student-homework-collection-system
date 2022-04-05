package fun.china1.servlet;
import com.alibaba.fastjson.JSONObject;
import fun.china1.dao.UserDao;
import fun.china1.entity.Users;
import fun.china1.service.FaceService;
import fun.china1.service.GetMap;
import fun.china1.service.GetRe;
import fun.china1.service.Logging;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();
        String result = "t";
        UserDao userDao = new UserDao();
        if(!"true".equals(request.getParameter("first"))) {
            if (GetRe.isOpenRs()) {
                if (!userDao.isexit(request.getParameter("personId"))) {
                    if (GetMap.sibie(request.getParameter("faceImage")) == null) {
                        FaceService faceService = new FaceService();
                        result = faceService.createPersonFace(request.getParameter("faceImage"), Long.valueOf(request.getParameter("gender")), request.getParameter("username"), request.getParameter("personId"));
                        if (result.equals("")) {
                            jsonObject.put("flag", 1);
                            Logging.regist(request.getParameter("username"), request.getParameter("personId"), false);
                        } else {
                            jsonObject.put("flag", 3);
                            Users users = new Users(request.getParameter("username"), Long.parseLong(request.getParameter("gender")), request.getParameter("personId"), 0, 0);
                            userDao.add(users);
                            Logging.regist(request.getParameter("username"), request.getParameter("personId"), true);
                            File file = new File(GetRe.getPath() + request.getParameter("username"));
                            if (!file.exists()) {//如果文件夹不存在
                                file.mkdir();//创建文件夹
                            }
                        }
                    } else {
                        jsonObject.put("flag", 2);
                        Logging.regist(request.getParameter("username"), request.getParameter("personId"), false);
                    }
                } else {
                    jsonObject.put("flag", 2);
                    Logging.regist(request.getParameter("username"), request.getParameter("personId"), false);
                }
            } else {
                jsonObject.put("flag", 4);
            }
        }else {
            if (GetMap.sibie(request.getParameter("faceImage")) == null) {
                FaceService faceService = new FaceService();
                result = faceService.createPersonFace(request.getParameter("faceImage"), Long.valueOf(request.getParameter("gender")), request.getParameter("username"), request.getParameter("personId"));
                if (result.equals("")) {
                    jsonObject.put("flag", 1);
                } else {
                    jsonObject.put("flag", 3);
                    Users users = new Users(request.getParameter("username"), Long.parseLong(request.getParameter("gender")), request.getParameter("personId"), 0, 2);
                    userDao.add(users);
                    File file = new File(GetRe.getPath() + request.getParameter("username"));
                    if (!file.exists()) {//如果文件夹不存在
                        file.mkdir();//创建文件夹
                    }
                    String path = getServletContext().getRealPath("/");
                    File filewelcome=new File(path+"welcome.html");
                    filewelcome.delete();
                     path = getServletContext().getRealPath("/");
                     filewelcome=new File(path+"welcome2.html");
                    filewelcome.delete();
                    path = getServletContext().getRealPath("/");
                    filewelcome=new File(path+"welcome3.html");
                    filewelcome.delete();
                }
            } else {
                jsonObject.put("flag", 2);
                Logging.regist(request.getParameter("username"), request.getParameter("personId"), false);
            }
        }
        response.getWriter().write(jsonObject.toString());
    }
}
