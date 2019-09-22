package ru.skyeng.dictionary.ui.meaning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_meaning.*
import kotlinx.coroutines.Job
import org.koin.android.viewmodel.ext.android.viewModel
import ru.skyeng.dictionary.R
import ru.skyeng.dictionary.data.model.meanings.Meanings
import ru.skyeng.dictionary.ui.meaning.adapter.MeaningsAdapter

class MeaningFragment : Fragment() {

    companion object {
        fun newInstance() = MeaningFragment()
    }

    private val viewModel: MeaningViewModel by viewModel()
    private lateinit var meaningAdapter: MeaningsAdapter
    private var textChangedJob: Job? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_meaning, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        meaningAdapter = MeaningsAdapter()
        meanings.apply {
            layoutManager =
                LinearLayoutManager(this@MeaningFragment.activity, RecyclerView.VERTICAL, false)
            adapter = meaningAdapter
        }
        initViewModel()
        arguments?.getString("ids", "")?.let { getMeaningInfo(it) }
    }

    private fun initViewModel() {
        viewModel.meaningList.observe(this, Observer { newMeaningInfo ->
            updateData(newMeaningInfo)
        })
        viewModel.showLoading.observe(this, Observer { showLoading ->
            // if (showLoading!!) //TODO Loading progress
        })
        viewModel.showError.observe(this, Observer { showError ->
            Toast.makeText(activity, showError, Toast.LENGTH_SHORT).show()
        })
    }

    private fun updateData(newMeaningInfo: List<Meanings>?) {
        if (newMeaningInfo != null) {
            meaningAdapter.updateData(newMeaningInfo)
        }
        toolbar.title = arguments?.getString("word", "")
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        if (newMeaningInfo?.get(0)?.images?.size!! > 0) {
            Picasso.get().load("https:" + (newMeaningInfo[0].images!![0]?.url ?: ""))
                .into(app_bar_image)
        }
    }


    private fun getMeaningInfo(ids: String) {
        viewModel.getMeaning(ids)
    }
}
