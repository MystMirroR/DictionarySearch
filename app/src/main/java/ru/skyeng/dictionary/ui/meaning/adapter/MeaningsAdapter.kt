package ru.skyeng.dictionary.ui.meaning.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_meaning.view.*
import ru.skyeng.dictionary.R
import ru.skyeng.dictionary.data.model.meanings.Meanings
import kotlin.properties.Delegates


class MeaningsAdapter : RecyclerView.Adapter<MeaningsAdapter.MeanViewHolder>() {

    private var meaningList: List<Meanings> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_meaning, parent, false)
        return MeanViewHolder(view, this)
    }

    override fun getItemCount(): Int = meaningList.size

    override fun onBindViewHolder(holder: MeanViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val mean: Meanings = meaningList[position]
            holder.bind(mean)
        }
    }

    fun updateData(newMeaningList: List<Meanings>) {
        meaningList = newMeaningList
    }

    class MeanViewHolder(
        itemView: View,
        private val meaningAdapter: MeaningsAdapter
    ) : RecyclerView.ViewHolder(itemView) {


        fun bind(meaning: Meanings) {
            if (meaning.transcription?.isNotEmpty()!!) {
                itemView.wordtext.text = "${meaning.text} [${meaning.transcription}]"
            } else {
                itemView.wordtext.text = "${meaning.text}"
            }
            itemView.definition.text = meaning.definition?.text
            itemView.translation.text =
                "${meaning.translation?.text} ${meaning.translation?.note ?: ""}"
            if (meaning.images?.size!! > 0) {
                Picasso.get().load("https:" + meaning.images[0]?.url).into(itemView.image)
            }

            if (meaning.examples != null && meaning.examples.isNotEmpty()) {
                val i = meaning.examples.iterator()
                var examples = ""
                while (i.hasNext()) {
                    val item = i.next()
                    if (item != null) {
                        examples += item.text?.replace("[", "<font color='red'>")?.replace(
                            "]", "</font>"
                        ) ?: ""
                    }
                    if (i.hasNext()) {
                        examples += "<br/><br/>"
                    }
                }
                itemView.examples.text = Html.fromHtml("Examples:<br/><br/>$examples")
            }


        }
    }


}