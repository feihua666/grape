<template>
    <el-dialog
            title="提示"
            :visible.sync="localVisible"
            v-on:opened="$emit('opened')"
            v-on:closed="$emit('closed')"
            width="30%">
        <slot></slot>
        <BaiduMap ref="baiduMap" :m-click="mClick" v-on:mClick="(e)=>{$emit('mClick',e)}" v-on:ready="$emit('ready')"></BaiduMap>
        <span slot="footer" class="dialog-footer">
    <el-button @click="localVisible = false">取 消</el-button>
    <el-button type="primary" @click="submit">确 定</el-button>
  </span>
    </el-dialog>
</template>

<script>
    import BaiduMap from './BaiduMap.vue'
    export default {
        name: "BaiduMapDialog",
        components:{BaiduMap},
        props:{
            visible: {
                type: Boolean,
                default: false
            },
            // 地图点击
            mClick:{
                type: Function
            },
            // 确定按钮点击
            sureClick:{
                type: Function
            }
        },
        data(){
            return {
                localVisible: this.visible
            }
        },
        mounted(){

        },
        methods:{
            getBaiduMap(){
                return this.$refs.baiduMap
            },
            show(){
                this.localVisible = true
            },
            hide(){
                this.localVisible = false
            },
            submit(){
                if(this.sureClick){
                    let r = this.sureClick()
                    if(r == true){
                        this.hide()
                    }
                }else {
                    this.hide()
                }
            }
        },
        watch:{
            visible(val){
                this.localVisible = val
            }
        }
    }
</script>

<style scoped>

</style>