package com.example.infobin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infobin.R
import com.example.infobin.assebly.HistoryAssembly
import com.example.infobin.databinding.FragmentHistoryBinding
import com.example.infobin.ui.adapters.DetailDataAdapter
import com.example.infobin.ui.viewmodels.HistoryViewModel

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding
    private var historyListAdapter: DetailDataAdapter? = null
    private var recyclerView: RecyclerView? = null
    private val viewModel: HistoryViewModel by lazy { HistoryAssembly().build() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        recyclerView = binding?.historyList
        recyclerView?.layoutManager = LinearLayoutManager(context)
        historyListAdapter = DetailDataAdapter()
        recyclerView?.adapter = historyListAdapter
        binding?.textError?.text = getString(R.string.history_empty)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            this.getHistoryList()
            historyList.observe(viewLifecycleOwner) {
                historyListAdapter?.setMoreItems(it)
            }
            historyListState.observe(viewLifecycleOwner) {
                binding?.textError?.isVisible = it.errorTextVisible()
            }
        }
    }
}