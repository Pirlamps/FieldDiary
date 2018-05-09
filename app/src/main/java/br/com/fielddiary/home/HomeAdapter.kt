package br.com.fielddiary.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fielddiary.R
import br.com.fielddiary.model.Growth
import kotlinx.android.synthetic.main.row_growth.view.*

class HomeAdapter(private var dataSet: List<Growth>? = null) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_growth, parent, false)
        return HomeHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet?.size ?: 0
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bind(dataSet?.get(position))
    }

    fun setData(dataSet: List<Growth>?) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

//    fun addData(data: Growth) {
//        this.dataSet?.add()
//    }

    inner class HomeHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Growth?) {
            itemView.row_title_tv.text = item?.title
            itemView.row_desc_tv.text = item?.desc
        }
    }
}
