<template>
    <div>
        <el-popover
                ref="selectPopover"
                placement="right"
                trigger="click">
            <el-scrollbar wrapStyle="max-height:500px;">
                <div>
                    <div>element</div>
                    <ul class="icon-list">
                        <li  v-for="(item ,index) in elementIcons" :key="index" @click="elementClick(item)" :class="item === localValue ? 'selected' : ''"><span><i :class="item"></i><span class="icon-name">{{item}}</span></span></li>
                    </ul>
                    <template  v-if="bootstrap">
                        <div>bootstrap</div>
                        <ul class="bs-glyphicons-list">
                            <li v-for="(item ,index) in bootstrapIcons" :key="index" @click="bootstrapClick(item)" :class="item === localValue ? 'selected' : ''"> <span :class="item" aria-hidden="true" style="top:0;"></span> <span class="glyphicon-class">{{item}}</span> </li>
                        </ul>
                    </template>

                </div>
            </el-scrollbar>
        </el-popover>
        <el-input
                v-model="localValue"
                v-on:focus="handleFocus($event)"
                v-on:handleBlur="handleBlur($event)"
                v-on:change="emitChange"
                v-on:input="emitInput"
                :placeholder="placeholder"
                :disabled="disabled"
                :readonly="readonly"
                clearable>
            <i slot="prepend" :class="localValue"></i>
            <el-button slot="append"
                       icon="el-icon-search"
                       :disabled="disabled || readonly"
                       v-popover:selectPopover></el-button>
        </el-input>
    </div>
</template>

<script>
  export default {
    name: 'InputSelectIcon',
    props: {
      value: {
          required: true
      },
        placeholder:{
          type: String
        },
        bootstrap:{
          type: Boolean,
            default: false
        },
        disabled:{
          type:Boolean,
            default:false
        },
        readonly:{
            type:Boolean,
            default:false
        }
    },
    data () {
      return {
        localValue: this.value,
        elementIcons: [
          'el-icon-info',
          'el-icon-error',
          'el-icon-success',
          'el-icon-warning',
          'el-icon-question',
          'el-icon-back',
          'el-icon-arrow-left',
          'el-icon-arrow-down',
          'el-icon-arrow-right',
          'el-icon-arrow-up',
          'el-icon-caret-left',
          'el-icon-caret-bottom',
          'el-icon-caret-top',
          'el-icon-caret-right',
          'el-icon-d-arrow-left',
          'el-icon-d-arrow-right',
          'el-icon-minus',
          'el-icon-plus',
          'el-icon-remove',
          'el-icon-circle-plus',
          'el-icon-remove-outline',
          'el-icon-circle-plus-outline',
          'el-icon-close',
          'el-icon-check',
          'el-icon-circle-close',
          'el-icon-circle-check',
          'el-icon-circle-close-outline',
          'el-icon-circle-check-outline',
          'el-icon-zoom-out',
          'el-icon-zoom-in',
          'el-icon-d-caret',
          'el-icon-sort',
          'el-icon-sort-down',
          'el-icon-sort-up',
          'el-icon-tickets',
          'el-icon-document',
          'el-icon-goods',
          'el-icon-sold-out',
          'el-icon-news',
          'el-icon-message',
          'el-icon-date',
          'el-icon-printer',
          'el-icon-time',
          'el-icon-bell',
          'el-icon-mobile-phone',
          'el-icon-service',
          'el-icon-view',
          'el-icon-menu',
          'el-icon-more',
          'el-icon-more-outline',
          'el-icon-star-on',
          'el-icon-star-off',
          'el-icon-location',
          'el-icon-location-outline',
          'el-icon-phone',
          'el-icon-phone-outline',
          'el-icon-picture',
          'el-icon-picture-outline',
          'el-icon-delete',
          'el-icon-search',
          'el-icon-edit',
          'el-icon-edit-outline',
          'el-icon-rank',
          'el-icon-refresh',
          'el-icon-share',
          'el-icon-setting',
          'el-icon-upload',
          'el-icon-upload2',
          'el-icon-download',
          'el-icon-loading'
        ],
        bootstrapIcons: [
          'glyphicon glyphicon-asterisk',
          'glyphicon glyphicon-plus',
          'glyphicon glyphicon-euro',
          'glyphicon glyphicon-eur',
          'glyphicon glyphicon-minus',
          'glyphicon glyphicon-cloud',
          'glyphicon glyphicon-envelope',
          'glyphicon glyphicon-pencil',
          'glyphicon glyphicon-glass',
          'glyphicon glyphicon-music',
          'glyphicon glyphicon-search',
          'glyphicon glyphicon-heart',
          'glyphicon glyphicon-star',
          'glyphicon glyphicon-star-empty',
          'glyphicon glyphicon-user',
          'glyphicon glyphicon-film',
          'glyphicon glyphicon-th-large',
          'glyphicon glyphicon-th',
          'glyphicon glyphicon-th-list',
          'glyphicon glyphicon-ok',
          'glyphicon glyphicon-remove',
          'glyphicon glyphicon-zoom-in',
          'glyphicon glyphicon-zoom-out',
          'glyphicon glyphicon-off',
          'glyphicon glyphicon-signal',
          'glyphicon glyphicon-cog',
          'glyphicon glyphicon-trash',
          'glyphicon glyphicon-home',
          'glyphicon glyphicon-file',
          'glyphicon glyphicon-time',
          'glyphicon glyphicon-road',
          'glyphicon glyphicon-download-alt',
          'glyphicon glyphicon-download',
          'glyphicon glyphicon-upload',
          'glyphicon glyphicon-inbox',
          'glyphicon glyphicon-play-circle',
          'glyphicon glyphicon-repeat',
          'glyphicon glyphicon-refresh',
          'glyphicon glyphicon-list-alt',
          'glyphicon glyphicon-lock',
          'glyphicon glyphicon-flag',
          'glyphicon glyphicon-headphones',
          'glyphicon glyphicon-volume-off',
          'glyphicon glyphicon-volume-down',
          'glyphicon glyphicon-volume-up',
          'glyphicon glyphicon-qrcode',
          'glyphicon glyphicon-barcode',
          'glyphicon glyphicon-tag',
          'glyphicon glyphicon-tags',
          'glyphicon glyphicon-book',
          'glyphicon glyphicon-bookmark',
          'glyphicon glyphicon-print',
          'glyphicon glyphicon-camera',
          'glyphicon glyphicon-font',
          'glyphicon glyphicon-bold',
          'glyphicon glyphicon-italic',
          'glyphicon glyphicon-text-height',
          'glyphicon glyphicon-text-width',
          'glyphicon glyphicon-align-left',
          'glyphicon glyphicon-align-center',
          'glyphicon glyphicon-align-right',
          'glyphicon glyphicon-align-justify',
          'glyphicon glyphicon-list',
          'glyphicon glyphicon-indent-left',
          'glyphicon glyphicon-indent-right',
          'glyphicon glyphicon-facetime-video',
          'glyphicon glyphicon-picture',
          'glyphicon glyphicon-map-marker',
          'glyphicon glyphicon-adjust',
          'glyphicon glyphicon-tint',
          'glyphicon glyphicon-edit',
          'glyphicon glyphicon-share',
          'glyphicon glyphicon-check',
          'glyphicon glyphicon-move',
          'glyphicon glyphicon-step-backward',
          'glyphicon glyphicon-fast-backward',
          'glyphicon glyphicon-backward',
          'glyphicon glyphicon-play',
          'glyphicon glyphicon-pause',
          'glyphicon glyphicon-stop',
          'glyphicon glyphicon-forward',
          'glyphicon glyphicon-fast-forward',
          'glyphicon glyphicon-step-forward',
          'glyphicon glyphicon-eject',
          'glyphicon glyphicon-chevron-left',
          'glyphicon glyphicon-chevron-right',
          'glyphicon glyphicon-plus-sign',
          'glyphicon glyphicon-minus-sign',
          'glyphicon glyphicon-remove-sign',
          'glyphicon glyphicon-ok-sign',
          'glyphicon glyphicon-question-sign',
          'glyphicon glyphicon-info-sign',
          'glyphicon glyphicon-screenshot',
          'glyphicon glyphicon-remove-circle',
          'glyphicon glyphicon-ok-circle',
          'glyphicon glyphicon-ban-circle',
          'glyphicon glyphicon-arrow-left',
          'glyphicon glyphicon-arrow-right',
          'glyphicon glyphicon-arrow-up',
          'glyphicon glyphicon-arrow-down',
          'glyphicon glyphicon-share-alt',
          'glyphicon glyphicon-resize-full',
          'glyphicon glyphicon-resize-small',
          'glyphicon glyphicon-exclamation-sign',
          'glyphicon glyphicon-gift',
          'glyphicon glyphicon-leaf',
          'glyphicon glyphicon-fire',
          'glyphicon glyphicon-eye-open',
          'glyphicon glyphicon-eye-close',
          'glyphicon glyphicon-warning-sign',
          'glyphicon glyphicon-plane',
          'glyphicon glyphicon-calendar',
          'glyphicon glyphicon-random',
          'glyphicon glyphicon-comment',
          'glyphicon glyphicon-magnet',
          'glyphicon glyphicon-chevron-up',
          'glyphicon glyphicon-chevron-down',
          'glyphicon glyphicon-retweet',
          'glyphicon glyphicon-shopping-cart',
          'glyphicon glyphicon-folder-close',
          'glyphicon glyphicon-folder-open',
          'glyphicon glyphicon-resize-vertical',
          'glyphicon glyphicon-resize-horizontal',
          'glyphicon glyphicon-hdd',
          'glyphicon glyphicon-bullhorn',
          'glyphicon glyphicon-bell',
          'glyphicon glyphicon-certificate',
          'glyphicon glyphicon-thumbs-up',
          'glyphicon glyphicon-thumbs-down',
          'glyphicon glyphicon-hand-right',
          'glyphicon glyphicon-hand-left',
          'glyphicon glyphicon-hand-up',
          'glyphicon glyphicon-hand-down',
          'glyphicon glyphicon-circle-arrow-right',
          'glyphicon glyphicon-circle-arrow-left',
          'glyphicon glyphicon-circle-arrow-up',
          'glyphicon glyphicon-circle-arrow-down',
          'glyphicon glyphicon-globe',
          'glyphicon glyphicon-wrench',
          'glyphicon glyphicon-tasks',
          'glyphicon glyphicon-filter',
          'glyphicon glyphicon-briefcase',
          'glyphicon glyphicon-fullscreen',
          'glyphicon glyphicon-dashboard',
          'glyphicon glyphicon-paperclip',
          'glyphicon glyphicon-heart-empty',
          'glyphicon glyphicon-link',
          'glyphicon glyphicon-phone',
          'glyphicon glyphicon-pushpin',
          'glyphicon glyphicon-usd',
          'glyphicon glyphicon-gbp',
          'glyphicon glyphicon-sort',
          'glyphicon glyphicon-sort-by-alphabet',
          'glyphicon glyphicon-sort-by-alphabet-alt',
          'glyphicon glyphicon-sort-by-order',
          'glyphicon glyphicon-sort-by-order-alt',
          'glyphicon glyphicon-sort-by-attributes',
          'glyphicon glyphicon-sort-by-attributes-alt',
          'glyphicon glyphicon-unchecked',
          'glyphicon glyphicon-expand',
          'glyphicon glyphicon-collapse-down',
          'glyphicon glyphicon-collapse-up',
          'glyphicon glyphicon-log-in',
          'glyphicon glyphicon-flash',
          'glyphicon glyphicon-log-out',
          'glyphicon glyphicon-new-window',
          'glyphicon glyphicon-record',
          'glyphicon glyphicon-save',
          'glyphicon glyphicon-open',
          'glyphicon glyphicon-saved',
          'glyphicon glyphicon-import',
          'glyphicon glyphicon-export',
          'glyphicon glyphicon-send',
          'glyphicon glyphicon-floppy-disk',
          'glyphicon glyphicon-floppy-saved',
          'glyphicon glyphicon-floppy-remove',
          'glyphicon glyphicon-floppy-save',
          'glyphicon glyphicon-floppy-open',
          'glyphicon glyphicon-credit-card',
          'glyphicon glyphicon-transfer',
          'glyphicon glyphicon-cutlery',
          'glyphicon glyphicon-header',
          'glyphicon glyphicon-compressed',
          'glyphicon glyphicon-earphone',
          'glyphicon glyphicon-phone-alt',
          'glyphicon glyphicon-tower',
          'glyphicon glyphicon-stats',
          'glyphicon glyphicon-sd-video',
          'glyphicon glyphicon-hd-video',
          'glyphicon glyphicon-subtitles',
          'glyphicon glyphicon-sound-stereo',
          'glyphicon glyphicon-sound-dolby',
          'glyphicon glyphicon-sound-5-1',
          'glyphicon glyphicon-sound-6-1',
          'glyphicon glyphicon-sound-7-1',
          'glyphicon glyphicon-copyright-mark',
          'glyphicon glyphicon-registration-mark',
          'glyphicon glyphicon-cloud-download',
          'glyphicon glyphicon-cloud-upload',
          'glyphicon glyphicon-tree-conifer',
          'glyphicon glyphicon-tree-deciduous',
          'glyphicon glyphicon-cd',
          'glyphicon glyphicon-save-file',
          'glyphicon glyphicon-open-file',
          'glyphicon glyphicon-level-up',
          'glyphicon glyphicon-copy',
          'glyphicon glyphicon-paste',
          'glyphicon glyphicon-alert',
          'glyphicon glyphicon-equalizer',
          'glyphicon glyphicon-king',
          'glyphicon glyphicon-queen',
          'glyphicon glyphicon-pawn',
          'glyphicon glyphicon-bishop',
          'glyphicon glyphicon-knight',
          'glyphicon glyphicon-baby-formula',
          'glyphicon glyphicon-tent',
          'glyphicon glyphicon-blackboard',
          'glyphicon glyphicon-bed',
          'glyphicon glyphicon-apple',
          'glyphicon glyphicon-erase',
          'glyphicon glyphicon-hourglass',
          'glyphicon glyphicon-lamp',
          'glyphicon glyphicon-duplicate',
          'glyphicon glyphicon-piggy-bank',
          'glyphicon glyphicon-scissors',
          'glyphicon glyphicon-bitcoin',
          'glyphicon glyphicon-btc',
          'glyphicon glyphicon-xbt',
          'glyphicon glyphicon-yen',
          'glyphicon glyphicon-jpy',
          'glyphicon glyphicon-ruble',
          'glyphicon glyphicon-rub',
          'glyphicon glyphicon-scale',
          'glyphicon glyphicon-ice-lolly',
          'glyphicon glyphicon-ice-lolly-tasted',
          'glyphicon glyphicon-education',
          'glyphicon glyphicon-option-horizontal',
          'glyphicon glyphicon-option-vertical',
          'glyphicon glyphicon-menu-hamburger',
          'glyphicon glyphicon-modal-window',
          'glyphicon glyphicon-oil',
          'glyphicon glyphicon-grain',
          'glyphicon glyphicon-sunglasses',
          'glyphicon glyphicon-text-size',
          'glyphicon glyphicon-text-color',
          'glyphicon glyphicon-text-background',
          'glyphicon glyphicon-object-align-top',
          'glyphicon glyphicon-object-align-bottom',
          'glyphicon glyphicon-object-align-horizontal',
          'glyphicon glyphicon-object-align-left',
          'glyphicon glyphicon-object-align-vertical',
          'glyphicon glyphicon-object-align-right',
          'glyphicon glyphicon-triangle-right',
          'glyphicon glyphicon-triangle-left',
          'glyphicon glyphicon-triangle-bottom',
          'glyphicon glyphicon-triangle-top',
          'glyphicon glyphicon-console',
          'glyphicon glyphicon-superscript',
          'glyphicon glyphicon-subscript',
          'glyphicon glyphicon-menu-left',
          'glyphicon glyphicon-menu-right',
          'glyphicon glyphicon-menu-down',
          'glyphicon glyphicon-menu-up'
        ]
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
            this.$emit('change', this.localValue)
        },
        emitInput (val) {
            this.$emit('input', this.localValue)
        },
      elementClick (item) {
        this.click(item)
      },
      bootstrapClick (item) {
        this.click(item)
      },
      click (item) {
        this.$emit('change', item)
          this.$emit('input', item)
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
ul>li{
  cursor: pointer;
    list-style:none;
}
ul>li:hover,.selected{
  background-color: gray;
}
</style>
