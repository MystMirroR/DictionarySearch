package ru.skyeng.dictionary.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.astritveliu.boom.Boom
import kotlinx.android.synthetic.main.item_word.view.*
import ru.skyeng.dictionary.R
import ru.skyeng.dictionary.data.model.search.Word
import kotlin.properties.Delegates

class WordsAdapter : RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {

    private var wordsList: List<Word> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }
    private var mOnItemClickListener: AdapterView.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_word, parent, false)
        return WordViewHolder(view, this)
    }

    fun setOnItemClickListener(onItemClickListener: AdapterView.OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int = wordsList.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val word: Word = wordsList[position]
            holder.bind(word)
        }
    }

    fun getItemById(position: Int): Word {
        return wordsList[position]
    }

    fun updateData(newWordList: List<Word>) {
        wordsList = newWordList
    }

    class WordViewHolder(
        itemView: View,
        private val wordsAdapter: WordsAdapter
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {

            wordsAdapter.onItemHolderClick(this)

        }

        fun bind(word: Word) {
            itemView.setOnClickListener(this)
            Boom(itemView)
            if (word.meanings?.get(0)?.transcription?.isNotEmpty()!!) {
                itemView.word.text = "${word.text} [${word.meanings.get(0)?.transcription}]"
            } else {
                itemView.word.text = "${word.text}"
            }

        }
    }

    private fun onItemHolderClick(wordViewHolder: WordViewHolder) {

        if (mOnItemClickListener != null) {
            mOnItemClickListener!!.onItemClick(
                null, wordViewHolder.itemView, wordViewHolder.adapterPosition,
                wordViewHolder.itemId
            )
        }
    }
}