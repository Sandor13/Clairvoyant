package com.anikin.aleksandr.dreamdictionary.view.managenote

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import com.anikin.aleksandr.dreamdictionary.R
import com.anikin.aleksandr.dreamdictionary.data.model.Color
import com.anikin.aleksandr.dreamdictionary.data.model.Note
import com.anikin.aleksandr.dreamdictionary.format
import com.anikin.aleksandr.dreamdictionary.getColorInt
import com.anikin.aleksandr.dreamdictionary.view.base.BaseActivity
import com.anikin.aleksandr.dreamdictionary.view.managenote.NoteViewState.Data
import java.util.*

private const val SAVE_DELAY = 200L

class NoteActivity : BaseActivity<Data, NoteViewState>() {

    companion object {

        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"

        fun getIntent(context: Context, id: String?): Intent =
            Intent(context, NoteActivity::class.java).apply { putExtra(EXTRA_NOTE, id) }
    }

    override val model: NoteViewModel by lazy { ViewModelProviders.of(this).get(NoteViewModel::class.java) }
    override val layoutRes: Int = R.layout.activity_note
    private var note: Note? = null
    private val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            triggerSaveNote()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }
    private var color = Color.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        intent.getStringExtra(EXTRA_NOTE)?.let { model.loadNote(it) }
        colorPicker.onColorClickListener = {
            color = it
            note_toolbar.setBackgroundColor(color.getColorInt(this))
            triggerSaveNote()
        }
    }

    override fun onBackPressed() {
        if (colorPicker.isOpen) {
            colorPicker.close()
            return
        }
        super.onBackPressed()
    }

    private fun togglePalette() {
        if (colorPicker.isOpen) {
            colorPicker.close()
        } else {
            colorPicker.open()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
        menuInflater.inflate(R.menu.note_menu, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> super.onBackPressed().let { true }
            R.id.palette -> togglePalette().let { true }
            R.id.delete -> deleteNote().let { true }
            else -> super.onOptionsItemSelected(item)
        }

    private fun deleteNote() {
        AlertDialog.Builder(this)
            .setTitle("Delete")
            .setMessage("Delete note?")
            .setPositiveButton("OK") { _, _ -> (model.deleteNote()) }
            .setNegativeButton(R.string.logout_dialog_cancel) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    override fun renderData(data: Data) {
        if (data.isDeleted) finish()

        this.note = data.note
        data.note?.let { color = it.color }
        renderView(data.note)
    }

    private fun setEditListeners() {
        titleEt.addTextChangedListener(textChangeListener)
        bodyEt.addTextChangedListener(textChangeListener)
    }

    private fun removeEditListeners() {
        titleEt.removeTextChangedListener(textChangeListener)
        bodyEt.removeTextChangedListener(textChangeListener)
    }

    private fun renderView(note: Note?) {
        note?.let {
            supportActionBar?.title = note.lastChanged.format()
            removeEditListeners()
            titleEt.setText(note.title)
            bodyEt.setText(note.description)
            setEditListeners()
            note_toolbar.setBackgroundColor(note.color.getColorInt(applicationContext))
        }
    }

    private fun triggerSaveNote() {
        if (titleEt.text != null && titleEt.text!!.length < 3) return

        Handler().postDelayed(
            {
                note = note?.copy(
                    title = titleEt.text.toString(),
                    description = bodyEt.text.toString(),
                    lastChanged = Date(),
                    color = color
                )
                        ?: createNewNote()

                if (note != null) model.saveChanges(note!!)
            },
            SAVE_DELAY
        )
    }

    private fun createNewNote(): Note = Note(
        UUID.randomUUID().toString(),
        titleEt.text.toString(),
        bodyEt.text.toString()
    )

    private fun initViews() {
        setSupportActionBar(note_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.new_note_title)
        setEditListeners()
    }
}
