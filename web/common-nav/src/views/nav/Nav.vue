<template>
    <container type="top-header" class="g-width100 g-height100" header-style="padding:0">
        <Header slot="header">

        </Header>
        <el-scrollbar slot="aside" class="g-height100 scroll-bar-view" wrapStyle="overflow:auto;">
        <el-menu
                :default-openeds="defaultActive"
                class="g-height100"
                v-loading="funcDataLoading"
                 element-loading-background="#304156"
                 background-color="#304156"
                 text-color="#fff"
                 active-text-color="#ffd04b"
        >
            <template v-for="(item,index) in funcData">
                <nav-sub-menu v-if="item.children && item.children.length > 0" :item="item" :key="index"></nav-sub-menu>
                <nav-menu-item v-else-if="item.node.typeDictCode == 'page'" :item="item" :key="index"></nav-menu-item>
            </template>
        </el-menu>
        </el-scrollbar>
        <mfe-loader
                slot="main"
                :loading-id="loadingId"
                    :container-id="containerId"
        ></mfe-loader>
    </container>
</template>
<script>
    import NavMenuItem from './NavMenuItem.vue'
    import NavSubMenu from './NavSubMenu.vue'
    import Header from '../topheader/Header.vue'
    import MfeLoader from 'common-util/src/components/mfe/MfeLoader.vue'
    import Container from 'common-util/src/components/page/Container.vue'
    export default {
        name: 'Nav',
        components: {
            NavMenuItem,
            Header,
            NavSubMenu,
            Container,
            MfeLoader
        },
        computed:{
            defaultActive(){
                if(this.funcData && this.funcData.length > 0){
                    return ['' + this.funcData[0].node.id]
                }
                return ['']
            }
        },
        data () {
            return {
                loadingId: 'app-common-nav-container-loading',
                containerId: 'app-common-nav-container',
                funcData: [],
                funcDataLoading: false,
                funcDataErrorMsg:'发生了未知的错误，服务端未能返回错误信息'
            }
        },
        mounted () {
            this.getFuncData()
        },
        methods: {
            getFuncData(){
                this.funcDataLoading = true
                this.axios.get('/func/tree/nav')
                    .then(res =>{
                        this.funcData = res.data.data
                    })
                    .catch(error =>{
                        if(error.response){
                            let status = error.response.status
                            if(window.mfe && status == 401){
                                window.mfe_vue_bus.$emit('login')
                            }else {
                                this.funcDataErrorMsg = error.response.data.msg
                                this.$message.error(error.response.data.msg)
                            }
                        }

                    }).finally(()=>{
                        this.funcDataLoading = false
                    })
            }
        },
        watch: {

        }
    }
</script>
<style scoped>
</style>
<style>
    .el-scrollbar__view{
        overflow: hidden;
        height: 100%;
    }
</style>
