<template>
    <div>
        <header>
            <div class="branding">
                <img class ="logo-small" src="./img/fidget-spinner.svg" />
                <span>Nagevación superior</span>
            </div>
        <nav>
            <a href="#/">Home</a>
            <a href="#/actors">Lista</a>
            <a href="#/actors/new">Nuevo</a>
            <a href="#/games">Juegos</a>
            <a href="#/filter_game">Buscar Juegos</a>
        </nav>
        </header>

        <main class="container">
            <router-view></router-view>
        </main>
    </div>
</template>

<script>
export default {

}
</script>
