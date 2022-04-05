package fun.china1.service;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.CreatePersonRequest;
import com.tencentcloudapi.iai.v20200303.models.CreatePersonResponse;
import com.tencentcloudapi.iai.v20200303.models.DeletePersonRequest;
import com.tencentcloudapi.iai.v20200303.models.DeletePersonResponse;

public class FaceService {

    private final String ID = "";
    private final String KEY = "";
    private final String GroupId = "";

    //人脸操作的业务
    public String createPersonFace(String imageBase64,Long gender,String personName,String personId){
        Credential credential = new Credential(ID,KEY);
        IaiClient iaiClient = new IaiClient(credential,"ap-beijing");
        CreatePersonRequest request = new CreatePersonRequest();
        request.setGroupId(GroupId);
        request.setGender(gender);
        request.setPersonName(personName);
        request.setPersonId(personId);
        request.setImage(imageBase64);
        String result = "";
        try {
            CreatePersonResponse response = iaiClient.CreatePerson(request);
            result = CreatePersonResponse.toJsonString(response);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        //解析。。。。。
        return result;
    }
    public void deletePersonFace(String personId){
        Credential credential = new Credential(ID,KEY);
        IaiClient iaiClient = new IaiClient(credential,"ap-beijing");
        DeletePersonRequest request=new DeletePersonRequest();
        request.setPersonId(personId);
        try {
            DeletePersonResponse response=iaiClient.DeletePerson(request);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
    }
}
