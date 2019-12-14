<template>
    <el-select class="g-width100"
               ref="elSelect"
               filterable
               remote
               :disabled="disabled"
               :readonly="readonly"
                v-model="localValue"
               :placeholder="placeholder"
               :remote-method="getData"
               :loading="loading"
            v-on:focus="handleFocus($event)"
            v-on:blur="handleBlur($event)"
            v-on:change="emitChange"
            v-on:input="emitInput"
    >
        <el-option
                v-for="(item) in data"
                :label="item[props.label]"
                :disabled="item.isDisable"
                :value="item[props.value]"
                :key="item[props.value]"
                ></el-option>
    </el-select>
</template>

<script>
    export default {
        name: 'SelectRemote',
        props: {
            value: {
                required: true
            },
            // 初始显示的label值
            label:{
                type: String
            },
            placeholder:{
                type: String,
                default: '请输入关键字'
            },
            // 数据地址
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
            },
            queryProp:{
                type: String,
                default: 'name'
            }
        },
        data () {
            return {
                localValue: this.value,
                loading: false,
                data: []
            }
        },
        mounted () {
            if(this.label){
                let dataItem = {}
                dataItem[this.props.value] = this.value
                dataItem[this.props.label] = this.label
                this.data.push(dataItem)
            }
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
            // 加载
            getData (query) {
                if(!query){
                    return
                }
                this.loading = true
                let data = {}
                data[this.queryProp] = query
                this.axios.get(this.url,{
                    params:data
                }).then(res => {
                    this.data = res.data.data
                }).catch(()=>{
                    this.data = []
                }).finally(()=>{
                    this.loading = false
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
