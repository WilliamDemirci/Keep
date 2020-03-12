package com.wil8dev.keep

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note_detail.*


class NoteDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_NOTE = "note"
        val EXTRA_NOTE_INDEX = "noteIndex"
        val REQUEST_CODE_EDITED_NOTE = 1
    }

    lateinit var note: Note
    var noteIndex: Int = -1 // if noteIndex == -1 -> new Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        note = intent.getParcelableExtra<Note>(EXTRA_NOTE)
        noteIndex = intent.getIntExtra(EXTRA_NOTE_INDEX, -1)

        title_note_detail.setText(note.title)
        content_note_detail.setText(note.content)

        setSupportActionBar(toolbarNoteDetail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        saveNote()
    }

    fun saveNote() {
        note.title = title_note_detail.text.toString()
        note.content = content_note_detail.text.toString()
        intent = Intent()
        intent.putExtra(EXTRA_NOTE, note)
        intent.putExtra(EXTRA_NOTE_INDEX, noteIndex)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    //setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_detail_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.delete_note_detail_menu -> {
            Toast.makeText(this,"delete action",Toast.LENGTH_LONG).show()
            true
        }
        android.R.id.home ->{
            saveNote()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
