<template>
  <div>
    <el-container style="height: 100%; border: 1px solid #eee">
      <el-aside width="230px" style="height: 100vh" >
        <Aside></Aside>
      </el-aside>
      <el-container>
        <el-header style=" text-align: right;font-size: 12px;">
          <Header ></Header>
        </el-header>
        <el-main>
        <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'Manage',
  components: {
    Header,
    Aside
  },
  data() {
    return {
    }
  },
  methods: {
  }
}
</script>

<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>