import VueRouter from 'vue-router'
let base = '/base'
if(window.mfe){
    base = window.mfe_nav_router_base_path + base
}

const router = new VueRouter({
    mode: 'history',
    base: base,
    routes: [
        {
            path: '/dict',
            component: () => import('@/views/dict/Dict.vue'),
            meta: {
                // 名称编码，对应后台功能的code，主要是用来替换title，
                // 这样可以面包屑随后台设置而修改，如果后台分配的功能存在该值则以该code对象的名称替换title显示
                titleCode:'',
                title: '字典管理', // 名称，目前主要用于面包屑
                keepAlive: false, // 不需要缓存
                root:true //标识是一个根路由 用于面包屑
            }
        },
        {
            path: '/dict/dictAdd',
            component: () => import('@/views/dict/DictAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加字典'
            }
        },
        {
            path: '/dict/dictEdit/:dictId',
            component: () => import('@/views/dict/DictEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改字典'
            }
        },
        {
            path: '/func',
            component: () => import('@/views/func/Func.vue'),
            meta: {
                titleCode:'',
                title: '功能管理',
                root:true
            }
        },
        {
            path: '/func/funcAdd',
            component: () => import('@/views/func/FuncAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加功能'
            }
        },
        {
            path: '/func/funcEdit/:funcId',
            component: () => import('@/views/func/FuncEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改功能'
            }
        },
        {
            path: '/func/assignRole/:funcId',
            component: () => import('@/views/func/FuncAssignRole.vue'),
            meta: {
                titleCode:'',
                title: '功能分配角色'
            }
        },
        {
            path: '/comp',
            component: () => import('@/views/comp/Comp.vue'),
            meta: {
                titleCode:'',
                title: '公司管理',
                root:true
            }
        },
        {
            path: '/comp/compAdd',
            component: () => import('@/views/comp/CompAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加公司'
            }
        },
        {
            path: '/comp/compEdit/:compId',
            component: () => import('@/views/comp/CompEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改公司'
            }
        },
        {
            path: '/dept',
            component: () => import('@/views/dept/Dept.vue'),
            meta: {
                titleCode:'',
                title: '部门管理',
                root:true
            }
        },
        {
            path: '/dept/deptAdd',
            component: () => import('@/views/dept/DeptAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加部门'
            }
        },
        {
            path: '/dept/deptEdit/:deptId',
            component: () => import('@/views/dept/DeptEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改部门'
            }
        },
        {
            path: '/user',
            component: () => import('@/views/user/User.vue'),
            meta: {
                titleCode:'',
                title: '用户管理',
                root:true
            }
        },
        {
            path: '/user/userAdd',
            component: () => import('@/views/user/UserAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加用户'
            }
        },
        {
            path: '/user/userEdit/:userId',
            component: () => import('@/views/user/UserEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改用户'
            }
        },
        {
            path: '/user/updatePwd',
            component: () => import('@/views/user/UpdatePwd.vue'),
            meta: {
                titleCode:'',
                title: '修改密码',
                root:true
            }
        },
        {
            path: '/user/userinfo/current',
            component: () => import('@/views/user/Userinfo.vue'),
            meta: {
                titleCode:'',
                title: '个人信息',
                root:true
            }
        },
        {
            path: '/user/assignRole/:userId',
            component: () => import('@/views/user/UserAssignRole.vue'),
            meta: {
                titleCode:'',
                title: '用户分配角色'
            }
        },
        {
            path: '/user/assignFunc/:userId',
            component: () => import('@/views/user/UserAssignFunc.vue'),
            meta: {
                titleCode:'',
                title: '用户分配功能'
            }
        },
        {
            path: '/user/userPost/:userId?',
            component: () => import('@/views/user/userpost/UserPost.vue'),
            meta: {
                titleCode:'',
                title: '用户岗位管理',
                root:true
            }
        },
        {
            path: '/user/userPost/userPostAdd/:userId',
            component: () => import('@/views/user/userpost/UserPostAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加用户岗位'
            }
        },
        {
            path: '/user/userPost/userPostEdit/:userPostId',
            component: () => import('@/views/user/userpost/UserPostEdit.vue'),
            meta: {
                titleCode:'',
                title: '添加用户岗位'
            }
        },
        {
            path: '/user/userPost/userPostAssignRole/:userPostId',
            component: () => import('@/views/user/userpost/UserPostAssignRole.vue'),
            meta: {
                titleCode:'',
                title: '用户岗位分配角色'
            }
        },
        {
            path: '/user/userPost/userPostAssignDataScope/:userPostId',
            component: () => import('@/views/user/userpost/UserPostAssignDataScope.vue'),
            meta: {
                titleCode:'',
                title: '用户岗位分配数据范围约束'
            }
        },
        {
            path: '/user/userPost/userPostAssignFunc/:userPostId',
            component: () => import('@/views/user/userpost/UserPostAssignFunc.vue'),
            meta: {
                titleCode:'',
                title: '用户岗位分配功能'
            }
        },
        {
            path: '/user/userIdentifier/:userId',
            component: () => import('@/views/user/useridentifier/UserIdentifier.vue'),
            meta: {
                titleCode:'',
                title: '用户帐号标识管理',
                root:true
            }
        },
        {
            path: '/user/userIdentifier/userIdentifierAdd/:userId',
            component: () => import('@/views/user/useridentifier/UserIdentifierAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加用户帐号标识'
            }
        },
        {
            path: '/user/UserAssignDataScope/:userId',
            component: () => import('@/views/user/UserAssignDataScope.vue'),
            meta: {
                titleCode:'',
                title: '用户分配数据范围约束'
            }
        },
        {
            path: '/role',
            component: () => import('@/views/role/Role.vue'),
            meta: {
                titleCode:'',
                title: '角色管理',
                root:true
            }
        },
        {
            path: '/role/roleAdd',
            component: () => import('@/views/role/RoleAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加角色'
            }
        },
        {
            path: '/role/roleEdit/:roleId',
            component: () => import('@/views/role/RoleEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改角色'
            }
        },
        {
            path: '/role/assignFunc/:roleId',
            component: () => import('@/views/role/RoleAssignFunc.vue'),
            meta: {
                titleCode:'',
                title: '角色分配功能'
            }
        },
        {
            path: '/role/assignUser/:roleId',
            component: () => import('@/views/role/RoleAssignUser.vue'),
            meta: {
                titleCode:'',
                title: '角色分配用户'
            }
        },
        {
            path: '/role/RoleAssignDataScope/:roleId',
            component: () => import('@/views/role/RoleAssignDataScope.vue'),
            meta: {
                titleCode:'',
                title: '角色分配数据范围约束'
            }
        },
        {
            path: '/post',
            component: () => import('@/views/post/Post.vue'),
            meta: {
                titleCode:'',
                title: '岗位管理',
                root:true
            }
        },
        {
            path: '/post/postAdd',
            component: () => import('@/views/post/PostAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加岗位'
            }
        },
        {
            path: '/post/postEdit/:postId',
            component: () => import('@/views/post/PostEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改岗位'
            }
        },
        {
            path: '/post/postAssignDataScope/:postId',
            component: () => import('@/views/post/PostAssignDataScope.vue'),
            meta: {
                titleCode:'',
                title: '岗位分配数据范围'
            }
        },
        {
            path: '/post/postAssignFunc/:postId',
            component: () => import('@/views/post/PostAssignFunc.vue'),
            meta: {
                titleCode:'',
                title: '岗位分配功能'
            }
        },
        // 职务
        {
            path: '/job',
            component: () => import('@/views/job/Job.vue'),
            meta: {
                titleCode:'',
                title: '职务管理',
                root:true
            }
        },
        {
            path: '/job/jobAdd',
            component: () => import('@/views/job/JobAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加职务'
            }
        },
        {
            path: '/job/jobEdit/:jobId',
            component: () => import('@/views/job/JobEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改职务'
            }
        },
        // 职级
        {
            path: '/jobLevel',
            component: () => import('@/views/joblevel/JobLevel.vue'),
            meta: {
                titleCode:'',
                title: '职级管理',
                root:true
            }
        },
        {
            path: '/jobLevel/jobLevelAdd',
            component: () => import('@/views/joblevel/JobLevelAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加职级'
            }
        },
        {
            path: '/jobLevel/jobLevelEdit/:jobLevelId',
            component: () => import('@/views/joblevel/JobLevelEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改职级'
            }
        },
        // 区域
        {
            path: '/area',
            component: () => import('@/views/area/Area.vue'),
            meta: {
                titleCode:'',
                title: '区域管理',
                root:true
            }
        },
        {
            path: '/area/areaAdd',
            component: () => import('@/views/area/AreaAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加区域'
            }
        },
        {
            path: '/area/areaEdit/:areaId',
            component: () => import('@/views/area/AreaEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改区域'
            }
        },
        // 参数配置
        {
            path: '/paramConfig',
            component: () => import('@/views/paramconfig/ParamConfig.vue'),
            meta: {
                titleCode:'',
                title: '参数配置管理',
                root:true
            }
        },
        {
            path: '/paramConfig/paramConfigAdd',
            component: () => import('@/views/paramconfig/ParamConfigAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加参数配置'
            }
        },
        {
            path: '/paramConfig/paramConfigEdit/:paramConfigId',
            component: () => import('@/views/paramconfig/ParamConfigEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改参数配置'
            }
        },
        // 数据约束
        {
            path: '/dataConstraint/dataObject',
            component: () => import('@/views/dataconstraint/dataobject/DataObject.vue'),
            meta: {
                titleCode:'',
                title: '数据对象管理',
                root:true
            }
        },
        {
            path: '/dataConstraint/dataObjectAdd',
            component: () => import('@/views/dataconstraint/dataobject/DataObjectAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加数据对象'
            }
        },
        {
            path: '/dataConstraint/dataObjectEdit/:dataObjectId',
            component: () => import('@/views/dataconstraint/dataobject/DataObjectEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改数据对象'
            }
        },
        {
            path: '/dataConstraint/dataScope',
            component: () => import('@/views/dataconstraint/datascope/DataScope.vue'),
            meta: {
                titleCode:'',
                title: '数据范围约束管理',
                root:true
            }
        },
        {
            path: '/dataConstraint/dataScopeAdd/:dataObjectId?',
            component: () => import('@/views/dataconstraint/datascope/DataScopeAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加数据范围约束'
            }
        },
        {
            path: '/dataConstraint/dataScopeEdit/:dataScopeId',
            component: () => import('@/views/dataconstraint/datascope/DataScopeEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改数据范围约束'
            }
        },
        {
            path: '/dataConstraint/dataScopeAssignDataTree/:dataScopeId',
            component: () => import('@/views/dataconstraint/datascope/DataScopeAssignDataTree.vue'),
            meta: {
                titleCode:'',
                title: '数据范围分配自定义数据'
            }
        },
        {
            path: '/dataConstraint/dataScopeAssignDataTable/:dataScopeId',
            component: () => import('@/views/dataconstraint/datascope/DataScopeAssignDataTable.vue'),
            meta: {
                titleCode:'',
                title: '数据范围分配自定义数据'
            }
        },
        // 应用
        {
            path: '/application',
            component: () => import('@/views/application/Application.vue'),
            meta: {
                titleCode:'',
                title: '后端应用管理',
                root:true
            }
        },
        {
            path: '/application/applicationAdd',
            component: () => import('@/views/application/ApplicationAdd.vue'),
            meta: {
                titleCode:'',
                title: '添加后端应用'
            }
        },
        {
            path: '/application/applicationEdit/:applicationId',
            component: () => import('@/views/application/ApplicationEdit.vue'),
            meta: {
                titleCode:'',
                title: '修改后端应用'
            }
        }
    ]
})

export default router