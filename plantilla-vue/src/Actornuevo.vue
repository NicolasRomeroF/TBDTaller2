<template>
  <div class="center">
    <h2>{{title}}</h2>
    <form>
      <div class="form-group">
        <label for="">Nombre</label>
        <input type="text" v-model="firstName" class="form-control" id="" aria-describedby="emailHelp" placeholder="">
      </div>
      <div class="form-group">
        <label for="">Apellido</label>
        <input type="text" v-model="lastName" class="form-control" id="" placeholder="">
      </div>
      <button type="submit" v-on:click.prevent="post" class="btn btn-primary">Guardar</button>
    </form>
  </div>
</template>
<script>
export default {
  data(){
    return{
      title:'Agregar Actor',
      firstName: '',
      lastName: '',
      lastUpdate: '1139988873000'
    }
  },

  methods:{
    post:function(){
      this.$http.post('http://localhost:8081/actors', {
        firstName: this.firstName,
        lastName: this.lastName,
        lastUpdate: this.lastUpdate
      }).then(function(data){
        console.log(data);
      })
    }
  }

}
</script>
