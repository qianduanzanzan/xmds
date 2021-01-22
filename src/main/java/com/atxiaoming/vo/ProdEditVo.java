package com.atxiaoming.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProdEditVo {
    private Integer id;
    private String prodName;
    private Integer categoryId;
    private String categoryName;
    private String[] imgs;
    private String description;
}
