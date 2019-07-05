package kz.zangpro.sportqaz.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kz.zangpro.sportqaz.models.NewsListModel
import kz.zangpro.sportqaz.R

class NewsAdapter(val listNews: MutableList<NewsListModel>, val context: Context, val listener: (NewsListModel) -> Unit)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_news_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(listNews[pos], listener)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val title = view.findViewById<TextView>(R.id.newsTitle)
        val date = view.findViewById<TextView>(R.id.newsDate)
        val views = view.findViewById<TextView>(R.id.newsViews)
        val urlSite = view.findViewById<TextView>(R.id.newsUrlSite)
        val imgPoster = view.findViewById<ImageView>(R.id.newsPoster)

        fun bind(newsListModel: NewsListModel, listener: (NewsListModel) -> Unit){
            title.text = newsListModel.title
            date.text = newsListModel.date
            views.text = newsListModel.views.toString()
            urlSite.text = newsListModel.baseUrl

            Picasso.get()
                .load(newsListModel.img)
                .fit()
                .centerCrop()
                .into(imgPoster)

            itemView.setOnClickListener {
                listener(newsListModel)
            }
        }
    }
}