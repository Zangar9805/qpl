package kz.zangpro.sportqaz.presenters

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kz.zangpro.sportqaz.models.FullNewsModel
import kz.zangpro.sportqaz.mvp.MVP
import kz.zangpro.sportqaz.repositories.FullNewsRepository

class FullNewsPresenter(val view: MVP.FullNewsView) : MVP.NewsPresenter {

    val repository = FullNewsRepository()

    lateinit var newsModel: FullNewsModel
    lateinit var job: Job

    override fun initialize(url: String) {
        job = GlobalScope.launch(Dispatchers.IO){
            newsModel = repository.getDataFromProsport(url)

            launch(Dispatchers.Main){
                view.showNewsList(newsModel)
            }
        }

    }

    override fun onDestroy() {
        //Do Nothing yet...
        job.cancel()
    }
}