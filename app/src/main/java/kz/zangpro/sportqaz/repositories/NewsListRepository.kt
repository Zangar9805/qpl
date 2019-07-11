package kz.zangpro.sportqaz.repositories

import kz.zangpro.sportqaz.extensions.getHtmlParsingPage
import kz.zangpro.sportqaz.models.NewsListModel
import kz.zangpro.sportqaz.mvp.MVP
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class NewsListRepository : MVP.NewsListRepository {

    override fun getDataFromProsport(): MutableList<NewsListModel> {

        val list = mutableListOf<NewsListModel>()
        val url = "https://prosports.kz/kz/football/kpl"
        val baseUrl = "https://m.prosports.kz"

        lateinit var title: String
        lateinit var img: String
        lateinit var siteUrl: String
        lateinit var date: String
        var views: Int = 0

        val doc = getHtmlParsingPage(url)

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


}