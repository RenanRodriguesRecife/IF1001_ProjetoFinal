package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    val items = arrayListOf(
        0 to "Item A",
        2 to "Item B",
        3 to "Item C",
        4 to "Item D"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)


        val adapter = MyAdapter(items)

        view.findViewById<RecyclerView>(R.id.RecycleView).adapter = adapter

        return view
    }



}

class MyAdapter(private val items: List<Pair<Int, String>>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            if (viewType == 0) {
                LayoutInflater.from(parent.context).inflate(R.layout.item_b, parent, false)

            } else {
                LayoutInflater.from(parent.context).inflate(R.layout.item_a, parent, false)

            }
        )
    }
    override fun getItemViewType(position: Int): Int {
        return items[position].first
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

}

