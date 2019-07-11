package kz.zangpro.sportqaz.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kz.zangpro.sportqaz.R
import kz.zangpro.sportqaz.models.TablesModel

class QPLTableAdapter(val listTables: MutableList<TablesModel>, val context: Context, val listener: (TablesModel) -> Unit)
    : RecyclerView.Adapter<QPLTableAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.qpl_table_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listTables.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(listTables[pos], listener)
    }


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        private val name = view.findViewById<TextView>(R.id.tableNameText)
        private val imgLogo = view.findViewById<ImageView>(R.id.tableImg)
        private val goals = view.findViewById<TextView>(R.id.tableGoalsText)
        private val runk = view.findViewById<TextView>(R.id.tableRunkText)
        private val point = view.findViewById<TextView>(R.id.tablePointsText)
        private val wins = view.findViewById<TextView>(R.id.tableWinsText)
        private val draws = view.findViewById<TextView>(R.id.tableDrawsText)
        private val losts = view.findViewById<TextView>(R.id.tableLostsText)
        private val games = view.findViewById<TextView>(R.id.tableGamesText)

        fun bind(table: TablesModel, listener: (TablesModel) -> Unit){
            name.text = table.clubName
            goals.text = table.countGoals
            runk.text = table.clubRunk.toString()
            point.text = table.countPoints.toString()
            wins.text = table.countWins.toString()
            draws.text = table.countDraws.toString()
            losts.text = table.countLosts.toString()
            games.text = table.countGame.toString()

            Picasso.get()
                .load(table.clubLogo)
                .fit()
                .centerCrop()
                .into(imgLogo)

            itemView.setOnClickListener {
                listener(table)
            }
        }
    }
}