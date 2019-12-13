<template>
    <el-form :model="form" status-icon :rules="rules" ref="form" label-width="100px">

        <el-form-item label="原密码" prop="oldPassword">
            <el-input type="password" v-model="form.oldPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
            <el-input type="password" v-model="form.newPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码" prop="confirmNewPassword">
            <el-input type="password" v-model="form.confirmNewPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm()" :loading="submitLoading">修改</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
    export default {
        data (){

            let validConfirmNewPassword = (rule, value, callback) => {
                if (!value) {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.form.newPassword) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            }
            return {
                submitLoading: false,
                form:{
                    // 原密码
                    oldPassword: null,
                    // 新密码
                    newPassword: null,
                    confirmNewPassword: null,
                },
                rules: {
                    oldPassword: [
                        {required: true, message: '原密码不能为空'}
                    ],
                    newPassword: [
                        {required: true, message: '新密码不能为空'}
                    ],
                    confirmNewPassword: [
                        {validator:validConfirmNewPassword}
                    ]
                }
            }
        },
        methods: {
            submitForm() {
                let self = this
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        self.submitLoading = true
                        self.axios.put('/userpwd/update',self.form).then(res => {
                            self.$emit('success')
                        }).catch(error => {
                            if (error.response) {
                                self.$message.error(error.response.data.msg)
                            }else{
                                self.$message.error('登录失败，未知原因')
                            }
                        }).finally(()=>{
                            self.submitLoading = false
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