package com.example.mvpkotlinandroid.UI.Main

import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpkotlinandroid.API.Models.Article
import com.example.mvpkotlinandroid.API.Models.HitsResponse
import com.example.mvpkotlinandroid.Adapters.main.ArticlesAdapter
import com.example.mvpkotlinandroid.Base.BaseActivity
import com.example.mvpkotlinandroid.Helper.Common
import com.example.mvpkotlinandroid.Helper.Listeners.OnClickListArticle
import com.example.mvpkotlinandroid.Helper.SharedMemory
import com.example.mvpkotlinandroid.Helper.SwipeGesture
import com.example.mvpkotlinandroid.R
import com.example.mvpkotlinandroid.UI.DetailsArticle.DetailArticleActivity
import com.example.mvpkotlinandroid.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), MainContract.View, OnClickListArticle {

    override val mPresenter: MainPresenter = MainPresenter(this, MainAPI)
    private lateinit var mAdapter: ArticlesAdapter

    var articleList   = ArrayList<Article>()
    var articleDelete = ArrayList<Article>()

    override fun setToolbar(): Boolean { return true }
    override fun setHomeAsUpEnabled(): Boolean { return false }

    // INICIALIZA LA VISTA
    override fun initView() {
        showProgressDialog()
        mPresenter.getArticles()

        mBinding.boxMain.swRefresh.setOnRefreshListener {
            mPresenter.getArticles()
            getDeleteArticles()
        }

        getDeleteArticles()
    }

    // CARGA EL LISTADO DE ARTICULOS ELIMINADOS
    private fun getDeleteArticles() {
        val gson = Gson()
        val json = SharedMemory.getStringValue(this, Common.DELETE_ARTICLE)

        if (json != null) {
            if(!json.isEmpty()) {
                val tempData: ArrayList<Article> = gson.fromJson(
                    json,
                    object : TypeToken<ArrayList<Article>?>() {}.type
                )

                if(tempData.size > 0) {
                    articleDelete.addAll(tempData)
                }
            }
        }
    }

    // PROCESAR ALGUN ERROR DURANTE LA SOLICITUD AL SERVICIO WEB
    override fun error(message: String) {
        mBinding.boxMain.swRefresh.isRefreshing = false

        dismissProgressDialog()
        showToast(message, this)

        setTempDataView()
    }

    // LISTADO DE ARTICULOS RETORNADOS POR EL PRESENT
    override fun listArticles(articles: List<Article>) {
        mBinding.boxMain.swRefresh.isRefreshing = false
        
        if(articles != null) {
            val gson = Gson()
            val json = gson.toJson(articles)

            SharedMemory.setValue(this, Common.KEY_TEMP_DATA, json)
            setDataView(articles)
        }else{
            setTempDataView()
        }
    }

    // ENVIAR DATA DE ARTICULOS AL RECYCLEVIEW
    private fun setDataView(articles: List<Article>) {
        val deleteColor = resources.getColor(R.color.delete_row)
        val textColor   = resources.getColor(R.color.white)

        if(articleList.size > 0) {
            articleList.clear()
        }

        // VALIDAR ARTICULOS ELIMINADOS PARA NO MOSTRARLOS EN LA LISTA
        // NOTA: ESTE PROCESO SOLO SE EJECUTARA POR MOTIVOS DE DEMOSTRACIÓN,
        // EN PRODUCCIÓN DICHO PROCESO NO DEBE EJECUTARCE YA QUE NO ES OPTIMO,
        // SE DEBE BRINDAR UN SERVICIO WEB QUE TE PERMITA ELIMINAR DICHOS ARTICULOS

        for(article in articles) {
            var isExist : Boolean = false

            for(delete in articleDelete) {
                if(delete.story_id == article.story_id) {
                    isExist = true
                    break
                }
            }

            if(!isExist) {
                articleList.add(article)
            }
        }

        val swipeGesture = object : SwipeGesture(deleteColor, textColor) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when(direction) {
                    ItemTouchHelper.LEFT -> {
                        val position = viewHolder.adapterPosition

                        articleDelete.add(articleList.get(position))

                        val gson = Gson()
                        val json = gson.toJson(articleDelete)

                        SharedMemory.setValue(this@MainActivity, Common.DELETE_ARTICLE, json)

                        articleList.removeAt(position)
                        mAdapter.notifyItemRemoved(position)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(mBinding.boxMain.rvArticles)


        mAdapter = ArticlesAdapter(articleList, this)
        mBinding.boxMain.rvArticles.layoutManager = LinearLayoutManager(this)
        mBinding.boxMain.rvArticles.adapter = mAdapter
        dismissProgressDialog()
    }

    // CARGAR DATA TEMPORAL SI EL DISPOSITIVO NO ESTA CONECTADO A INTERNET
    private fun setTempDataView() {
        val gson = Gson()
        val json: String? = SharedMemory.getStringValue(this, Common.KEY_TEMP_DATA)

        val tempData: List<Article> = gson.fromJson(
            json,
            object : TypeToken<List<Article>?>() {}.type
        )

        setDataView(tempData)
    }
    // VENTO CLICK DEL ADAPTER
    override fun onItemClick(article: Article) {
        if(article != null) {
            val gson = Gson()
            val json = gson.toJson(article)

            val viewDetails = Intent(this, DetailArticleActivity::class.java)
            viewDetails.putExtra(Common.DATA_ARTICLE, json)
            startActivity(viewDetails)
        }
    }
}