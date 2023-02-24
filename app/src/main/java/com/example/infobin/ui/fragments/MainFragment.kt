package com.example.infobin.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infobin.R
import com.example.infobin.assebly.MainAssembly
import com.example.infobin.databinding.FragmentMainBinding
import com.example.infobin.datamodels.BinTextState
import com.example.infobin.datamodels.TypesItem
import com.example.infobin.utils.applyFormat
import com.example.infobin.utils.toDigital
import com.example.infobin.ui.viewmodels.MainViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class MainFragment : Fragment(), OnItemClick {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding
    private var binDataAdapter: GroupAdapter<GroupieViewHolder>? = null
    private var recyclerView: RecyclerView? = null
    private var textState = BinTextState.EMPTY
    private val viewModel: MainViewModel by lazy {
        MainAssembly(
            findNavController(),
            requireContext(),
            this
        ).build()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel.checkConnection()
        viewModel.handleEditText(binding?.binText?.text.toString())
        binDataAdapter = GroupAdapter()
        recyclerView = binding?.binDataList
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = binDataAdapter
        binding?.historyIcon?.setOnClickListener {
            viewModel.showHistory()
        }
        binding?.tryAgain?.setOnClickListener {
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.checkConnection()
        }
        binding?.clearIcon?.setOnClickListener {
            val length = binding?.binText?.text?.length ?: 0
            binding?.binText?.text?.replace(0, length, getString(R.string.empty_text))
        }
        binding?.requestButton?.background =
            context?.resources?.getDrawable(textState.buttonColor().first, null)
        context?.resources?.getColor(textState.buttonColor().second, null)
            ?.let { colorId -> binding?.requestButton?.setTextColor(colorId) }
        return binding?.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            dataList.observe(viewLifecycleOwner) {
                recyclerView?.adapter = binDataAdapter?.apply {
                    addAll(it)
                }
            }
            binTextState.observe(viewLifecycleOwner) {
                textState = it
                binding?.clearIcon?.isVisible = it.clearIconVisible()
                binding?.inputErrorText?.isVisible = it.errorTextVisible()
                binding?.inputErrorText?.text = it.messageErrorText()
                    ?.let { textId -> context?.resources?.getString(textId) }
                binding?.requestButton?.background =
                    context?.resources?.getDrawable(it.buttonColor().first, null)
                context?.resources?.getColor(it.buttonColor().second, null)
                    ?.let { colorId -> binding?.requestButton?.setTextColor(colorId) }
            }
            queryErrorTextState.observe(viewLifecycleOwner) {
                binding?.progressBar?.visibility = View.GONE
                binding?.textError?.isVisible = it.errorTextVisible()
                binding?.textError?.text = it.messageErrorText()
                    ?.let { textId -> context?.resources?.getString(textId) }
                binding?.requestButton?.background =
                    context?.resources?.getDrawable(it.buttonColor().first, null)
                context?.resources?.getColor(it.buttonColor().second, null)
                    ?.let { colorId -> binding?.requestButton?.setTextColor(colorId) }
                binding?.tryAgain?.isVisible = it.tryAgainButtonVisible()
            }

            binding?.requestButton?.setOnClickListener {
                queryTask(textState)
            }
        }
        binding?.binText?.addTextChangedListener(DataListener())
    }

    override fun onItemClick(clickedItem: String, type: TypesItem) {
        viewModel.itemClicked(clickedItem, type)
    }

    inner class DataListener : TextWatcher {
        private var result = StringBuilder()
        private var ignore = false

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(edit: Editable?) {
            viewModel.handleEditText(edit.toString())
            if (!ignore && edit?.isNotEmpty() == true) {
                result.toDigital(edit.toString())
                result.applyFormat(result.toString(), getString(R.string.template))
                ignore = true
                edit.replace(0, edit.length, result.toString())
                ignore = false
            }
        }
    }

    private fun queryTask(textState: BinTextState) {
        if (textState == BinTextState.NO_EMPTY && viewModel.checkConnection()) {
            binding?.textError?.visibility = View.GONE
            binding?.progressBar?.visibility = View.VISIBLE
            recyclerView?.adapter = binDataAdapter?.apply {
                clear()
            }
            val query = binding?.binText?.text.toString().filter { !it.isWhitespace() }
            viewModel.requestBinInfo(query)
            viewModel.addHistoryItem(query)
        }
    }
}