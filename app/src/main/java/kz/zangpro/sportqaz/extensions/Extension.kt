package kz.zangpro.sportqaz.extensions

import io.reactivex.Observable
import kotlinx.coroutines.runBlocking
import kz.zangpro.sportqaz.models.NewsListModel
import kz.zangpro.sportqaz.repositories.NewsListRepository
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun doRxFunc(list: MutableList<NewsListModel>): Observable<MutableList<NewsListModel>> {

    return Observable.create { subscriber ->
        subscriber.onNext(list)
        subscriber.onComplete()
    }
}
