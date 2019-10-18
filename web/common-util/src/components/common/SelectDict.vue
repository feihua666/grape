<template>
    <el-select class="g-width100"
            :disabled="disabled"
            v-model="localValue"
            v-on:focus="handleFocus($event)"
            v-on:blur="handleBlur($event)"
            v-on:change="emitChange"
            v-on:input="emitInput">
        <el-option label="请选择" :value="empty" v-if="showEmpty"></el-option>
        <el-option
                v-for="item in dictItems"
                :label="item.name"
                :disabled="item.isDisable || false"
                :value="item.id"
                :key="item.id"
                ></el-option>
    </el-select>
</template>

<script>
    export default {
        name: 'SelectDict',
        props: {
            value: {
                required: true
            },
            // 是否显示请选择
            showEmpty: {
                type: Boolean,
                default: true
            },
            // 字典组编码
            groupCode: {
                type: String,
                required: true
            },
            // 是否禁用
            disabled: {
                type: Boolean,
                default: false
            }
        },
        data () {
            return {
                localValue: this.value,
                empty: null,
                dictItems: []
            }
        },
        mounted () {
            this.getDictItemByGroupCode(this.groupCode)
        },
        methods: {
            handleFocus (event) {
                this.$emit('focus', event)
            },
            handleBlur (event) {
                this.$emit('blur', event)
            },
            emitChange (val) {
                this.$emit('change', val)
            },
            emitInput (val) {
                this.$emit('input', val)
            },
            // 加载字典
            getDictItemByGroupCode (groupCode) {
                this.axios.get('/dict/items/' + groupCode).then(res => {
                    this.dictItems = res.data.data
                })
            }
        },
        watch: {
            value(val){
                this.localValue = val
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
