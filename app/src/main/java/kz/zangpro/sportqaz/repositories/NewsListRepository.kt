package kz.zangpro.sportqaz.repositories

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kz.zangpro.sportqaz.mvp.MainContract
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class NewsListRepository : MainContract.NewsListRepository {

    override fun getHtmlParsingPage(url: String): Document {

        val doc = Jsoup.connect(url)
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .referrer("https://www.google.com")
            .get()


        return doc
    }
}