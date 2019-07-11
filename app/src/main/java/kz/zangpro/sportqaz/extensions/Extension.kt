package kz.zangpro.sportqaz.extensions

import io.reactivex.Observable
import kotlinx.coroutines.runBlocking
import kz.zangpro.sportqaz.models.NewsListModel
import kz.zangpro.sportqaz.repositories.NewsListRepository
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun getHtmlParsingPage(url: String): Document {

    val doc = Jsoup.connect(url)
        .userAgent("Chrome/4.0.249.0 Safari/532.5")
        .referrer("https://www.google.com")
        .get()


    return doc
}

//fun doRxFunc(list: MutableList<NewsListModel>): Observable<MutableList<NewsListModel>> {
//
//    return Observable.create { subscriber ->
//        subscriber.onNext(list)
//        subscriber.onComplete()
//    }
//}
//
//
//fun getDataFromAlaman(): MutableList<NewsListModel> {
//
//    val repository: MVP.NewsListRepository = NewsListRepository()
//    val list = mutableListOf<NewsListModel>()
//    val url = "https://www.alaman.kz/news?tag=Қазақ%20футболы"
//    val baseUrl = "https://www.alaman.kz/"
//
//    lateinit var title: String
//    lateinit var img: String
//    lateinit var siteUrl: String
//    lateinit var date: String
//    var views = 0
//
//    val doc = repository.getHtmlParsingPage(url)
//
//    val elements = doc.select("div.post.fashion-post.post-default-list.post-separator-border")
//
//    for (e in elements) {
//        title = e.select("div.content").select("h4").select("a").text()
//        img = baseUrl + e.select("div.image").select("a").select("img").attr("src")
//        siteUrl = baseUrl + e.select("div.content").select("a").attr("href")
//        date = e.select("div.content").select("span.meta-item.date").text()
//        views = e.select("div.content").select("span.meta-item.view").text().toInt()
//        list.add(NewsListModel(title, img, siteUrl, date, views))
//    }
//
//    return list
//}
