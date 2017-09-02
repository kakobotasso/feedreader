package br.com.kakobotasso.feedreader

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class ItemAdapter(val list: ArrayList<MainActivity.Item>, val context: Context): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title = view.findViewById(R.id.title) as TextView
        val author = view.findViewById(R.id.author) as TextView
        val date = view.findViewById(R.id.date) as TextView
        val image = view.findViewById(R.id.image) as ImageView
        val btSeeMore = view.findViewById(R.id.btSeeMore) as Button
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_list, parent, false)
        var ivh = ItemViewHolder(v)

        return ivh
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.title?.text = list[position].title
        holder?.author?.text = list[position].author
        holder?.date?.text = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).format(Date(list[position].date))
        holder?.btSeeMore?.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, list[position].link)
            context.startActivity(intent)
        }

        DownloadImageTask(holder?.image!!).execute(list[position].image)

    }

    override fun getItemCount(): Int = list.size
}