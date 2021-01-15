package com.atxiaoming.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateUserVo {
    private Integer id;
    private String userName;
    private MultipartFile avatar;
}
