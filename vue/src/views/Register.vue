<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>Register</b></div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input placeholder="please enter your username" size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="please enter your password" size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input placeholder="please confirm your password" size="medium" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.confirmPassword"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" autocomplete="off" @click="register">register</el-button>
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/login')">back to log in</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      user: {},
      rules: {
        username: [
          {required: true, message: 'please enter username', trigger: 'blur'},
          {min: 3, max: 10, message: 'length is 3 to 10 characters', trigger: 'blur'}
        ],
        password: [
          {required: true, message: 'please enter password', trigger: 'blur'},
          {min: 3, max: 20, message: 'length is 3 to 10 characters', trigger: 'blur'}
        ],
        confirmPassword: [
          {required: true, message: 'please enter password', trigger: 'blur'},
          {min: 3, max: 20, message: 'length is 3 to 10 characters', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    register() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          if (this.user.password !== this.user.confirmPassword) {
            this.$message.error("the passwords are not same")
            return false
          }
          this.request.post("/user/register", this.user).then(res => {
            if (res.code === '200') {
              this.$router.push("/")
            } else {
              this.$message.error(res.msg)
            }
          })
        } else {
          return false
        }
      });
    }
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
  overflow: hidden;
}
</style>