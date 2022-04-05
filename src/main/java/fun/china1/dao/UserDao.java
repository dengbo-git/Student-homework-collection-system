package fun.china1.dao;

import fun.china1.entity.Users;
import fun.china1.service.FaceService;
import fun.china1.service.GetRe;
import fun.china1.utils.DBUtil;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private DBUtil dbUtil=new DBUtil();
    public void welcomers(){
            String sql= "CREATE TABLE `t_face_user` (\n" +
                    "  `userid` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(50) NOT NULL,\n" +
                    "  `usergender` varchar(50) NOT NULL,\n" +
                    "  `personid` varchar(255) NOT NULL,\n" +
                    "  `homework` int(1) NOT NULL DEFAULT '0',\n" +
                    "  `admin` int(1) NOT NULL DEFAULT '0',\n" +
                    "  PRIMARY KEY (`userid`),\n" +
                    "  UNIQUE KEY `personid` (`personid`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;\n" +
                    "\n";
            PreparedStatement ps=dbUtil.getstmt(sql);
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbUtil.close();
        }
    }
    public int isFinish(String username,String personid,int finish){
        String sql="";
        if(username.equals("all")&&personid.equals("all")){
            sql="update t_face_user set homework = ?";
            PreparedStatement ps=dbUtil.getstmt(sql);
            try {
                ps.setInt(1,finish);
                ps.executeUpdate();
                if(finish==0){
                    File fileuser=new File(GetRe.getPath());
                    File files[]=fileuser.listFiles();
                    for (int i = 0; i <files.length ; i++) {
                        if(files[i].isDirectory()){
                        File filework[]=files[i].listFiles();
                        for (int j = 0; j <filework.length ; j++) {
                            filework[j].delete();
                        }
                    }
                    }
                }return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                dbUtil.close();
            }
        }else {
            sql="update t_face_user set homework = ? WHERE personid = ?";
            PreparedStatement ps=dbUtil.getstmt(sql);
            try {
                ps.setInt(1,finish);
                ps.setString(2,personid);
                ps.executeUpdate();
                if(finish==0){
                    File file=new File(GetRe.getPath()+username);
                    File files[]=file.listFiles();
                    for (int i = 0; i <files.length ; i++) {
                        files[i].delete();
                    }
                }
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {

                DBUtil.close();
            }
        }

        return 0;
    }
    public int add(Users users){
        int result=0;
        String sql="insert into t_face_user (username,usergender,personId,admin) values(?,?,?,?)";
        PreparedStatement ps= DBUtil.getstmt(sql);
        try {
            ps.setString(1,users.getUsername());
            ps.setString(2, users.getGender().toString());
            ps.setString(3,users.getNum());
            ps.setInt(4,users.getAdmin());
            result=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbUtil.close();
        }
        return result;
    }
    public boolean isexit(String personid){
        String sql="select *from t_face_user";
        PreparedStatement ps=dbUtil.getstmt(sql);
        ResultSet rs=null;
        try {
            rs=ps.executeQuery();
            while (rs.next()){
                String personLd=rs.getString("personid");
                if(personLd.equals(personid)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            dbUtil.close();
        }
        return false;
    }

    public List userFind(){
        ResultSet rs;
        List list=new ArrayList();
        String sql="select *from t_face_user order by homework,personid";
        PreparedStatement ps=dbUtil.getstmt(sql);
        try {
            rs=ps.executeQuery();
            while (rs.next()){
                String  username=rs.getString("username");
                String  usergender=rs.getString("usergender");
                String personid=rs.getString("personid");
                int finish=rs.getInt("homework");
                Integer admin=rs.getInt("admin");
                Users users=new Users(username,Long.parseLong(usergender),personid,finish,admin);
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            dbUtil.close();
        }
        return list;
    }
    public int delete(String userId,String username){
        String sql ="delete from t_face_user where personid=?";
        int result = 0;
        PreparedStatement ps=dbUtil.getstmt(sql);
        try {
            ps.setString(1,userId);
            result=ps.executeUpdate();
            File file=new File(GetRe.getPath()+username);
            file.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            FaceService faceService=new FaceService();
            faceService.deletePersonFace(userId);
            dbUtil.close();
        }
        return result;
    }
    public void addAdmin(String num,String admin){
        String sql="update t_face_user set admin = ? WHERE personid = ?";
        int setadmin=Integer.valueOf(admin);
        PreparedStatement ps=dbUtil.getstmt(sql);
            try {
                ps.setInt(1,setadmin);
                ps.setString(2,num);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    public String getxXh(String userNum){
        ResultSet rs;
        String sql="select *from t_face_user order by personid ";
        PreparedStatement ps=dbUtil.getstmt(sql);
        try {
            rs=ps.executeQuery();
            int i=1;
            while (rs.next()){
                String personid=rs.getString("personid");
                if(personid.equals(userNum)){
                    return String.valueOf(i);
                }
                else {
                    i++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            dbUtil.close();
        }
        return "";
    }

}
