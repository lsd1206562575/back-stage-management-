<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="please enter file name" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-button type="primary" @click="load" style="margin-left: 10px">search</el-button>
      <el-button type="warning" @click="reset">reset</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-upload :action="'http://' + serverIp + ':9090/file/upload'" :show-file-list="false"
                 :on-success="handleFileUploadSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">upload<i class="el-icon-top"></i></el-button>
      </el-upload>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='Yes'
          cancel-button-text='No'
          icon="el-icon-info"
          icon-color="red"
          title="Are you sure?"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference" style="margin-left: 10px">delete<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>
    <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="name" label="name"></el-table-column>
      <el-table-column prop="type" label="type" ></el-table-column>
      <el-table-column prop="size" label="size"></el-table-column>
      <el-table-column label="preview">
        <template slot-scope="scope">
          <el-button type="primary" @click="preview(scope.row.url)">preview</el-button>
        </template>
      </el-table-column>
      <el-table-column label="download">
        <template slot-scope="scope">
          <el-button type="primary" @click="download(scope.row.url)">download</el-button>
        </template>
      </el-table-column>
      <el-table-column label="enable">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc"
                     @change="changeEnable(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="Operation" width="200" align="center">
        <template slot-scope="scope">
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
  </div>
</template>

<script>
import {serverIp} from "../../public/config";

export default {
  name: "File",
  data() {
    return {
      serverIp: serverIp,
      tableData: [],
      name: '',
      multipleSelection: [],
      pageNum: 1,
      pageSize: 10,
      total: 0
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/file/page",{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name
        }
      }).then(
          res =>{
            console.log(res)
            this.tableData = res.data
            this.total = res.total
          })
    },
    changeEnable(row) {
      this.request.post("/file/update", row).then(res => {
        if (res.code === '200') {
          this.$message.success("success!")
        }
      })
    },
    del(id) {
      this.request.delete("/file/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("success!")
          this.load()
        } else {
          this.$message.error("failed!")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/file/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("success!")
          this.load()
        } else {
          this.$message.error("failed!")
        }
      })
    },
    reset() {
      this.name = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    handleFileUploadSuccess(res) {
      console.log(res)
      this.$message.success("success!")
      this.load()
    },
    download(url) {
      window.open(url)
    },
    preview(url) {
      window.open('https://file.keking.cn/onlinePreview?url=' + encodeURIComponent(window.btoa((url))))
    },
  }
}
</script>

<style scoped>

</style>