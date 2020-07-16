package com.staticresource.service;
import com.staticresource.dao.AvatarDao;
import com.staticresource.entity.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Service
public class AvatarServcie {

    @Resource
    private AvatarDao avatarDao;

    @Transactional
    public String UpdateUserAvatar(String url,String filePath, MultipartFile file, BigInteger userID) throws IOException {
        // 保存图片的路径，图片上传成功后，将路径保存到数据库
        // 获取原始图片的扩展名
        String originalFilename = file.getOriginalFilename();
        // 生成文件新的名字
        String newFileName = UUID.randomUUID() + originalFilename;
        // 封装上传文件位置的全路径
        File targetFile = new File(filePath, newFileName);
        file.transferTo(targetFile);
        String url1 = url +newFileName;
        // 保存到数据库
        avatarDao.UpdateUserAvatar(userID,url1);
        return url1;
    }

    public String PreservePhotoWall( String url,String filePath,MultipartFile file,BigInteger userID) throws IOException {
        // 保存图片的路径，图片上传成功后，将路径保存到数据库
        // 获取原始图片的扩展名
        String originalFilename = file.getOriginalFilename();
        // 生成文件新的名字
        String newFileName = UUID.randomUUID() + originalFilename;
        // 封装上传文件位置的全路径
        File targetFile = new File(filePath, newFileName);
        file.transferTo(targetFile);
        String url1 = url +newFileName;
        // 保存到数据库
        avatarDao.PreservePhotoWall(url1,userID);
        return url1;
    }

    public List<String> GetUserPhotoWall(BigInteger userID){
        List<String> photoList =  avatarDao.GetUserPhotoWall(userID);
        return photoList;
    }
}
