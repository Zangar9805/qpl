package kz.zangpro.sportqaz.mvp

import kz.zangpro.sportqaz.models.NewsListModel
import org.jsoup.nodes.Document

interface MainContract {

    interface NewsListView{
        fun showNewsList(list: MutableList<NewsListModel>)
        fun showLoading()
        fun hideLoading()
    }

    interface NewsListPresenter{
        fun initialize()
        fun getDataFromAlaman(): MutableList<NewsListModel>
        fun getDataFromProsport(): MutableList<NewsListModel>
        fun onDestroy()
    }

    interface NewsListRepository{
        fun getHtmlParsingPage(url: String): Document
    }


}