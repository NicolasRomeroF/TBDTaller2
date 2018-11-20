<template>
    <div class="center">
    
        <h1>Gráfico donut sobre cantidad de menciones de juegos (Positivas + Negativas)</h1>
        
        <br><hr><br>
        
        <div style="width:100%; float: left;"  >

            <vue-chart  v-if="this.chartData !== null" type="horizontalBar" :data="this.chartData"></vue-chart>
            <div v-else>
                <div class=" lds-css ng-scope">
                    <div style="width:100%;height:100%" class="lds-bars">
                        <div></div><div></div><div></div><div></div> <div></div>
                    </div>
                </div>
            </div>

        </div>

        <div style="width: 75%">
            <vue-chart v-if="this.chartData !== null" type="doughnut" :data="this.chartData"></vue-chart>
            <div v-else>
                <div class="lds-css  ng-scope">
                    <div style="width:100%;height:100%" class="lds-bars">
                        <div></div><div></div><div></div><div></div> <div></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import VueChart from "vue-chart-js";
    export default {
        components: {
            VueChart
        },
        data(){
            return{
                title: 'Prueba Juegos',
                games: [
                    {
                        name: "God of War",
                        val_pos: 300,
                        val_neg: 40
                    },
                    {
                        name: "Uncharted",
                        val_pos: 250,
                        val_neg: 25
                    },
                    {
                        name: "Resident Evil",
                        val_pos: 400,
                        val_neg: 150
                    },
                    {
                        name: "Dino Crisis",
                        val_pos: 450,
                        val_neg: 40
                    },
                    {
                        name: "Call of Duty",
                        val_pos: 300,
                        val_neg: 200
                    }
                ],
                chartData: null,
                torta:false,
                barra: false,
                colores: []
            }
        },
        created(){
            this.crearGrafico();
            console.log("grafico creado", this.chartData);
        },
        methods: {
            mostrarBarra(){
                this.barra=true;
                this.torta=false;

            },
            mostrarTorta(){
                this.barra=false;
                this.torta=true;
            },

            colorRandom(){
                var color = "#"+((1<<24)*Math.random()|0).toString(16)
                this.colores.push(color)
            },

            crearGrafico() {
                this.chartData = {
                    labels: [],
                    datasets: [
                        {
                            backgroundColor: this.colores,
                            data: [],
                            label: ["Cantidad de menciones"]
                        },
                    ]
                };

                for (let i = 0; i < this.games.length; i++) {
                    this.colorRandom();
                    this.chartData.labels = this.chartData.labels.concat([this.games[i].name]);
                    this.chartData.datasets[0].data.push( this.games[i].val_pos + this.games[i].val_neg);
                }
            }
        }
    }
</script>
