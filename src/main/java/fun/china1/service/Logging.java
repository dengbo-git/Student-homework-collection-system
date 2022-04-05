package fun.china1.service;

import fun.china1.entity.Users;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logging {
    private static final SimpleDateFormat sdf;
    static {
         sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    }

    private Logging(){}
    public static void regist(String username,String personId,boolean issuccess) {
        Date nowTime=new Date();
        FileWriter fws = null;
        try {
            fws = new FileWriter(GetRe.getPath() + "日志记录.txt", true);
            fws.write("<tr>" + "<td>" + username + ":" + personId + "</td>" + "<td>" + sdf.format(nowTime) + "</td>" + "<td><font style=\"color: gold\">注册</font></td>" + "<td>" + (issuccess ? "成功" : "失败") + "</td>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fws.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fws.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void dengLu(HttpSession session,boolean issuccess){
        Users users = (Users) session.getAttribute("user");
        Date nowTime=new Date();
        FileWriter fws = null;
            try {
                fws = new FileWriter(GetRe.getPath() + "日志记录.txt", true);
                fws.write("<tr>"+"<td>"+users.getUsername()+":"+users.getNum()+"</td>"+"<td>"+sdf.format(nowTime)+"</td>"+"<td><font style=\"color: green\">登录</font></td>"+"<td>"+(issuccess?"成功":"失败")+"</td>");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fws.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fws.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    public static void upLoad(HttpSession session,boolean issuccess){
            Users users = (Users) session.getAttribute("user");
            FileWriter fws = null;
         Date nowTime=new Date();
            try {
                fws = new FileWriter(GetRe.getPath() + "日志记录.txt", true);
                fws.write("<tr>"+"<td>"+users.getUsername()+":"+users.getNum()+"</td>"+"<td>"+sdf.format(nowTime)+"</td>"+"<td><font style=\"color: darkturquoise\">上传作业</font></td>"+"<td>"+(issuccess?"成功":"失败")+"</td>");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fws.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fws.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
    public static void delete(HttpSession session,String userName,String personid,boolean issuccess){
        Users users = (Users) session.getAttribute("user");
        FileWriter fws = null;
        Date nowTime=new Date();
        try {
            fws = new FileWriter(GetRe.getPath() + "日志记录.txt", true);
            fws.write("<tr>"+"<td>"+"<font style=\"color: lightslategrey\">"+users.getUsername()+":"+users.getNum()+"</font></td>"+"<td>"+sdf.format(nowTime)+"</td>"+"<td><font style=\"color: lightslategrey\">删除用户"+userName+personid+"</font></td>"+"<td>"+(issuccess?"成功":"失败")+"</td>");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fws.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fws.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void deleteHM(HttpSession session,String userName,String personid,boolean issuccess){
        Users users = (Users) session.getAttribute("user");
        FileWriter fws = null;
        if("all".equals(personid)&&"all".equals(userName)){
            personid="部";
            userName="全";
        }
        Date nowTime=new Date();
        try {
            fws = new FileWriter(GetRe.getPath() + "日志记录.txt", true);
            fws.write("<tr>"+"<td>"+"<font style=\"color: red\">"+users.getUsername()+":"+users.getNum()+"</font></td>"+"<td>"+sdf.format(nowTime)+"</td>"+"<td><font style=\"color: red\">删除作业"+userName+personid+"</font></td>"+"<td>"+(issuccess?"成功":"失败")+"</td>");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fws.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fws.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void daochuHM(HttpSession session,boolean issuccess){
        Users users = (Users) session.getAttribute("user");
        FileWriter fws = null;
        Date nowTime=new Date();
        try {
            fws = new FileWriter(GetRe.getPath() + "日志记录.txt", true);
            fws.write("<tr>"+"<td>"+"<font style=\"color: red\">"+users.getUsername()+":"+users.getNum()+"</font></td>"+"<td>"+sdf.format(nowTime)+"</td>"+"<td><font style=\"color: red\">导出作业</font></td>"+"<td>"+(issuccess?"成功":"失败")+"</td>");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fws.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fws.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void makeFormat(HttpSession session,boolean issuccess){
        Users users = (Users) session.getAttribute("user");
        FileWriter fws = null;
        Date nowTime=new Date();
        try {
            fws = new FileWriter(GetRe.getPath() + "日志记录.txt", true);
            fws.write("<tr>"+"<td>"+"<font style=\"color: darkorchid\">"+users.getUsername()+":"+users.getNum()+"</font></td>"+"<td>"+sdf.format(nowTime)+"</td>"+"<td><font style=\"color: darkorchid\">制定格式</font></td>"+"<td>"+(issuccess?"成功":"失败")+"</td>");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fws.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fws.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void Eydenglu(HttpSession session,boolean issuccess){
        Users users = (Users) session.getAttribute("user");
        FileWriter fws = null;
        Date nowTime=new Date();
        try {
            fws = new FileWriter(GetRe.getPath() + "日志记录.txt", true);
            fws.write("<tr>"+"<td>"+"<font style=\"color: darkorchid\">"+users.getUsername()+":"+users.getNum()+"</font></td>"+"<td>"+sdf.format(nowTime)+"</td>"+"<td><font style=\"color: darkorchid\">恶意登录</font></td>"+"<td>"+(issuccess?"成功":"失败")+"</td>");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fws.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fws.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void addAdmin(HttpSession session,String adminName,String adminnum,boolean issuccess,int level){
        Users users = (Users) session.getAttribute("user");
        FileWriter fws = null;
        Date nowTime=new Date();
        String adlevel="";
        if(level==0){
           adlevel= "取消管理员"+adminName+adminnum;
        }else if(level==1){
            adlevel= "设置管理员"+adminName+adminnum;
        }
        else if(level==3){
            adlevel= "设置超级管理员"+adminName+adminnum;
        }
        try {
            fws = new FileWriter(GetRe.getPath() + "日志记录.txt", true);
            fws.write("<tr>"+"<td>"+"<font style=\"color: blue\">"+users.getUsername()+":"+users.getNum()+"</font></td>"+"<td>"+sdf.format(nowTime)+"</td>"+"<td><font style=\"color: darkorchid\">"+adlevel+"</font></td>"+"<td>"+(issuccess?"成功":"失败")+"</td>");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fws.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fws.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
