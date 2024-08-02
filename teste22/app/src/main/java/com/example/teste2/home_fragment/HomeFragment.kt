package com.example.teste2.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teste2.R


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    val items = arrayListOf(
        0 to "Item A",
        1 to "Item B",
        2 to "Item C",
        3 to "Item D",
        4 to "Item D",
        2 to "Item C",

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
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            if (viewType == 0) {
                LayoutInflater.from(parent.context).inflate(R.layout.item_b, parent, false)

            }else if (viewType == 1) {
                LayoutInflater.from(parent.context).inflate(R.layout.item_c, parent, false)

            }else if (viewType == 2) {
                LayoutInflater.from(parent.context).inflate(R.layout.item_d, parent, false)

            }  else {
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

