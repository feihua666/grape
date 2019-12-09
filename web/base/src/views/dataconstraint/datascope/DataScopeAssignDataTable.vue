<template>
    <TableForRelBind v-if="initReady" :checked-data-url="'/datascopecustomrel/datascope/' + $route.params.dataScopeId"
                    :data="dataUrl"
                    submit-url="/datascopecustomrel/datascope/assign/data"
                    :main-value="$route.params.dataScopeId"
                    main-prop="dataScopeId"
                    checked-prop="checkedDataIds"
                    unchecked-prop="uncheckedDataIds"
                    :clear-all-url="'/datascopecustomrel/datascope/' + $route.params.dataScopeId"
                     :columns="tableColumns"
                     :lazyload="lazyload"
    >

    </TableForRelBind>
</template>

<script>
    import TableForRelBind from 'common-util/src/components/page/TableForRelBind.vue'
    export default {
        components:{
            TableForRelBind
        },
        data (){
            return {
                initReady:false,
                dataUrl:'',
                lazyload: true,
                tableColumns:[
                    {
                        type:'selection'
                    },
                    {
                        prop: 'name',
                        label:'名称'
                    }
                ]
            }
        },
        mounted(){
            this.initData()
        },
        methods:{
            initData(){
                this.axios.get('/dataobject/dataobject/' + this.$route.params.dataScopeId)
                    .then(res=>{
                        let dataobject = res.data.data
                        this.dataUrl=dataobject.dataCustomUrl
                        if(!this.dataUrl){
                            this.$message.error('数据对象未设置自定义url')
                        }else {
                            if(dataobject.tableModeColumns){
                                this.tableColumns = JSON.parse(dataobject.tableModeColumns)
                            }
                            this.lazyload = dataobject.isDataLazy

                            this.initReady = true
                        }
                    }).catch(error=>{
                    if(error.response){
                        this.$message.error(error.response.data.msg)
                    }else {
                        this.$message.error("未知错误")
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>