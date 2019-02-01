package com.anikin.aleksandr.dreamdictionary.view.mainscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.anikin.aleksandr.dreamdictionary.R
import com.anikin.aleksandr.dreamdictionary.data.model.Note
import com.anikin.aleksandr.dreamdictionary.view.base.BaseActivity
import com.anikin.aleksandr.dreamdictionary.view.managenote.NoteActivity
import com.anikin.aleksandr.dreamdictionary.view.splash.SplashActivity

class MainActivity : BaseActivity<List<Note>?, MainViewState>(), LogoutDialog.LogoutListener {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override val model:  MainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override val layoutRes: Int = R.layout.activity_main
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(main_toolbar)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
        MenuInflater(this).inflate(R.menu.menu_main, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.logout -> showLogoutDialog().let { true }
            else -> false
        }

    private fun initViews() {
        setRecycleView()
        fab.setOnClickListener { openNoteScreen(null) }
    }

    private fun setRecycleView() {
        adapter = MainAdapter(object : MainAdapter.OnItemClickListener {

            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        main_recyclerview.adapter = adapter
    }

    private fun openNoteScreen(note: Note?) {
        startActivity(NoteActivity.getIntent(this, note?.id))
    }

    override fun renderData(data: List<Note>?) {
        data?.let { adapter.notes = data }
    }

    private fun showLogoutDialog() {
        supportFragmentManager.findFragmentByTag(LogoutDialog.TAG) ?: LogoutDialog.createInstance().show(
            supportFragmentManager,
            LogoutDialog.TAG
        )
    }

    override fun onLogout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
    }
}
