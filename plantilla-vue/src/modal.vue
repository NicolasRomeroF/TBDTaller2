<template>
    <div id="modalID" class="modal is-active">
        <div class="modal-background"></div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">{{titulo}}</p>
                <button class="delete" aria-label="close"></button>
            </header>
            <section class="modal-card-body">
                {{ texto }}
            </section>
            <footer class="modal-card-foot">
                <button class="button" @click="$emit('close')"> {{ button_ok}}</button>
            </footer>
        </div>
    </div>
</template>
<script>
export default {
  props: {
    titulo: String,
    texto: String,
  },
  data(){
    return{
      button_ok: 'Ok',
    }
  },
  methods:{
  }

}
</script>
