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
            <el-input style="width: 300px" suffix-icon="el-icon-search" placeholder="please enter content here"></el-input>
            <el-button style="width: 100px; margin-left: 10px" class="ml-5" type="primary">search</el-button>
          </div>
          <div>
            <el-button type="primary" class="el-icon-circle-plus-outline">Add</el-button>
            <el-button type="danger" class="el-icon-remove-outline">Delete</el-button>
            <el-button type="primary" class="el-icon-bottom">Import</el-button>
            <el-button type="primary" class="el-icon-top">Export</el-button>
          </div>
          <el-table :data="tableData">
            <el-table-column prop="date" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
            <el-table-column>
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
                :current-page="currentPage4"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="5"
                layout="total, sizes, prev, pager, next, jumper"
                :total="40">
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
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      msg:'hello',
      tableData: Array(10).fill(item),
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