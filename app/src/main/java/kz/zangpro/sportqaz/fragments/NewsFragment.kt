package kz.zangpro.sportqaz.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kz.zangpro.sportqaz.R
import kz.zangpro.sportqaz.models.FullNewsModel
import kz.zangpro.sportqaz.mvp.MVP
import kz.zangpro.sportqaz.presenters.FullNewsPresenter

class NewsFragment : Fragment(), MVP.FullNewsView{

    private val presenter = FullNewsPresenter(this)
    private var url: String? = null
    private lateinit var newsMainTitle: TextView
    private lateinit var newsDateText: TextView
    private lateinit var newsMainPoster: ImageView
    private lateinit var newsTitle: TextView
    private lateinit var newsText: TextView
    private lateinit var newsSourceText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) url = bundle.getString("URL")
        else Log.d("FullNewsFr", "bundle == null")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.news_fragment, container, false)

        newsMainTitle = view.findViewById(R.id.aboutNewsGeneralTitle)
        newsDateText = view.findViewById(R.id.aboutNewsDate)
        newsMainPoster = view.findViewById(R.id.aboutNewsPoster)
        newsTitle = view.findViewById(R.id.aboutNewsTitle)
        newsText = view.findViewById(R.id.aboutNewsText)
        newsSourceText = view.findViewById(R.id.aboutNewsSource)

        if (url != null){
            presenter.initialize(url as String)
        }

        return view
    }

    override fun showNewsList(fullNews: FullNewsModel) {
        newsMainTitle.text = fullNews.mainTitle
        newsDateText.text = fullNews.date
        newsTitle.text = fullNews.title
        newsText.text = fullNews.text
        newsSourceText.text = fullNews.sourceText

        Picasso.get()
            .load(fullNews.imgUrl)
//            .fit()
//            .centerCrop()
            .into(newsMainPoster)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

}