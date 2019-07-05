package kz.zangpro.sportqaz.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kz.zangpro.sportqaz.presenters.Presenter
import kz.zangpro.sportqaz.R
import kz.zangpro.sportqaz.adapters.NewsAdapter
import kz.zangpro.sportqaz.models.NewsListModel
import kz.zangpro.sportqaz.mvp.MainContract

class ListNewsFragment : Fragment(), MainContract.NewsListView {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NewsAdapter
    val presenter = Presenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.prev_news_fragment, container!!, false)

        recyclerView = view.findViewById(R.id.recyclerNews)
        recyclerView.layoutManager = LinearLayoutManager(container.context)

        presenter.initialize()

        return view;
    }


    override fun showNewsList(list: MutableList<NewsListModel>) {
        adapter = NewsAdapter(list, context!!) {
            Toast.makeText(context, it.siteUrl, Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}