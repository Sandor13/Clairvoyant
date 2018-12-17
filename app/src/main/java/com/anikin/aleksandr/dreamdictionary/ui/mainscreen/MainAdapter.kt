package com.anikin.aleksandr.dreamdictionary.ui.mainscreen

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anikin.aleksandr.dreamdictionary.R
import com.anikin.aleksandr.dreamdictionary.data.model.Color
import com.anikin.aleksandr.dreamdictionary.data.model.Note

class MainAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MainAdapter.NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int): Unit {
        holder.bind(notes[position])
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleTextView = itemView.findViewById<TextView>(R.id.title)
        private val descriptionTextView = itemView.findViewById<TextView>(R.id.description)

        fun bind(note: Note) {
            with(note) {
                titleTextView.text = title
                descriptionTextView.text = description
                val color = when (note.color) {
                    Color.WHITE -> R.color.color_white
                    Color.VIOLET -> R.color.color_violet
                    Color.YELLOW -> R.color.color_yello
                    Color.RED -> R.color.color_red
                    Color.PINK -> R.color.color_pink
                    Color.GREEN -> R.color.color_green
                    Color.BLUE -> R.color.color_blue
                }
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context.applicationContext, color))
                itemView.setOnClickListener { onItemClickListener.onItemClick(note) }
            }
        }
    }

    interface OnItemClickListener {

        fun onItemClick(note: Note)
    }
}

