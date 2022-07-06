<template>
    <el-dropdown style="width: 80px">
      <i class="el-icon-setting" style=""></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>个人信息</el-dropdown-item>
        <el-dropdown-item>
          <router-link to="/login" style="text-decoration: none">exit</router-link>
        </el-dropdown-item>
      </el-dropdown-menu>
      <span style="margin-left: 5px">王小虎</span>
    </el-dropdown>
</template>

<script>
export default {
  name: "Header"
}
</script>

<style scoped>

</style>