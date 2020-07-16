package com.staticresource.controller;

import com.staticresource.componment.CreatUrl;
import com.staticresource.entity.CommonResult;
import com.staticresource.service.AvatarServcie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
public class AvatarController {
    @Resource
    AvatarServcie avatarServcie;

    @Resource
    CreatUrl creatUrl;

    @PostMapping(path = "/UpdateUserAvatar")
    public CommonResult UpdateUserAvatar(@RequestParam("avatar") MultipartFile file,
                                         @RequestParam("id") String userID,
                                         HttpSession session,
                                         HttpServletRequest servletRequest) throws IOException {
        String baseurl =creatUrl.CreatBaseUrl(servletRequest);
        baseurl += "/Avatar/";
        String filePath = creatUrl.CreatFilePath(servletRequest,"/Avatar");
        String aa = avatarServcie.UpdateUserAvatar(baseurl, filePath, file, new BigInteger(userID));
        return new CommonResult(200, "修改成功", aa);
    }

    //保存照片墙
    @PostMapping(path = "/PreservePhotoWall")
    public CommonResult PreservePhotoWall(@RequestParam("file") MultipartFile file,
                                          @RequestParam("id") String userID,
                                          HttpServletRequest servletRequest) throws IOException {
        String baseurl =creatUrl.CreatBaseUrl(servletRequest);
        baseurl += "/PhotoWall/";
        String filePath = creatUrl.CreatFilePath(servletRequest,"/PhotoWall");
        String res = avatarServcie.PreservePhotoWall(baseurl,filePath,file,new BigInteger(userID));
        return new CommonResult(200,"成功",res);
    }

    //获取用户照片墙
    @GetMapping(path = "/GetUserPhotoWall")
    public CommonResult GetUserPhotoWall(@RequestParam("id") String userID) {
        List<String> res = avatarServcie.GetUserPhotoWall(new BigInteger(userID));
        return new CommonResult(200,"成功",res);
    }
    @GetMapping(path = "/test")
    public CommonResult test(){
        System.out.println("触发了");
       return new CommonResult(200,"success");
    }
}
