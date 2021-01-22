package com.atxiaoming.controller;


import com.atxiaoming.service.impl.ProdServiceImpl;
import com.atxiaoming.service.impl.UserMenuServiceImpl;
import com.atxiaoming.vo.ProdEditVo;
import com.atxiaoming.vo.ProdPaginationVo;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小明
 * @since 2021-01-14
 */
@Api(description = "产品相关接口")
@RestController
@RequestMapping("/prod")
@Slf4j
public class ProdController {

    @Autowired
    private ProdServiceImpl prodServiceImpl;

    @ApiOperation(value = "新增一个产品" ,  notes="新增一个产品")
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public RespBean addProd(@RequestParam("prodName") String prodName,
                            @RequestParam("categoryId") Integer categoryId,
                            @RequestParam("imgs") String[] imgs,
                            @RequestParam("description") String description){
        return prodServiceImpl.addProd(prodName,categoryId,imgs,description);
    }

    @ApiOperation(value = "修改产品" ,  notes="修改产品")
    @RequestMapping(value="/eidt",method= RequestMethod.POST)
    public RespBean editProd(@RequestBody ProdEditVo prodEditVo){
        return prodServiceImpl.editProd(prodEditVo);
    }

    @ApiOperation(value = "获取产品分页信息" ,  notes="获取产品分页信息")
    @RequestMapping(value="/getPage",method= RequestMethod.POST)
    public RespBean getProdPage(@RequestBody ProdPaginationVo prodPaginationVo){
        return prodServiceImpl.getProdPage(prodPaginationVo);
    }

    @ApiOperation(value = "获取产品详情" ,  notes="获取产品详情")
    @RequestMapping(value="/getInfo",method= RequestMethod.POST)
    public RespBean getProdPage(@RequestParam("id") Integer id){
        return prodServiceImpl.getProdInfo(id);
    }

    @ApiOperation(value = "修改产品状态" ,  notes="修改产品状态")
    @RequestMapping(value="/changeStatus",method= RequestMethod.POST)
    public RespBean changeProdStatus(@RequestParam("id") Integer id){
        return prodServiceImpl.changeProdStatus(id);
    }

}
