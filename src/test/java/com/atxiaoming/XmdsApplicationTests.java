package com.atxiaoming;

import com.atxiaoming.entity.OssTemplate;
import com.atxiaoming.entity.ProdSku;
import com.atxiaoming.mapper.ProdSkuMapper;
import com.atxiaoming.utils.CommonUtils;
import com.atxiaoming.utils.TokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class XmdsApplicationTests {

	@Autowired
	private ProdSkuMapper prodSkuMapper;

	@Autowired
	private TokenUtil tokenUtil;

	@Test
	void contextLoads() {
		String phone = tokenUtil.getCusIdFromToken("eyJhbGciOiJIUzI1NiIsIlR5cGUiOiJKd3QiLCJ0eXAiOiJKV1QifQ.eyJhIjoxNjExOTAyMzg3LCJwaG9uZSI6IjE4NTcxNjE5NTQ5In0.c7QCub1QouEpaPsFAp9ksG7wp-rQpj4Q-8B-83E5STw");
//		String password = "1996zdm1115";
//		String md5_password = DigestUtils.md5DigestAsHex(password.getBytes());
//		System.out.println(md5_password);
		System.out.println(phone);
	}

}
