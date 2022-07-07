<template>
  <el-card style="width: 700px">
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
      <el-form-item>
        <el-button type="primary" @click="save">Yes</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      form: {},
      user: localStorage.getItem("user")? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.request.get("/user/username/" + this.user.username).then(res => {
      if (res.code === '200') {
        this.form = res.data
      }
    })
  },
  methods: {
    save(){
      this.request.post("/user", this.form).then(res => {
        if (res.data) {
          this.$message.success("success!")
        } else {
          this.$message.success("failed!")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>