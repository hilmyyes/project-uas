package com.example.projectuas.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuas.databinding.HomeListItemBinding
import com.example.projectuas.model.News

class HomeListAdapter (val homeList: ArrayList<News>)
    :RecyclerView.Adapter<HomeListAdapter.HomeViewHolder>(), ButtonActionNavClickListener {

    class HomeViewHolder(var binding: HomeListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.news = homeList[position]
        holder.binding.navListener = this
    }

    fun updateHomeList(newHomeList: List<News>){
        homeList.clear()
        homeList.addAll(newHomeList)
        notifyDataSetChanged()
    }

    override fun onButtonActionNavClick(v: View) {
        val id = v.tag.toString()
        val action = HomeListFragmentDirections.actionHomeDetailFragment(id)
        Navigation.findNavController(v).navigate(action)
    }


}