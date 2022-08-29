package com.xulei.cn.utils;

import cn.hutool.json.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.FileInputStream;

/**
 * 七牛云工具
 */
public class QiniuUtil {

    private static Auth auth = Auth.create(Const.QN_ACCESS_KEY, Const.QN_SECRET_KEY);

    /**
     * 下载文件
     *
     * @param url
     *            七牛资源url
     * @return
     */
    public static String download(String url) {
        String downloadRUL = auth.privateDownloadUrl(url);// 调用privateDownloadUrl方法生成下载链接
        System.out.println("downloadRUL:" + downloadRUL);
        return downloadRUL;
    }

    public static String uploadImgToQiNiu(FileInputStream file, String filename) {
        // 构造一个带指定Zone对象的配置类，注意后面的zone各个地区不一样的
        Configuration cfg = new Configuration();
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成*
        try {
            String upToken = auth.uploadToken(Const.QN_BUCKETNAME);
            try {
                Response response = uploadManager.put(file, filename, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new JSONObject(response.bodyString()).toBean(DefaultPutRet.class);
                // 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
                String returnPath = Const.QN_DOMAIN + "/" + putRet.key;
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
