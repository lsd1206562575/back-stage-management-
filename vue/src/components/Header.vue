<template>
  <div style="line-height: 60px">
    <el-dropdown style="width: 100px">
      <i class="el-icon-setting" style=""></i>
      <el-dropdown-menu slot="dropdown" style="width: 120px; text-align: center">
        <el-dropdown-item style="font-size: 14px">
          <router-link to="/person">information</router-link>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px">
          <span style="text-decoration: none" @click="logout">exit</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <span style="margin-left: 10px">{{user.nickname}}</span>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      form: {},
      user: localStorage.getItem("user")? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods: {
    logout() {
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("Log out success!")
    }
  }
}
</script>

<style scoped>

</style>