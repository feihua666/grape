<template>
    <el-container>
        <template v-if="type == 'simple'">
            <el-header v-if="$slots.header" :style="headerStyle" v-show="show.header !== false">
                <slot name="header"></slot>
            </el-header>
            <el-main v-if="$slots.main" :style="mainStyle" v-show="show.main !== false">
                <slot name="main"></slot>
            </el-main>
            <el-footer v-if="$slots.footer" :style="footerStyle" v-show="show.footer !== false">
                <slot name="footer"></slot>
            </el-footer>
        </template>
        <template v-if="type == 'top-header'">
            <el-header v-if="$slots.header" :style="headerStyle"  v-show="show.header !== false">
                <slot name="header"></slot>
            </el-header>
            <el-container>
                <el-aside v-if="$slots.aside" :style="asideStyle" v-show="show.aside !== false" width="200px">
                    <slot name="aside"></slot>
                </el-aside>
                <el-main v-if="$slots.main" :style="mainStyle" v-show="show.main !== false">
                    <slot name="main"></slot>
                </el-main>
                <el-footer v-if="$slots.footer" :style="footerStyle" v-show="show.footer !== false">
                    <slot name="footer"></slot>
                </el-footer>
            </el-container>
        </template>
        <template v-else-if="type == 'left-aside'">
            <el-aside v-if="$slots.aside" :style="asideStyle" v-show="show.aside !== false" width="200px">
                <slot name="aside"></slot>
            </el-aside>
            <el-container>
                <el-header v-if="$slots.header" :style="headerStyle"   v-show="show.header !== false">
                    <slot name="header"></slot>
                </el-header>
                <el-main v-if="$slots.main" :style="mainStyle" v-show="show.main !== false">
                    <slot name="main"></slot>
                </el-main>
                <el-footer v-if="$slots.footer" :style="footerStyle" v-show="show.footer !== false">
                    <slot name="footer"></slot>
                </el-footer>
            </el-container>
        </template>
    </el-container>
</template>

<script>
    export default {
        name: "Container",
        props:{
            type:{
                type: String,
                default: 'top-header' // left-aside
            },
            headerStyle:{},
            footerStyle:{},
            mainStyle:{},
            asideStyle:{},
            /*
            {
                header: false,
                footer: false,
                main: false,
                aside: false,
            }
            */
            show:{
                type: Object,
                default: function () {
                    return {}
                }
            }
        }
    }
</script>

<style scoped>

</style>