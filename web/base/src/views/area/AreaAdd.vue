<template>
    <div>
    <Form
          :form-items="formItems"
    ></Form>
        <BaiduMapDialog ref="bMapDialog">

            <el-input placeholder="输入关键字定位">
                <el-button slot="append"
                           @click="bClick">搜索</el-button>
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
                formItems:[
                    {
                        field: {
                            name: 'parentId',
                            value:this.$route.query.id,
                            parentId__label:this.$route.query.name
                        },
                        element:{
                            type: 'inputSelectTree',
                            inputSelectTree:{
                                dataUrl:'/area/tree'
                            },
                            disabled: !!this.$route.query.id,
                            placeholder: '不填写为根节点',
                            label: '父级'
                        }
                    },
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
                        element:{
                            type: 'button',
                            button:[
                                {
                                    action: 'submit',
                                    requestMethod:'post',
                                    url: '/area',
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
        methods:{
            longitudeBtnClick () {

                this.$refs.bMapDialog.show()
            }
        }
    }
</script>

<style scoped>

</style>