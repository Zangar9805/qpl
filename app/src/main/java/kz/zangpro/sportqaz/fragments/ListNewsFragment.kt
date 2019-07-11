package kz.zangpro.sportqaz.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kz.zangpro.sportqaz.presenters.ListNewsListPresenter
import kz.zangpro.sportqaz.R
import kz.zangpro.sportqaz.adapters.NewsAdapter
import kz.zangpro.sportqaz.models.NewsListModel
import kz.zangpro.sportqaz.mvp.MVP

class ListNewsFragment : Fragment(), MVP.NewsListView {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NewsAdapter
    val presenter = ListNewsListPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.list_news_fragment, container!!, false)

        recyclerView = view.findViewById(R.id.recyclerNews)
        recyclerView.layoutManager = LinearLayoutManager(container.context)

        presenter.initialize()

        return view;
    }


    override fun showNewsList(list: MutableList<NewsListModel>) {
        adapter = NewsAdapter(list, context!!) {
//            Toast.makeText(context, it.siteUrl, Toast.LENGTH_SHORT).show()
            val aNewsFr = NewsFragment()
            val bundle = Bundle()
            bundle.putString("URL", it.siteUrl)
            aNewsFr.arguments = bundle
            fragmentManager!!.beginTransaction().replace(R.id.container, aNewsFr).addToBackStack(null).commit()
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