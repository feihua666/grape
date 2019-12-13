import { registerMicroApps} from 'qiankun';
import {genActiveRule, render} from "common-util/src/components/mfe";

export default function registApps() {


    registerMicroApps(
        [
            {
                name: 'base',
                entry: '//localhost:9002/base',//，这个base是随便写的好像不无关，暂时没弄明白
                render: (renderProps) => {render(renderProps,'renderKey-commonNavRenderMfe')},// renderKey-commonNavRenderMfe定义在common-nav项目views/nav/Nav.vue组件中
                activeRule: genActiveRule(window.mfe_nav_router_base_path + '/base'),
                props: {
                    // 自定义方法在应用卸载的时候将dom渲染为空，因为vue在destroy的时候不会清除dom，如果子项目不是vue请考虑具体情况
                    removeDomAfterUnmount: () => {render({appContent: null,loading: false})}
                }
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