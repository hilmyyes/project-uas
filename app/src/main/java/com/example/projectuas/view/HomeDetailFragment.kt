package com.example.projectuas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projectuas.databinding.FragmentHomeDetailBinding
import com.example.projectuas.viewModel.DetailViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeDetailFragment : Fragment() {

    private lateinit var binding:FragmentHomeDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetchDetail(HomeDetailFragmentArgs.fromBundle(requireArguments()).id.toInt())

            observeViewModel()
        }
    }

    fun observeViewModel(){
        viewModel.homesDetailLD.observe(viewLifecycleOwner, Observer {
            if(it == null){
                Toast.makeText(requireContext(), "Failed Detail Page", Toast.LENGTH_SHORT).show()
            }else{
                binding.news = it
//                binding.txtTitleDetail.setText(it.title)
//                binding.txtAuthorDetail.setText("@${it.author}")
//                val picasso = Picasso.Builder(binding.root.context)
//                picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
//                picasso.build().load(binding.news!!.image).into(binding.imgViewDetail)

            }

            //Multi Page
//            val news = it.news
//            val size = news?.size ?: 0
//            var index = 0
//
//            if(size > 0){
//                binding.txtDescDetail.text = news?.get(index)
//                binding.btnBack.isEnabled = false
//
//                binding.btnNext.setOnClickListener {
//                    index++
//                    binding.txtDescDetail.text = news?.get(index)
//                    binding.btnBack.isEnabled = true
//                    if(index == size - 1) {
//                        binding.btnNext.isEnabled = false
//                    }
//                }
//
//                binding.btnBack.setOnClickListener {
//                    index--
//                    binding.txtDescDetail.text = news?.get(index)
//                    binding.btnNext.isEnabled = true
//                    if(index == 0){
//                        binding.btnBack.isEnabled = false
//                    }
//                }
//            }
        })
    }
}