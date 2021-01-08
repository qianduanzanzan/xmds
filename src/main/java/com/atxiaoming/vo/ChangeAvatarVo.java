package com.atxiaoming.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ChangeAvatarVo {
    private Long id;
    private String avatar;
}
