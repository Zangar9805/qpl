package kz.zangpro.sportqaz.models


data class NewsListModel(val title: String,
                         val img: String,
                         val siteUrl: String,
                         val date: String,
                         val views: Int,
                         val baseUrl: String = "alaman.kz")

data class NewsModel(val img: String)

data class TablesModel(val clubRunk: Int,
                       val clubName: String,
                       val clubLogo: String,
                       val countGame: Int,
                       val countWins: Int,
                       val countDraws: Int,
                       val countLosts: Int,
                       val countGoals: String,
                       val countPoints: Int)