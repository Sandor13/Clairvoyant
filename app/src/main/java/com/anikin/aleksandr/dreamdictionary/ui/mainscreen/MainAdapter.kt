package com.anikin.aleksandr.dreamdictionary.ui.mainscreen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anikin.aleksandr.dreamdictionary.R
import com.anikin.aleksandr.dreamdictionary.data.model.Note

class MainAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int): Unit {
        holder.bind(notes[position])
    }
}

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView = itemView.findViewById<TextView>(R.id.title)
    private val descriptionTextView = itemView.findViewById<TextView>(R.id.description)

    fun bind(note: Note) {
        with(note) {
            titleTextView.text = title
            descriptionTextView.text = description
            itemView.setBackgroundColor(color)
        }
    }
}
