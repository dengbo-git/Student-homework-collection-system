package fun.china1.servlet;

import fun.china1.utils.BaseGet;
import fun.img.path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetimgServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh mm ss SSS");
        BaseGet.decodeBase64ToImage(request.getParameter("image").replace("data:image/png;base64,",""),date.format(new Date())+".png");
        //BaseGet.decodeBase64ToImage(request.getParameter("image"), path.index+ ".png");
        path.index++;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
