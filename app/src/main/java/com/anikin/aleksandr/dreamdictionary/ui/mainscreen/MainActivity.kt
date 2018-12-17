package com.anikin.aleksandr.dreamdictionary.ui.mainscreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.anikin.aleksandr.dreamdictionary.R
import com.anikin.aleksandr.dreamdictionary.data.model.Note
import com.anikin.aleksandr.dreamdictionary.ui.managenote.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = MainAdapter(object : MainAdapter.OnItemClickListener {

            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        main_recyclerview.adapter = adapter

        viewModel.getViewState().observe(this, Observer<MainViewState> { t ->
            t?.let { adapter.notes = it.notes }
        })

        initView()
    }

    private fun initView() {
        fab.setOnClickListener { openNoteScreen(null) }
    }

    private fun openNoteScreen(note: Note?) {
        val intent = NoteActivity.getIntent(this, note)
        startActivity(intent)
    }
}
