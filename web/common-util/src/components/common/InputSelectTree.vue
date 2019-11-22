<template>
  <div>
    <el-popover
      ref="selectPopover"
      placement="right"
      v-model="popShow"
      trigger="click">
      <el-scrollbar wrapStyle="max-height:500px;">
        <tree :label="treeLabel" :data-url="this.treeDataUrl" v-on:nodeClick="treeNodeClick" v-on:nodeClickExt="treeNodeClickExt" ></tree>
      </el-scrollbar>
    </el-popover>
    <el-input
            v-model="localLabelText"
            v-on:focus="handleFocus($event)"
            v-on:handleBlur="handleBlur($event)"
            v-on:change="emitChange"
            v-on:input="emitInput"
            :readonly="true"
            :placeholder="placeholder"
            :disabled="disabled"
            clearable>
      <el-button slot="append"
                 icon="el-icon-search"
                 :disabled="disabled"
                 v-popover:selectPopover></el-button>
      <i v-if="localValue && !disabled" slot="suffix" class="el-input__icon el-icon-circle-close el-input__clear" @click="clear"></i>
    </el-input>
  </div>
</template>

<script>
  import Tree from './Tree.vue'
  export default {
    components: {Tree},
    name: 'InputSelectTree',
    props: {
      value: {
          required:true
      },
      labelText: {
          type: String
      },
        placeholder:{
          type: String,
            default: '请选择'
        },
        treeLabel:{
          type: String,
            default: 'name'
        },
        treeDataUrl:{
          type: String,
            required: true
        },
        disabled:{
            type:Boolean,
            default:false
        }
    },
    data () {
      return {
          popShow:false,
        localValue: this.value,
        localLabelText: this.labelText
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
        this.localLabelText = val
        this.$emit('change', this.localValue)
      },
      emitInput (val) {
        this.localLabelText = val
        this.$emit('input', this.localValue)
      },
      treeNodeClick (data) {
      },
        treeNodeClickExt (data) {
          this.popShow = !this.popShow
            this.localValue = data.key.id
            this.localLabelText = data.key.label
            this.$emit('change', this.localValue)
            this.$emit('input', this.localValue)
        },
      clear () {
          this.localValue = null
          this.localLabelText = null
          this.$emit('change', null)
          this.$emit('input', null)
      }
    },
    watch: {
      value (val) {
        this.localValue = val
          if(!val){
              this.localLabelText = val
          }
      },
        labelText(val){
          this.localLabelText = val
        }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
