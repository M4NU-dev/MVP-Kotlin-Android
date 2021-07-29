# MVP-Kotlin-Android
Android Developer at Reign test - MVP

## Comenzando 🚀

_Dentro del proyecto se puede apreciar una estructúra de código MVP hecho en Kotlin, la app consume un servicio de pruebas de Hacker News:_

```
https://hn.algolia.com/api/v1/search_by_date?query=mobile
```

El proyecto cuenta con dos vistas, la principal **MainActivity** donde se muestra un listado de articulos dentro de un **RecycleView** y una secundaria llamada **DetailArticleActivity**
donde se muestra el articulo seleccionado dentro de un **WebView.**

El servicio es consumido usando la libreria Retrofit

### Librerias Utilizadas 📋

Estas librerias son fundamentales para el buen funcionamiento de la app.

```
    implementation "com.squareup.retrofit2:retrofit:2.8.1"
    implementation "com.squareup.retrofit2:converter-gson:2.8.1"
    implementation 'com.google.code.gson:gson:2.8.6'
```

```
    implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
i   mplementation 'com.github.xabaras:RecyclerViewSwipeDecorator:1.3'
```

_Tambien se hace uso de ViewBinding, esto para realizar la vinculación de vistas mucho mas facil, todo esto se realiza en tu archivo build.gradle_

```
    buildFeatures{
        viewBinding = true
    }
```

## Implementar MVP a nuevas vistas 🔧

_Este proceso es realmente sencillo, dentro del proyecto existen carpetas que dividen la estructúra del mismo, dentro de la carpeta **UI**
puedes encontrar toda la lógica del MVP aplicada a las vistas._

_Puedes poner los archivos como mejor te parezca pero te recomiendo que si haces una vista nueva por ejemplo **PRODUCTOS**, crea una carpeta dentro de **UI** con el nombre de la misma._

_Esto te ayudara a mantener todo mas ordenado y tu mantenimiento sera mas fácil._

_Tus nuevas vistas deben tener esta estructura:_

```
    class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), MainContract.View
```

_Donde le indicas al **BaseActivity** tu **ActivityBinding** y el View perteneciente de tu **Contract**_

## Estructura del Presenter

```

    class MainPresenter(private val view: MainContract.View, private val API: MainContract.API): MainContract.Presenter, BasePresenter<MainContract.View>(view) {
        override fun getArticles() {
            API.getArticles({
                Articles ->
                    view.listArticles(Articles)
            },{
                error ->
                    view.error(error)
            })
        }
    }

```

## Estructura del Contract

```

    interface MainContract {
        interface View: BaseView {
            fun error(message: String)
            fun listArticles(articles: List<Article>)
        }

        interface Presenter {
            fun getArticles()
        }

        interface API {
            fun getArticles(onSuccess: (List<Article>) -> Unit, onError: (String) -> Unit)
        }
    }

```

## Estructura del Model

```

    object MainAPI: MainContract.API {
        override fun getArticles(onSuccess: (List<Article>) -> Unit, onError: (String) -> Unit){
            RetrofitClient.getAPI().getArticles().enqueue(object: Callback<HitsResponse> {
                override fun onFailure(call: Call<HitsResponse>, t: Throwable) {
                    t.printStackTrace()

                    onError("Ocurrio un error de conexion")
                }

                override fun onResponse(call: Call<HitsResponse>, response: Response<HitsResponse>) {
                    val res : HitsResponse? = response.body()

                    if(res != null) {
                        res?.let {
                            onSuccess(it.articulos)
                        }
                    }else{
                        onError("Ocurrio un error inesperado")
                    }
                }
            })
        }
    }

```



