<template>
  <div>
    <div style="padding: 10px">
      <el-input style="width: 300px" suffix-icon="el-icon-search" placeholder="please enter username" v-model="username"></el-input>
      <el-button style="width: 100px; margin-left: 10px" class="ml-5" type="primary" @click="load">search</el-button>
      <el-button style="width: 100px; margin-left: 10px" type="warning" @click="reset">reset</el-button>

    </div>
    <div>
      <el-button type="primary" class="el-icon-circle-plus-outline" @click="handleAdd">Add</el-button>
      <el-button type="danger" class="el-icon-remove-outline" @click="delBatch">Delete</el-button>
      <el-upload action="http://localhost:9090/user/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="el-icon-bottom" style="margin-left: 5px">Import</el-button>
      </el-upload>
      <el-button type="primary" class="el-icon-top" @click="exp" style="margin-left: 5px">Export</el-button>
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
  </div>
</template>

<script>
export default {
  name: "User",
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
      this.request.get("/user/page",{
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
      this.request.post("/user", this.form).then(res => {
        if (res.data) {
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
      this.request.delete("/user/" + id).then(res =>{
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
      this.request.post("/user/del/batch", ids).then(res =>{
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
    },
    exp() {
      window.open("http://localhost:9090/user/export")
    },
    handleExcelImportSuccess() {
      this.$message.success("success!")
      this.load()
    }
  }
}
</script>

<style scoped>

</style>