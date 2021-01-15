package com.atxiaoming;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootTest
class XmdsApplicationTests {

	@Test
	void contextLoads() {
		String str = "https://xmcommunity.oss-cn-beijing.aliyuncs.com/pic/0c068cba4a3f_avatar.jpg";
		String newStr = str.substring(47);
		System.out.println("--------------------------------------------------");
		System.out.println(newStr);
	}

}
