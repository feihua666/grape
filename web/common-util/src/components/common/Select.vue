<template>
    <el-select class="g-width100"
               ref="elSelect"
               filterable
               :disabled="disabled"
               :readonly="readonly"
            v-model="localValue"
               :placeholder="placeholder"
            v-on:focus="handleFocus($event)"
            v-on:blur="handleBlur($event)"
            v-on:change="emitChange"
            v-on:input="emitInput"
    >
        <el-option label="请选择" :value="empty" v-if="showEmpty"></el-option>
        <el-option
                v-for="(item,index) in data"
                :label="item[props.label]"
                :disabled="item.isDisable || false"
                :value="item[props.value]"
                :key="index"
                ></el-option>
    </el-select>
</template>

<script>
    export default {
        name: 'Select',
        props: {
            value: {
                required: true
            },
            placeholder:{
                type: String,
                default: '请选择'
            },
            // 是否显示请选择
            showEmpty: {
                type: Boolean,
                default: true
            },
            // 字典组编码
            url: {
                type: String,
                required: true
            },
            // 是否禁用
        disabled:{
            type:Boolean,
            default:false
        },
        readonly:{
            type:Boolean,
            default:false
        },
            props:{
                type: Object,
                default:function () {
                    return {
                        value: 'id',
                        label: 'name'
                    }
                }
            }
        },
        data () {
            return {
                localValue: this.value,
                empty: null,
                data: []
            }
        },
        mounted () {
            this.getDataByUrl(this.url)
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
            getDataByUrl (url) {
                this.axios.get(url).then(res => {
                    this.data = res.data.data
                    this.$nextTick(()=>{
                        this.$refs.elSelect.setSelected()
                    })
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
