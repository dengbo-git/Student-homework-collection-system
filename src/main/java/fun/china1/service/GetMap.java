package fun.china1.service;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.SearchFacesRequest;
import com.tencentcloudapi.iai.v20200303.models.SearchFacesResponse;
import fun.china1.entity.Users;
import fun.china1.utils.DBUtil;
import fun.china1.utils.JsonUtils;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


//判断当前用户是否为已存在的用户
public class GetMap {
    public static Users sibie(String img) throws IOException {

        //创建应用者信息
        Credential credential=new Credential("AKIDtnSr0xKCuNo6oHvAYmkUO2f3FCxTuvoy","2g5C5lbp66B0YrlS2YtyoNhkbJVUJuWs");
        //创建客户端
        IaiClient client=new IaiClient(credential,"ap-beijing");
        //进行人脸对比
        SearchFacesRequest searchFacesRequest=new SearchFacesRequest();
        String[] groupIds={"dengbo1"};
        //将数组添加到请求对象中
        searchFacesRequest.setGroupIds(groupIds);
        //设置要查询的信息
        searchFacesRequest.setImage(img);
        try{
            SearchFacesResponse searchFacesResponse=client.SearchFaces(searchFacesRequest);
            HashMap<Object,Object> map=new HashMap<>();
            JsonUtils.getJsonElementForKey(SearchFacesResponse.toJsonString(searchFacesResponse),"Score",map);
            Users users;
            if(!map.isEmpty()){
            String personId= (String) map.get("PersonId");
                DBUtil dbUtil=new DBUtil();
                String sql="select *from t_face_user where personid="+personId+"";
                PreparedStatement ps= DBUtil.getConnection().prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                while (rs.next()){
                    String username=rs.getString("username");
                    String usergender=rs.getString("usergender");
                    String personid=rs.getString("personid");
                    Integer finish=rs.getInt("homework");
                    Integer admin=rs.getInt("admin");
                     users=new Users(username,Long.parseLong(usergender),personid,finish,admin);
                    return users;
                }
                DBUtil.close();
            }
            else {
                return null;
            }
        }
        catch (TencentCloudSDKException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
