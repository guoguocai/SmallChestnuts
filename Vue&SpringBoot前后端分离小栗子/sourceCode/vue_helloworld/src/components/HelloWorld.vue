<template>
  <div>
    <button @click="queryAllEmployees">查询所有员工</button>
    <button @click="addEmployee">添加员工</button>
    <button @click="deleteEmployee">删除员工</button>
    <button @click="updateEmployee">更新员工信息</button>
    <br><br>
    <button @click="deleteDept">删除部门</button>
    <button @click="addDept">添加部门</button>
    <button @click="updateDept">修改部门</button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  methods: {
    updateEmployee () {
      this.$axios({
        url: '/api/emp/1',
        method: 'PUT',
        data: "{\n" +
          "\t\"lastName\": \"Blessed\",\n" +
          "\t\"email\": \"blessed@163.com\",\n" +
          "\t\"phoneNumber\": \"12365478985\",\n" +
          "\t\"birth\": \"1996-03-29\",\n" +
          "\t\"createTime\": \"2018-09-24T21:52:52\",\n" +
          "\t\"department\": \"http://localhost:8080/dept/3\"\n" +
          "}",
        headers: {'Content-Type': 'application/json'}
      }).then(res => {console.log(res)}).catch(err => {console.log(err)})
    },
    deleteEmployee () {
      this.$axios({
        url: '/api/emp/3',
        method: 'DELETE'
      }).then(res => {console.log(res)}).catch(err => {console.log(err)})
    },
    updateDept () {
      this.$axios({
        url: '/api/dept/13',
        method: 'PUT',
        data: "{\"departmentName\": \"前台部门\"}",
        headers: {'Content-Type': 'application/json'}
      }).then(
        res => {console.log(res)}
      ).catch(err => {console.log(err)})
    },
    deleteDept () {
      this.$axios({
        url: '/api/dept/11',
        method: 'DELETE'
      }).then(
        res => {
          console.log(res)
        }
      ).catch(
        err => {
          alert('失败')
        }
      )
    },
    addEmployee () {
      alert('进入')
      this.$axios({
        url: '/api/emp',
        method: 'POST',
        data: "{\"lastName\": \"Jack\", \"email\": \"jack@163.com\", \"phoneNumber\": \"25136587541\", \"birth\": \"1996-07-12\", \"createTime\": \"2019-09-24T12:00:00\", \"department\": \"/api/dept/4\"}",
        headers: {'Content-Type': 'application/json'}
      }).then(
        res => {
          alert('成功')
          console.log(res)
        }
      ).catch(
        err => {
          alert('失败')
          console.log(err)
        }
      )
      alert('结束')
    },
    queryAllEmployees () {
      var url = '/api/emp'
      this.$axios.get(url).then(
        res => {
          console.log(res)
        }
      ).catch(
        err => {
          console.log(err)
        }
      )
    },
    addDept () {
      this.$axios({
        url: '/api/dept',
        method: 'post',
        data: "{\"departmentName\": \"前台接待部门\"}",
        headers: {'Content-Type': 'application/json'}
      }).then(
        res => {
          console.log(res)
        }
      ).catch(
        err => {
          console.log(err)
        }
      )
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
