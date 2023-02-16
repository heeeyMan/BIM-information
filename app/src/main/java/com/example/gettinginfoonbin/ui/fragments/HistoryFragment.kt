package com.example.gettinginfoonbin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gettinginfoonbin.assebly.HistoryAssembly
import com.example.gettinginfoonbin.databinding.FragmentHistoryBinding
import com.example.gettinginfoonbin.ui.adapters.DetailDataAdapter
import com.example.gettinginfoonbin.ui.viewmodels.HistoryViewModel

class HistoryFragment : Fragment() {

    private var viewModel: HistoryViewModel? = null
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding
    private var historyListAdapter: DetailDataAdapter? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = HistoryAssembly().build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        recyclerView = binding?.historyList
        recyclerView?.layoutManager = LinearLayoutManager(context)
        historyListAdapter = DetailDataAdapter()
        recyclerView?.adapter = historyListAdapter

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.apply {
            historyList.observe(viewLifecycleOwner) {
                historyListAdapter?.setMoreItems(it)
            }
        }
    }
}