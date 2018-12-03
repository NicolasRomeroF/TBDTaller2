<template>
    <div class="center">
        
        <h2>Filtrar juegos por su respectivo género</h2>

        <hr>

        <div style="float:left;">
            <label for="select">Seleccione un género:</label>
            <div class="styled-select slate">
                <select v-model="selected">
                    <option @click.prevent="seleccionarGenero" 
                            v-for="genero in generos"
                            v-bind:key="genero.id_genero"
                            v-bind:value="genero.id_genero"> {{ genero.nombre_genero }}  
                    </option>
                </select>
            </div>
        </div>

        <br>

        <ul v-if="selected != 0" class="list-group">         
            <!--Lista de juegos filtrados-->
            <li v-for="juego in juegos_filtrados" v-bind:key="juego.nombre_juego" class="list-group-item">
                {{ juego.nombre_juego }}
            </li>
        </ul>

    </div>
</template>

<script>
    export default {
        data(){
            return{
                selected: 0,
                buscado: "",
                generos: [
                    {
                        id_genero: 1,
                        nombre_genero: "Acción",
                    },
                    {
                        id_genero: 2,
                        nombre_genero: "Aventuras",
                    },
                    {
                        id_genero: 3,
                        nombre_genero: "Shooters",
                    }
                ],
                juegos: [
                    {
                        nombre_juego: "God of War",
                        id_genero: 1
                    },
                    {
                        nombre_juego: "Uncharted",
                        id_genero: 2
                    },
                    {
                        nombre_juego: "Resident Evil",
                        id_genero: 2
                    },
                    {
                        nombre_juego: "Dino Crisis",
                        id_genero: 2
                    },
                    {
                        nombre_juego: "Call of Duty",
                        id_genero: 3
                    }
                ],
                juegos_filtrados: [],
            }
        },

        methods: {
            seleccionarGenero(){
                this.juegos_filtrados = [];
                this.buscado = "";
                for(let i = 0; i < this.juegos.length; i++){
                    if(this.juegos[i].id_genero == this.selected){
                        this.juegos_filtrados.push(this.juegos[i]);
                    }
                }
            }
        }
    }
</script>

