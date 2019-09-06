package ${package.Controller};

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
@Api(tags = "${table.comment!}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass}<${table.serviceName},${entity}WebMapper, ${entity}Vo, ${entity}, ${entity}CreateForm,${entity}UpdateForm,${entity}ListPageForm> {
<#else>
public class ${table.controllerName} {
</#if>

    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 ${table.comment!} 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[${table.comment!}]单表创建/添加数据")
     @RequiresPermissions("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public ${entity}Vo create(@RequestBody @Valid ${entity}CreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[${table.comment!}]单表根据ID查询")
     @RequiresPermissions("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public ${entity}Vo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[${table.comment!}]单表根据ID删除")
     @RequiresPermissions("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public Object deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[${table.comment!}]单表根据ID更新")
     @RequiresPermissions("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public ${entity}Vo update(@PathVariable Long id,@RequestBody @Valid ${entity}UpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[${table.comment!}]单表分页列表")
    @RequiresPermissions("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<${entity}Vo> listPage(${entity}ListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
</#if>
