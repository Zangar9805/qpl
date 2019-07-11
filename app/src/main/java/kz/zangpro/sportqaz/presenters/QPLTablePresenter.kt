package kz.zangpro.sportqaz.presenters

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kz.zangpro.sportqaz.mvp.MVP
import kz.zangpro.sportqaz.repositories.QPLTableRepository

class QPLTablePresenter(val view: MVP.LeagueTabView) : MVP.LeagueTabPresenter {

    val repository = QPLTableRepository()

    override fun initialize() {
        GlobalScope.launch(Dispatchers.IO) {
            val tableList = repository.getDataFromProsport()

            launch(Dispatchers.Main){
                view.showTable(tableList)
            }
        }
    }

    override fun onDestroy() {
        // Do Nothing yet
    }
}