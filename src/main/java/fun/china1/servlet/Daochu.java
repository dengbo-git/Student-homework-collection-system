package fun.china1.servlet;

import fun.china1.entity.Users;
import fun.china1.service.GetRe;
import fun.china1.service.Logging;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Daochu extends HttpServlet {
    private static final int  BUFFER_SIZE = 2 * 1024;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("charset=utf-8");
        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("user");
        if(users==null){
            response.getWriter().print("<font font-size=100 color=red >请先完成人脸验证</font>");
        }
        else if(GetRe.isAdmin(session,true)){
            byte bt[] = new byte[1024];
            File fileall=new File(GetRe.getPath()+"all");
            File fs[]=fileall.listFiles();
            for (int i = 0; i <fs.length ; i++) {
                fs[i].delete();
            }
            File file = new File(GetRe.getPath());
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()&&!files[i].getName().equals("all")) {
                    File work[] = files[i].listFiles();
                    for (int j = 0; j < work.length; j++) {
                        FileOutputStream fos = new FileOutputStream(GetRe.getPath() + "all" + File.separator + work[j].getName());
                        FileInputStream fis = new FileInputStream(work[j]);
                        int p;
                        while ((p = fis.read(bt)) != -1) {
                            fos.write(bt, 0, p);
                            fos.flush();
                        }
                        fos.close();
                        fis.close();
                    }
                }
            }
            ServletOutputStream out = response.getOutputStream();
            Logging.daochuHM(session,true);
            toZip(new File(GetRe.getPath()+"all"),out,true);
        }
        else {
            response.getWriter().print("<font font-size=100 color=red >"+users.getUsername()+"已检测到你非法登录，下不为例"+"</font>");
        }
    }
    public static void toZip(File sourceFile, OutputStream out, boolean KeepDirStructure)
            throws RuntimeException{
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            compress(sourceFile,zos,sourceFile.getName(),KeepDirStructure);
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }
                }
            }
        }
    }
}