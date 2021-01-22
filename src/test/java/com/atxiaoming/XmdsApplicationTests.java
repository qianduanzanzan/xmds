package com.atxiaoming;

import com.atxiaoming.entity.OssTemplate;
import com.atxiaoming.entity.ProdSku;
import com.atxiaoming.mapper.ProdSkuMapper;
import com.atxiaoming.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

	@Test
	void contextLoads() {
		QueryWrapper wrapper = new QueryWrapper();
		wrapper.eq("prod_id",1);
		List<ProdSku> prodSkus = prodSkuMapper.selectList(wrapper);
		System.out.println(prodSkus.equals(new ArrayList<>()));
	}

}
