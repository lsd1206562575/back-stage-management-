<template>
  <div>
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
            <el-input style="width: 300px" suffix-icon="el-icon-search" placeholder="please enter username" v-model="username"></el-input>
            <el-button style="width: 100px; margin-left: 10px" class="ml-5" type="primary" @click="load">search</el-button>
            <el-button style="width: 100px; margin-left: 10px" type="warning" @click="reset">reset</el-button>

          </div>
          <div>
            <el-button type="primary" class="el-icon-circle-plus-outline" @click="handleAdd">Add</el-button>
            <el-button type="danger" class="el-icon-remove-outline" @click="delBatch">Delete</el-button>
            <el-button type="primary" class="el-icon-bottom">Import</el-button>
            <el-button type="primary" class="el-icon-top">Export</el-button>
          </div>
          <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="ID"></el-table-column>
            <el-table-column prop="username" label="username"></el-table-column>
            <el-table-column prop="nickname" label="nickname" ></el-table-column>
            <el-table-column prop="email" label="email"></el-table-column>
            <el-table-column prop="phone" label="phone"></el-table-column>
            <el-table-column prop="address" label="address"></el-table-column>
            <el-table-column label="Operation" width="200" align="center">
              <template slot-scope="scope">
                <el-button type="success" @click="handleEdit(scope.row)">Edit<i class="el-icon-edit"></i></el-button>
                <el-popconfirm
                    style="margin-left: 5px"
                    confirm-button-text='Yes'
                    cancel-button-text='No'
                    icon="el-icon-info"
                    icon-color="red"
                    title="Are you sure？"
                    @confirm="del(scope.row.id)"
                >
                  <el-button slot="reference" type="danger">Delete</el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>
          <div style="padding: 10px">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
          </div>
          <el-dialog title="user information" :visible.sync="dialogFormVisible">
            <el-form label-width="120px">
              <el-form-item label="username" >
                <el-input v-model="form.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="nickname" >
                <el-input v-model="form.nickname" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="email" >
                <el-input v-model="form.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="phone" >
                <el-input v-model="form.phone" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="address" >
                <el-input v-model="form.address" autocomplete="off"></el-input>
              </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">No</el-button>
              <el-button type="primary" @click="save">Yes</el-button>
            </div>
          </el-dialog>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src

import request from "../../utils/request";

export default {
  name: 'HomeView',
  data() {
    return {
      tableData: [],
      total:0,
      pageNum: 1,
      pageSize: 10,
      username: "",
      dialogFormVisible: false,
      form: {},
      multipleSelection: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/user/page",{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username
        }
      }).then(
          res =>{
            console.log(res)
            this.tableData = res.data
            this.total = res.total
          })
    },
    save(){
      request.post("/user", this.form).then(res => {
        if (res) {
          this.$message.success("success!")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.success("failed!")
          this.dialogFormVisible = false
        }
      })
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row){
      this.form = Object.assign({}, row)
      this.dialogFormVisible = true
    },
    del(id){
      request.delete("/user/" + id).then(res =>{
        if (res) {
          this.$message.success("success!")
          this.load()
        } else {
          this.$message.success("failed!")
        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      console.log(ids)
      request.post("/user/del/batch", ids).then(res =>{
        if (res) {
          this.$message.success("success!")
          this.load()
        } else {
          this.$message.success("failed!")
        }
      })
    },
    reset(){
      this.username = ""
      this.load()
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