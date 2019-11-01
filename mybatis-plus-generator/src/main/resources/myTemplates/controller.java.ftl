package ${package.Controller};

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import ${cfg.formPackage}.${entity}CreateForm;
import ${cfg.formPackage}.${entity}UpdateForm;
import ${cfg.formPackage}.${entity}ListPageForm;
import ${cfg.voPackage}.${entity}Vo;
import ${cfg.mapperWebPackage}.${entity}WebMapper;
import org.springframework.beans.factory.annotation.Autowired;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import java.util.List;
<#if cfg.treeTable>
import grape.common.rest.vo.TreeNodeVo;
</#if>
/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen?replace('-po', '' )}<#else>${table.entityPath}</#if>")
@Api(tags = "${table.comment!}相关接口")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass}<${entity}Vo, ${entity}> {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${entity}WebMapper currentWebMapper;
    @Autowired
    private ${table.serviceName} currentService;



     @ApiOperation("添加${table.comment!}")
     @RequiresPermissions("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public ${entity}Vo create(@RequestBody @Valid ${entity}CreateForm cf) {
         ${entity} po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("根据ID查询${table.comment!}")
     @RequiresPermissions("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public ${entity}Vo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除${table.comment!}")
     @RequiresPermissions("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新${table.comment!}")
     @RequiresPermissions("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public ${entity}Vo update(@PathVariable String id,@RequestBody @Valid ${entity}UpdateForm uf) {
         ${entity} po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询${table.comment!}")
    @RequiresPermissions("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<${entity}Vo> listPage(${entity}ListPageForm listPageForm) {
         ${entity} po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

    <#if cfg.treeTable>
    @ApiOperation("${table.comment!}树")
    @RequiresPermissions("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>:single:tree")
    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeNodeVo<${entity}Vo>> tree( String parentId) {
        return super.listToTreeNodeVo(super.getByParentId(parentId));
    }
    </#if>
}
</#if>
