<template>
    <el-container class="g-height100 g-width100 g-padding0" style="background-color: #3c8dbc;">
        <el-aside width="200px" class="g-flex-center-all g-font-size-elg g-color-fff" style="background-color: #367fa9;">
            <i class="el-icon-grape"> grape</i>
        </el-aside>
        <el-main class="g-color-fff g-padding0">
            <el-row type="flex" class="g-height100" justify="space-between">
                <el-col class="g-height100 g-flex-center-v"></el-col>
                <el-col class="g-height100 g-flex-center-v g-flex-align-right">
                    <!-- 右侧 头像和用户名 -->
                    <UserinfoDropdown class="item"></UserinfoDropdown>
                </el-col>
            </el-row>

        </el-main>
    </el-container>
</template>

<script>
    import UserinfoDropdown from './UserinfoDropdown.vue'

    export default {
        components: {
            UserinfoDropdown
        }

    }
</script>

<style scoped>
.item{
    height: 100%;
}
.item:hover{
    background-color: #367fa9;
}
</style>