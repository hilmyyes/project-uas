package com.example.projectuas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectuas.R
import com.example.projectuas.databinding.FragmentHomeListBinding
import com.example.projectuas.viewModel.HomeViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeListFragment : Fragment() {
    private lateinit var viewModel:HomeViewModel
    private lateinit var binding: FragmentHomeListBinding
    private val homeListAdapter = HomeListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.itemHome)
            }
        })

        viewModel =ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.refresh()

        binding.recViewHome.layoutManager = LinearLayoutManager(context)
        binding.recViewHome.adapter = homeListAdapter

        binding.refreshLayout.setOnRefreshListener {
            binding.recViewHome.visibility =View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressBar.visibility =View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.homesLD.observe(viewLifecycleOwner, Observer { homeListAdapter.updateHomeList(it) })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.recViewHome.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            else{
                binding.recViewHome.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.homesLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.txtError.visibility = View.VISIBLE
            }
            else{
                binding.txtError.visibility = View.GONE
            }
        })
    }

}