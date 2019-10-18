import { registerMicroApps} from 'qiankun';
import {genActiveRule, render} from "common-util/src/components/mfe";

export default function registApps() {
    registerMicroApps(
        [
            {
                name: 'common-nav',
                entry: '//localhost:9001/common-nav',
                render: (renderProps) => {render(renderProps,window.mfe_render_key + 'common')},
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