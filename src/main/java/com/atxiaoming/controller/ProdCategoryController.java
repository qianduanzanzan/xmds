package com.atxiaoming.controller;


import com.atxiaoming.service.impl.ProdCategoryServiceImpl;
import com.atxiaoming.vo.CategoryPagenationVo;
import com.atxiaoming.vo.EditMenuVo;
import com.atxiaoming.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小明
 * @since 2021-01-14
 */
@Api(description = "产品分类相关接口")
@RestController
@RequestMapping("/prodCategory")
@Slf4j
public class ProdCategoryController {

    @Autowired
    private ProdCategoryServiceImpl prodCategoryService;

    @ApiOperation(value = "新增产品分类" ,  notes="新增产品分类")
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public RespBean add(@RequestParam("category") String categoryName){
        return prodCategoryService.add(categoryName);
    }

    @ApiOperation(value = "修改分类状态" ,  notes="修改分类状态")
    @RequestMapping(value="/changeStatus",method= RequestMethod.POST)
    public RespBean changeStatus(@RequestParam("id") Integer id){
        return prodCategoryService.changeStatus(id);
    }

    @ApiOperation(value = "修改分类名称" ,  notes="修改分类名称")
    @RequestMapping(value="/edit",method= RequestMethod.POST)
    public RespBean edit(@RequestParam("id") Integer id,
                         @RequestParam("categoryName") String categoryName){
        return prodCategoryService.edit(id,categoryName);
    }

    @ApiOperation(value = "获取分类的分页信息" ,  notes="获取分类的分页信息")
    @RequestMapping(value="/getPage",method= RequestMethod.POST)
    public RespBean getCategoryPage(@RequestBody CategoryPagenationVo categoryPagenationVo){
        return prodCategoryService.getCategoryPage(categoryPagenationVo);
    }
}
