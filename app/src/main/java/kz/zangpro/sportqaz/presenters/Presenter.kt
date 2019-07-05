package kz.zangpro.sportqaz.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kz.zangpro.sportqaz.models.NewsListModel
import kz.zangpro.sportqaz.mvp.MainContract
import kz.zangpro.sportqaz.extensions.doRxFunc
import kz.zangpro.sportqaz.repositories.NewsListRepository
import org.jsoup.nodes.Document

class Presenter(val view: MainContract.NewsListView) : MainContract.NewsListPresenter {

    lateinit var dispose: Disposable

    override fun initialize() {

        val list = mutableListOf<NewsListModel>()

        runBlocking {
            launch(Dispatchers.IO) {
                list.addAll(getDataFromAlaman())
            }
        }

        view.showNewsList(list)

//        dispose = doRxFunc(getDataFromAlaman())
//            .subscribeOn(AndroidSchedulers.mainThread())
//            .observeOn(Schedulers.io())
//            .subscribe({
//                view.showNewsList(it)
//            },{
//                it.localizedMessage
//            })
    }

    override fun getDataFromAlaman(): MutableList<NewsListModel> {

        val repository: MainContract.NewsListRepository = NewsListRepository()
        val list = mutableListOf<NewsListModel>()
        val url = "https://www.alaman.kz/news?tag=Қазақ%20футболы"
        val baseUrl = "https://www.alaman.kz/"

        lateinit var title: String
        lateinit var img: String
        lateinit var siteUrl: String
        lateinit var date: String
        var views: Int = 0

        val doc = repository.getHtmlParsingPage(url)

        val elements = doc.select("div.post.fashion-post.post-default-list.post-separator-border")

        for (e in elements) {
            title = e.select("div.content").select("h4").select("a").text()
            img = baseUrl + e.select("div.image").select("a").select("img").attr("src")
            siteUrl = baseUrl + e.select("div.content").select("a").attr("href")
            date = e.select("div.content").select("span.meta-item.date").text()
            views = e.select("div.content").select("span.meta-item.view").text().toInt()
            list.add(NewsListModel(title, img, siteUrl, date, views))
        }

        return list
    }

    override fun getDataFromProsport(): MutableList<NewsListModel> {

        lateinit var doc: Document
        val repository: MainContract.NewsListRepository = NewsListRepository()
        val list = mutableListOf<NewsListModel>()
        val url = "https://prosports.kz/kz/football/kpl"
        val baseUrl = "https://prosports.kz"

        lateinit var title: String
        lateinit var img: String
        lateinit var siteUrl: String
        lateinit var date: String
        var views: Int = 0

        doc = repository.getHtmlParsingPage(url)

        val news = doc.select("div.content").select("div.news").select("div.items").select("div.item")

        for (n in news) {
            title = n.select("div.row.info")
                .select("div.right").select("div.title")
                .select("a").text()
            img = n.select("div.row.info")
                .select("div.image").select("a")
                .select("img").attr("src")
            siteUrl = baseUrl + n.select("div.row.info")
                .select("div.right").select("div.title")
                .select("a").attr("href")
            date = n.select("div.row.top")
                .select("div.date").text()

            list.add(NewsListModel(title, img, siteUrl, date, views, "prosports.kz"))
        }
        return list
    }

    override fun onDestroy() {
        dispose.dispose()
    }

}