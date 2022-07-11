<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden;"
            router
  >
    <div style="height: 60px; line-height: 60px; text-align: center" >
      <b style="font-size: small">Management System</b>
    </div>
    <el-menu-item style="text-align: center" index="/">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span slot="title">MainPage</span>
      </template>
    </el-menu-item>
    <el-submenu index="2" style="text-align: left">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">System_Management</span>
      </template>
      <el-menu-item index="/user">
        <i class="el-icon-s-custom"></i>
        <span slot="title">User</span>
      </el-menu-item>
      <el-menu-item index="/role">
        <i class="el-icon-user"></i>
        <span slot="title">Role</span>
      </el-menu-item>
      <el-menu-item index="/file">
        <i class="el-icon-files"></i>
        <span slot="title">File</span>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>