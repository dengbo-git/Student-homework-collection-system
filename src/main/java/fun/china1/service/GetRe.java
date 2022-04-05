package fun.china1.service;

import fun.china1.entity.Users;
import fun.china1.users.rode;

import javax.servlet.http.HttpSession;
import java.io.*;

public class GetRe {

    public static String getPath() {
        Class cl= rode.class;
        return cl.getResource("").getPath();
    }
    public static boolean isAdmin(HttpSession session,boolean eydenglu) {
        Users user = (Users) session.getAttribute("user");
        Integer isadmin=user.getAdmin();
        if (isadmin>0){
            return true;
        }
        else {
            Logging.Eydenglu(session,false);
            return false;
        }
    }

    public static boolean isAdminSuper(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        Integer issuperadmin = user.getAdmin();
        if (issuperadmin > 1) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isOpenRs() {
        String isOpen="0";
        try {
            BufferedReader filetxt = new BufferedReader( new InputStreamReader( new FileInputStream(new File(GetRe.getPath()+"agreers.txt")), "UTF-8" ));
             isOpen=filetxt.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if("1".equals(isOpen)){
            return true;
        }
        else {
            return false;
        }
    }
}
