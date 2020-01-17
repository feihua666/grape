<template>
    <el-form :model="form" status-icon :rules="rules" :style="formStyle"  ref="form" label-width="100px">
        <el-form-item label="帐号" prop="username">
            <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm()" :loading="logining">登录</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
    import StorageTools from '../../tools/StorageTools'
    export default {
        name: "Login",
        props: {
            formStyle:String
        },
        data (){
            return {
                logining: false,
                form:{
                    username: null,
                    password: null
                },
                rules: {
                    username: [
                        {required: true, message: '帐号不能为空'}
                    ],
                    password: [
                        {required: true, message: '密码不能为空'}
                    ]
                }
            }
        },
        methods: {
            submitForm() {
                let self = this
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        self.logining = true
                        self.axios.post('/user/login',self.form,{baseURL:self.$baseURL.auth}).then(res => {
                            self.$message.success('登录成功')
                            StorageTools.set('token',res.data.data.token)
                            self.$emit('success')
                        }).catch(error => {
                            if (error.response) {
                                self.$message.error(error.response.data.msg)
                            }else{
                                self.$message.error('登录失败，未知原因')
                            }
                        }).finally(()=>{
                            self.logining = false
                        })
                    } else {
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>