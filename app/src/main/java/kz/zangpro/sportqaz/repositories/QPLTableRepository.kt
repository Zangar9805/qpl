package kz.zangpro.sportqaz.repositories

import kz.zangpro.sportqaz.extensions.getHtmlParsingPage
import kz.zangpro.sportqaz.models.TablesModel
import kz.zangpro.sportqaz.mvp.MVP

class QPLTableRepository: MVP.LeagueTabRepository {

    private val url = "https://prosports.kz/kz/football/kpl/table"
    private var name = ""
    private var imgUrl = ""
    private var goals = ""
    private var runk = 0
    private var point = 0
    private var wins = 0
    private var draws = 0
    private var losts = 0
    private var games = 0

    override fun getDataFromProsport(): MutableList<TablesModel> {

        val listTable = mutableListOf<TablesModel>()

        val doc = getHtmlParsingPage(url)

        val table = doc.select("div.table.active").select("table").select("tbody")
        val trs = table.select("tr")

        for (i in trs.indices){

            val tds = trs[i].select("td")

            for (j in tds.indices){

                when (j) {
                    0 -> runk = tds[j].select("span").text().toInt()
                    1 -> {
                        name = tds[j].select("div.title").select("a").text().split(" ")[0]
                        imgUrl = tds[j].select("div.flag").select("img").attr("src")
                    }
                    2 -> games = tds[j].text().toInt()
                    3 -> wins = tds[j].text().toInt()
                    4 -> draws = tds[j].text().toInt()
                    5 -> losts = tds[j].text().toInt()
                    6 -> goals = tds[j].text()
                    7 -> point = tds[j].text().toInt()
                }

            }

            listTable.add(TablesModel(runk, name, imgUrl, games, wins, draws, losts, goals, point))
        }

        return listTable
    }
}