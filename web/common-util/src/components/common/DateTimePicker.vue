<template>
    <el-date-picker
            class="g-width100"
            v-model="localValue"
            :type="type"
            :picker-options="type.indexOf('range')>0? pickerRangeOptions: pickerOptions"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            align="left"
            v-on:focus="handleFocus($event)"
            v-on:blur="handleBlur($event)"
            v-on:change="emitChange"
            v-on:input="emitInput"
            :format="getFormat()"
            :value-format="getValueFormat()"
            :disabled="disabled"
    >
    </el-date-picker>
</template>

<script>
    import {isArray,itemHasProp} from '../../tools/ArrayTools.js'
    export default {
        props:{
            value: {
                required: true
            },
            //year/month/date/week/ datetime/datetimerange/daterange
            type:{
                type: String,
                default: 'datetime'
            },
            disabled:{
                type: Boolean
            },
            format:{
                type: String
            },
            valueFormat:{
                type: String
            }
        },
        data(){
            return {
                localValue:this.value,
                pickerOptions: {
                    shortcuts: [{
                        text: '今天',
                        onClick(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '昨天',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                },
                pickerRangeOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
            }
        },
        methods:{
            handleFocus (event) {
                this.$emit('focus', event)
            },
            handleBlur (event) {
                this.$emit('blur', event)
            },
            emitChange (val) {
                this.$emit('change', val)
                if(isArray(val)){
                    this.$emit('start', val[0])
                    this.$emit('end', val[1])
                }
            },
            emitInput (val) {
                this.$emit('input', val)
                if(isArray(val)){
                    this.$emit('start', val[0])
                    this.$emit('end', val[1])
                }
            },
            getFormat(){
                if(!this.format){
                    let defautFormat ={
                        year:'yyyy',
                        month:'MM',
                        date:'yyyy-MM-dd',
                        daterange:'yyyy-MM-dd',
                        week:'WW',
                        datetime:'yyyy-MM-dd HH:mm:ss',
                        datetimerange:'yyyy-MM-dd HH:mm:ss'
                    }
                    return defautFormat[this.type]
                }
                return this.format
            },
            getValueFormat(){
                if(!this.valueFormat){

                    let defautValueFormat ={
                        //year:'yyyy',
                        //month:'MM',
                        date:'timestamp',
                        daterange:'timestamp',
                        //week:'WW',
                        datetime:'timestamp',
                        datetimerange:'timestamp'
                    }
                    return defautValueFormat[this.type]
                }
                return this.valueFormat
            }
        },
        watch: {
            value(val){
                this.localValue = val
            }
        }

    }
</script>

<style scoped>

</style>