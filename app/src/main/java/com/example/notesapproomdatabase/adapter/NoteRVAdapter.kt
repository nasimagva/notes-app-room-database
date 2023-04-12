package com.example.notesapproomdatabase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapproomdatabase.MainActivity
import com.example.notesapproomdatabase.model.Note
import com.example.notesapproomdatabase.R

class NoteRVAdapter(
    val context: Context,
    val noteClickDeleteInterface: MainActivity,
    val noteClickInterface: NoteClickInterface
): RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {
    private val allNotes = ArrayList<Note>()
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNoteTitle)
        val timeTV = itemView.findViewById<TextView>(R.id.idTVTimeStamp)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    interface NoteClickInterface{
        fun onNoteClick(note: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTV.text = allNotes.get(position).noteTitle
        holder.timeTV.text = "Last Updated : "+allNotes.get(position).timeStamp

        holder.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}