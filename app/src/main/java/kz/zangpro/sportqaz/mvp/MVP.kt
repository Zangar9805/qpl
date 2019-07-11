package kz.zangpro.sportqaz.mvp

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kz.zangpro.sportqaz.models.FullNewsModel
import kz.zangpro.sportqaz.models.NewsListModel
import kz.zangpro.sportqaz.models.TablesModel
import org.jsoup.nodes.Document

interface MVP {

    //ListNewsFragment implement it---------------------------//
    interface NewsListView{
        fun showNewsList(list: MutableList<NewsListModel>)
        fun showLoading()
        fun hideLoading()
    }

    interface NewsListPresenter{
        fun initialize()
        fun onDestroy()
    }

    interface NewsListRepository{
        fun getDataFromProsport(): MutableList<NewsListModel>
    }
    //-----------------------------------------------------------//


    //NewsFragment implement it---------------------------//
    interface FullNewsView{
        fun showNewsList(fullNews: FullNewsModel)
        fun showLoading()
        fun hideLoading()
    }

    interface NewsPresenter{
        fun initialize(url: String)
        fun onDestroy()
    }

    interface FullNewsRepository{
        fun getDataFromProsport(url: String): FullNewsModel
    }
    //-----------------------------------------------------------//


    //TableLeagueFragment implement it---------------------------//
    interface LeagueTabView{
        fun showTable(tableList: MutableList<TablesModel>)
        fun showLoading()
        fun hideLoading()
    }

    interface LeagueTabPresenter{
        fun initialize()
        fun onDestroy()
    }

    interface LeagueTabRepository{
        fun getDataFromProsport(): MutableList<TablesModel>
    }
    //-----------------------------------------------------------//


}