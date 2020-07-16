package com.staticresource.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface AvatarDao {

    public void UpdateUserAvatar(@Param("userId") BigInteger userId,
                                 @Param("avatar") String avatar);

    public void PreservePhotoWall(@Param("filePath")String filePath,
                                    @Param("userId") BigInteger userId);

    public List<String> GetUserPhotoWall(@Param("userId")BigInteger userID);
}
