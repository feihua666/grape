<template>
    <el-input
            v-model="localValue"
            v-on:focus="handleFocus($event)"
            v-on:handleBlur="handleBlur($event)"
            v-on:change="emitChange"
            v-on:input="emitInput"
            :placeholder="placeholder"
            :disabled="disabled"
            :readonly="readonly"
            :clearable="clearable">
      <el-button slot="append"
                 :icon="bIcon"
                 @click="bClick"
                 :disabled="disabled">{{bLabel}}</el-button>
    </el-input>
</template>

<script>
  export default {
    components: {},
    name: 'InputButton',
    props: {
      value: {
          required:true
      },
      placeholder:{
        type: String,
          default: '请选择'
      },
      disabled:{
          type:Boolean,
          default:false
      },
      readonly:{
          type:Boolean,
          default:false
      },
      bIcon:{
        type:String,
          default: 'el-icon-search'
      },
      bLabel:{
        type:String
      },
      clearable:{
        type:Boolean
      },
      bClick:{
        type: Function,
        require: true
      }
    },
    data () {
      return {
        localValue: this.value
      }
    },
    mounted () {
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
      }
    },
    watch: {
      value (val) {
        this.localValue = val
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
