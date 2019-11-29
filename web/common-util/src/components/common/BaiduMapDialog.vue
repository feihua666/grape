<template>
    <el-dialog
            title="提示"
            :visible.sync="localVisible"
            v-on:opened="$emit('opened')"
            v-on:closed="$emit('closed')"
            width="30%">
        <slot></slot>
        <BaiduMap ref="baiduMap"></BaiduMap>
        <span slot="footer" class="dialog-footer">
    <el-button @click="localVisible = false">取 消</el-button>
    <el-button type="primary" @click="localVisible = false">确 定</el-button>
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
            }
        },
        data(){
            return {
                localVisible: this.visible
            }
        },
        methods:{
            handleClose (){
            },
            getBaiduMap(){
                return this.$refs.baiduMap
            },
            show(){
                this.localVisible = true
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