<template>
    <container type="left-aside" class="g-width100 g-height100" aside main>
        <el-menu slot="aside"
                :default-openeds="defaultActive"
                class="g-height100"
                active-text-color="#ffd04b"
                v-loading="funcDataLoading"
        >
            <template v-for="(item,index) in funcData">
                <nav-sub-menu v-if="item.children && item.children.length > 0" :item="item" :key="index"></nav-sub-menu>
                <nav-menu-item v-else-if="item.node.typeDictCode == 'page'" :item="item" :key="index"></nav-menu-item>
            </template>
        </el-menu>
        <mfe-loader
                slot="main"
                :loading-id="loadingId"
                    :container-id="containerId"
        />
    </container>
</template>
<script>
    import NavMenuItem from './NavMenuItem.vue'
    import NavSubMenu from './NavSubMenu.vue'
    import MfeLoader from 'common-util/src/components/mfe/MfeLoader.vue'
    import Container from 'common-util/src/components/page/Container.vue'
    export default {
        name: 'Nav',
        components: {
            NavMenuItem,
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
                        if(window.mfe && error.response.status == 401){
                            window.mfe_vue_bus.$emit('login')
                        }else {
                            if(error.response){
                                this.funcDataErrorMsg = error.response.data.message
                                this.$message.error(error.response.data.message)
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
