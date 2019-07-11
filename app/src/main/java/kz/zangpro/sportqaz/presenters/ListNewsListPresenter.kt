package kz.zangpro.sportqaz.presenters

import kotlinx.coroutines.*
import kz.zangpro.sportqaz.mvp.MVP
import kz.zangpro.sportqaz.repositories.NewsListRepository

class ListNewsListPresenter(val view: MVP.NewsListView) : MVP.NewsListPresenter {

    val repository: MVP.NewsListRepository = NewsListRepository()
    lateinit var job: Job

    override fun initialize() {

        job = GlobalScope.launch(Dispatchers.IO){

            val list = repository.getDataFromProsport()

            launch(Dispatchers.Main) {
                view.showNewsList(list)
            }

        }
    }


    override fun onDestroy() {
        //Do Nothing yet...
        job.cancel()
    }

}