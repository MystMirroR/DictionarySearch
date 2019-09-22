package ru.skyeng.dictionary.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import ru.skyeng.dictionary.R
import ru.skyeng.dictionary.ui.search.adapter.WordsAdapter

class SearchFragment : Fragment(), AdapterView.OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var ids = ""

        for (m in wordsAdapter.getItemById(position).meanings!!) {
            if (m != null) {
                ids = ids + m.id + ","
            }

        }
        ids.substring(0, ids.length - 1)
        val bundle = bundleOf("ids" to ids, "word" to (wordsAdapter.getItemById(position).text))
        findNavController().navigate(R.id.action_searchFragment_to_meaningFragment, bundle)


    }

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var wordsAdapter: WordsAdapter
    private var textChangedJob: Job? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordsAdapter = WordsAdapter()
        wordslist.apply {
            layoutManager =
                LinearLayoutManager(this@SearchFragment.activity, RecyclerView.VERTICAL, false)
            adapter = wordsAdapter
        }
        wordsAdapter.setOnItemClickListener(this)
        initViewModel()
        val watcher = object : TextWatcher {
            private var searchFor = ""

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return

                searchFor = searchText

                textChangedJob = GlobalScope.launch {
                    delay(350)
                    if (searchText != searchFor)
                        return@launch
                    search(searchText)
                }
            }

            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit
        }
        searchedit.addTextChangedListener(watcher)
    }

    private fun initViewModel() {
        viewModel.wordsList.observe(this, Observer { newWordList ->
            wordsAdapter.updateData(newWordList)
        })
        viewModel.showLoading.observe(this, Observer { showLoading ->
            //  if (showLoading!!) //TODO Loading progress
        })
        // Observe showError value and display the error message as a Toast
        viewModel.showError.observe(this, Observer { showError ->
            Toast.makeText(activity, showError, Toast.LENGTH_SHORT).show()
        })
    }


    private fun search(searchText: String) {
        viewModel.searchMeaningOfWord(searchText)
    }
}
