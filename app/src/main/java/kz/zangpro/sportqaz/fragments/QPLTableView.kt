package kz.zangpro.sportqaz.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kz.zangpro.sportqaz.R
import kz.zangpro.sportqaz.adapters.QPLTableAdapter
import kz.zangpro.sportqaz.models.TablesModel
import kz.zangpro.sportqaz.mvp.MVP
import kz.zangpro.sportqaz.presenters.QPLTablePresenter

class QPLTableView : Fragment(), MVP.LeagueTabView {

    private lateinit var tableRecycler: RecyclerView
    private lateinit var adapter: QPLTableAdapter

    private val presenter = QPLTablePresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.qpl_table_fragment, container, false)

        tableRecycler = view.findViewById(R.id.recyclerLeagTable)
        tableRecycler.layoutManager = LinearLayoutManager(container!!.context)

        presenter.initialize()

        return view
    }


    override fun showTable(tableList: MutableList<TablesModel>) {
        adapter = QPLTableAdapter(tableList, context!!){
            Toast.makeText(context, it.clubName, Toast.LENGTH_SHORT).show()
        }

        tableRecycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}