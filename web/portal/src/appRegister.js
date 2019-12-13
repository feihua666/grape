import { registerMicroApps} from 'qiankun';
import {genActiveRule, render} from "common-util/src/components/mfe";

export default function registApps() {
    registerMicroApps(
        [
            // 导航项目
            {
                name: 'common-nav',// 应用的标识，自定义
                entry: '//localhost:9001/common-nav',// 子应用（导航项目）的首页地址，自动提取导致的子应用挂载函数，这个common-nav是随便写的好像不无关，暂时没弄明白
                render: (renderProps) => {render(renderProps,'renderKey-portalRenderMfe')},// renderKey-portalRenderMfe定义在portal项目Mfe.vue组件中
                activeRule: () => true  // 导航项目必须一直渲染不能卸载，否则导致其它子项目挂载不上
            }
        ],
        {
            beforeLoad: [app => {
            }],
            beforeMount: [app => {
            }],
            afterMount: [app => {
            }],
            afterUnmount: [app => {
                if (app.props.removeDomAfterUnmount) {
                    app.props.removeDomAfterUnmount()
                }
            }]
        }
    )
}