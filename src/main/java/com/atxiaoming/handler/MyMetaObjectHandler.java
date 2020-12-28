package com.atxiaoming.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//自动填充的插件
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setFieldValByName("createAt",dateFormat.format(new Date()),metaObject);
        this.setFieldValByName("updateAt",dateFormat.format(new Date()),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setFieldValByName("updateAt",dateFormat.format(new Date()),metaObject);
    }
}
