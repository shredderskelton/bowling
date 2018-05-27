package com.nickskelton.bowlingscorer

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView

class FrameAdapter(private val context: Context) : RecyclerView.Adapter<FrameViewHolder>() {

    val items = mutableListOf<Frame>()

    fun update(newItems: Array<Frame>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrameViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.frame_item, parent, false)
        return FrameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FrameViewHolder, position: Int) {
        holder.bind(items[position], (position + 1).toString())
    }
}

class FrameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val first: TextView by lazy {
        itemView.findViewById<TextView>(R.id.firstRollTextView)
    }
    private val second: TextView by lazy {
        itemView.findViewById<TextView>(R.id.secondRollTextView)
    }
    private val third: TextView by lazy {
        itemView.findViewById<TextView>(R.id.thirdRollTextView)
    }
    private val total: TextView by lazy {
        itemView.findViewById<TextView>(R.id.totalTextView)
    }
    private val count: TextView by lazy {
        itemView.findViewById<TextView>(R.id.frameCountTextView)
    }

    fun bind(frame: Frame, label: String) {
        count.text = label
        first.text = frame.firstRollText
        second.text = frame.secondRollText
        total.text = frame.cumulativeTotalText
        if (frame is EndFrame) {
            third.text = frame.thirdRollText
            third.visibility = VISIBLE
        } else {
            third.visibility = GONE
        }
        if (frame.valid)
            total.setTextColor(total.rootView.context.getColor(R.color.black))
        else
            total.setTextColor(total.rootView.context.getColor(R.color.red))

    }
}