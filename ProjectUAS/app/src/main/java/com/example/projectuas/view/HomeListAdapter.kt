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
//        holder.binding.txtTitle.text =homeList[position].title
//        holder.binding.txtUsername.text = homeList[position].author
//        holder.binding.txtDesc.text = homeList[position].desc

//        holder.binding.btnRead.setOnClickListener {
//            val action = HomeListFragmentDirections.actionHomeDetailFragment(homeList[position].uuid.toString())
//            Navigation.findNavController(it).navigate(action)
//        }

//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
//        picasso.build().load(homeList[position].image).into(holder.binding.imgView, object :Callback{
//
//            override fun onSuccess() {
//                holder.binding.loadImage.visibility = View.INVISIBLE
//                holder.binding.imgView.visibility = View.VISIBLE
//            }
//
//            override fun onError(e: Exception?) {
//                Log.d("Cek", "Error")
//            }
//        })
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