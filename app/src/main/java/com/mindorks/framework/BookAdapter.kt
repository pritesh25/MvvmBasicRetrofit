package com.mindorks.framework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mindorks.framework.mvvm.R
import java.util.*

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookSearchResultHolder>() {
    private var results: List<StackModel.Item> = ArrayList<StackModel.Item>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchResultHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return BookSearchResultHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookSearchResultHolder, position: Int) {
        val volume: StackModel.Item = results[position]
        holder.titleTextView.setText(volume.title)

        holder.smallThumbnailImageView.load(volume.owner!!.profileImage!!)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun setResults(results: List<StackModel.Item>) {
        this.results = results
        notifyDataSetChanged()
    }

    inner class BookSearchResultHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView
        val authorsTextView: TextView
        val publishedDateTextView: TextView
        val smallThumbnailImageView: ImageView

        init {
            titleTextView = itemView.findViewById(R.id.book_item_title)
            authorsTextView = itemView.findViewById(R.id.book_item_authors)
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate)
            smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail)
        }
    }
}