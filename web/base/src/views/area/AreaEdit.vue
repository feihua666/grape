<template>
    <div>
    <Form
          :form-items="formItems"
          :form-item-value="formData"
          :loading="formLoading"
    ></Form>
    <BaiduMapDialog ref="bMapDialog" v-on:mClick="mClick" :sure-click="sureClick" v-on:ready="mapReady">

        <el-input v-model="mapAddress" placeholder="输入关键字定位">
            <el-button slot="append"
                       @click="locateAddress">搜索</el-button>
        </el-input>
    </BaiduMapDialog>
    </div>
</template>

<script>
    import Form from 'common-util/src/components/page/Form.vue'
    import BaiduMapDialog from 'common-util/src/components/common/BaiduMapDialog.vue'

    export default {
        components:{
            Form,
            BaiduMapDialog
        },
        data(){
            return {
                mapDialogVisible:false,
                mapAddress:null,
                // 地图经纬度选择用
                mapPoint:null,
                formLoading: false,
                formData:null,
                formItems:[
                    {
                        field: {
                            name: 'name'
                        },
                        element:{
                            label: '名称',
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'typeDictId'
                        },
                        element:{
                            label: '类型',
                            type: 'selectDict',
                            selectDict:{
                                groupCode: 'area_type'
                            },
                            required: true
                        }
                    },
                    {
                        field: {
                            name: 'adminDivisionId'
                        },
                        element:{
                            label: '行政区划id',
                        }
                    },
                    {
                        field: {
                            name: 'longitude'
                        },
                        element:{
                            type: 'inputButton',
                            inputButton:{
                                bClick:this.longitudeBtnClick
                            },
                            label: '经度',
                        }
                    },
                    {
                        field: {
                            name: 'latitude'
                        },
                        element:{
                            label: '纬度',
                        }
                    },
                    {
                        field: {
                            name: 'seq'
                        },
                        element:{
                            type: 'inputNumber',
                            label: '排序',
                        }
                    },
                    {
                        field: {
                            name: 'remark'
                        },
                        element:{
                            type: 'textarea',
                            label: '备注',
                        }
                    },
                    {
                        field: {
                            name: 'version'
                        }
                    },
                    {
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'put',
                                    url: '/area/' +this.$route.params.areaId,
                                    label: '保存'
                                },
                                {
                                    action: 'reset',
                                    label: '重置',
                                    buttonType: 'default'
                                }
                            ]
                        }
                    }
                ]
            }
        },
        mounted(){
            this.getData(this.$route.params.areaId)
        },
        methods:{
            // 根据id获取数据
            getData(id){
                this.formLoading = true
                this.axios.get('/area/' + id).then(res => {
                    let data = res.data.data
                    this.formData = data
                }).catch(error => {
                    if(error.response){
                        if(error.response.status == 404){
                            this.$message.error('数据不存在，请刷新数据再试')
                        }else {
                            this.$message.error(error.response.data.msg)
                        }
                    }else {
                        this.$message.error('数据加载失败')
                    }
                }).finally(()=>{
                    this.formLoading = false
                })
            },
            longitudeBtnClick () {

                this.$refs.bMapDialog.show()

            },
            locateAddress(){
                let bdMapCom = this.$refs.bMapDialog.getBaiduMap()
                bdMapCom.clearOverlays()
                bdMapCom.getPoint(this.mapAddress,(point)=>{
                    if (point) {
                        bdMapCom.centerAndZoom(point)
                        bdMapCom.addMarker(point)

                        this.mapPoint = point
                    }else{
                        this.$message.error("您选择地址没有解析到结果!");
                    }
                })

            },
            mClick(e){
                let bdMapCom = this.$refs.bMapDialog.getBaiduMap()
                bdMapCom.clearOverlays()
                bdMapCom.getAddress(e.point,(addr,rs)=>{
                    this.mapAddress = addr.join(' ')
                    bdMapCom.addMarker(e.point)
                    this.mapPoint = e.point
                })
            },
            sureClick(){
                if(!this.mapPoint){
                    this.$message.error('请先定位一个位置')
                    return false
                }
                this.formData.latitude = this.mapPoint.lat
                this.formData.longitude = this.mapPoint.lng
                return true
            },
            // 发现在容器show的时候地图刚刚进行初始化，容易造成获取不到地图对象，这里进行初始化
            mapReady(){
                // 如果没有定位过，并且存在经纬度，在地图上回显
                if(!this.mapPoint && this.formData.longitude && this.formData.latitude){
                    let bdMapCom = this.$refs.bMapDialog.getBaiduMap()
                    let point = bdMapCom.newPoint(this.formData.longitude,this.formData.latitude)
                    bdMapCom.getAddress(point,(addr,rs)=>{
                        this.mapAddress = addr.join(' ')
                        bdMapCom.centerAndZoom(point)
                        bdMapCom.addMarker(point)
                        this.mapPoint = point
                    })

                }
            }
        }
    }
</script>

<style scoped>

</style>