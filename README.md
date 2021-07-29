# MVP-Kotlin-Android
Android Developer at Reign test - MVP

## Comenzando 🚀

_Dentro del proyecto se puede apreciar una estructura de codigo MVP hecho en Kotlin, la app consume un servicio de pruebas de Hacker News:_

```
https://hn.algolia.com/api/v1/search_by_date?query=mobile
```

El proyecto cuenta con dos vistas, la principal **MainActivity** donde se muestra un listado de articulos dentro de un **RecycleView** y una secundaria llamada **DetailArticleActivity**
donde se muestra el articulo seleccionado dentro de un **WebView.**

El servicio es consumido usando la libreria Retrofit

### Librerias Utilizadas 📋

Estas librerias son fundamentales para el bun funcionamiento de la app.

```
implementation "com.squareup.retrofit2:retrofit:2.8.1"
implementation "com.squareup.retrofit2:converter-gson:2.8.1"
implementation 'com.google.code.gson:gson:2.8.6'
```

```
implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
implementation 'com.github.xabaras:RecyclerViewSwipeDecorator:1.3'
```

## Implementar MVP a nuevas vistas 🔧

_Este proceso es realmente sencillo, dentro del proyecto existen carpetas que dividen la estructura del mismo, dentro de la carpeta **UI**
puedes encontrar toda la logia del MVP aplicada a las vistas._

_Puedes poner los archivos como mejor te paresca pero te recomiendo que si haces una vista nueva por ejemplo **PRODUCTOS** crea una carpeta dentro de **UI** con el nombre de la misma._

_Esto te ayudara a mantener todo mas ordenado y su mantenimiento sera mas facil._

