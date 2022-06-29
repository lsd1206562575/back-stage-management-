<template>
  <div style="height: 100%">
    <el-container style="height: 100%; border: 1px solid #eee">
      <el-aside width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); height: 100%">
        <el-menu :default-openeds="['1', '3']" :collapse-transition="false">
          <div style="line-height: 60px; text-align: center" >
            <b>后台管理系统</b>
          </div>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>导航一</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-message"></i>导航二</template>
            <el-menu-item-group>
              <template slot="title">分组二</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px;">
          <el-dropdown style="width: 20px">
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>王小虎</span>
        </el-header>

        <el-main>
          <div style="padding: 10px">
            <el-input style="width: 300px" suffix-icon="el-icon-search" placeholder="please enter username"></el-input>
            <el-button style="width: 100px; margin-left: 10px" class="ml-5" type="primary">search</el-button>
          </div>
          <div>
            <el-button type="primary" class="el-icon-circle-plus-outline">Add</el-button>
            <el-button type="danger" class="el-icon-remove-outline">Delete</el-button>
            <el-button type="primary" class="el-icon-bottom">Import</el-button>
            <el-button type="primary" class="el-icon-top">Export</el-button>
          </div>
          <el-table :data="tableData" border stripe>
            <el-table-column prop="id" label="ID" >
            </el-table-column>
            <el-table-column prop="username" label="用户名">
            </el-table-column>
            <el-table-column prop="nickname" label="昵称" >
            </el-table-column>
            <el-table-column prop="email" label="邮箱">
            </el-table-column>
            <el-table-column prop="phone" label="电话">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
            <el-table-column label="Operation" width="200" align="center">
              <template slot-scope="scope">
                <el-button type="success">Edit<i class="el-icon-edit"></i></el-button>
                <el-button type="danger">Delete<i class="el-icon-remove-outline"></i></el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="padding: 10px">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[2, 5, 10, 15]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'HomeView',
  data() {
    return {
      tableData: [],
      total:0,
      pageNum: 1,
      pageSize: 2
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      fetch("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize)
          .then(res => res.json()).then(res =>{
        console.log(res)
        this.tableData = res.data
        this.total = res.total
      })
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load()
    }
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