package ru.skyeng.dictionary.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.skyeng.dictionary.R
import ru.skyeng.dictionary.data.model.search.Word
import kotlin.properties.Delegates

class WordsAdapter : RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {

    // Our data list is going to be notified when we assign a new list of data to it
    private var wordsList: List<Word> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_word, parent, false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int = wordsList.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val word: Word = wordsList[position]
            holder.bind(word)
        }
    }

    fun updateData(newWordList: List<Word>) {
        wordsList = newWordList
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(word: Word) {
            //TODO
        }
    }
}