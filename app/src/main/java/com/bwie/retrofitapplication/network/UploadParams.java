package com.bwie.retrofitapplication.network;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建（新增）日志
 * ../api/projectDiary/add
 * 请求参数：
 * projectId, String （必填）
 * diaryType， String（必填） 文本：text 文件：file  多媒体资源：multimedia（视频语音图片等。）
 * userId， String（必填）
 * resourceList ,   List 数据集合（必填）
 * 包含以下字段：
 * type  文件：file  图片：img  文字：text  视频 mv 语音：voic
 * url :资源存储路径
 * content ：描述信息
 * name: 资源自定义名称（默认为时间戳 20170906123030）
 * <p>
 * Created by liqy on 2017/9/6.
 */

public class UploadParams {

    public String projectId;
    public String diaryType;
    public String userId;
    public List<UploadLog> resourceList;

    public UploadParams() {
        this.projectId = "1112";
        this.diaryType = "text";
        this.userId = "1";
        this.resourceList = new ArrayList<>();
        UploadLog uploadLog = new UploadLog();
        resourceList.add(uploadLog);

    }

    public static class UploadLog {

        public String type;
        public String url;
        public String content;
        public String name;

        public UploadLog() {
            this.type = "text";
            this.url = "";
            this.content = "texttexttexttexttext";
            this.name = "title";
        }

    }


}
