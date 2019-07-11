package kz.zangpro.sportqaz.repositories

import kz.zangpro.sportqaz.extensions.getHtmlParsingPage
import kz.zangpro.sportqaz.models.FullNewsModel
import kz.zangpro.sportqaz.mvp.MVP
import org.jsoup.nodes.Document
import java.lang.StringBuilder

class FullNewsRepository: MVP.FullNewsRepository{

    private var mainTitle: String = ""
    private var dateText: String = ""
    private var imgUrl: String= ""
    private var title: String= ""
    private var text: StringBuilder = StringBuilder("")
    private var sourceText: String = ""

    override fun getDataFromProsport(url: String): FullNewsModel {

        val doc = getHtmlParsingPage(url)

        mainTitle = doc.select("div.content").select("div.article-header")
            .select("div.h1").select("h1").text()
        dateText = doc.select("div.content").select("div.article-header")
            .select("div.top.bottom").select("span").text()
        imgUrl = doc.select("div.content").select("div.article-body")
            .select("div.img-wrap").select("img").attr("src")


        val elements = doc.select("div.content").select("div.article-body")
            .select("p")

        for (i in elements.indices){
            if (i == 0) {
                val inElements = elements[i].select("strong")
                for (ele in inElements) title += ele.text() + " "
            }
            else {
                text.append(elements[i].text()).append(elements[i].select("span").text()).append("\n\n")
            }
        }

        sourceText = "Түпнұсқа: prosports.kz"


        return FullNewsModel(mainTitle, title, dateText, imgUrl, text.toString(), sourceText)
    }

}