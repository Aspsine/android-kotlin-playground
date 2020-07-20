package com.frank.kotlin.playground

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.ColorRes

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        supportActionBar?.title = "ListView Demo";

        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = Adapter(
            listOf(
                Item("Hello", R.color.colorAccent),
                Item("Hello1", R.color.colorPrimary),
                Item("Hello2", R.color.colorPrimaryDark),
                Item("Hello3", android.R.color.black),
                Item("Hello4", android.R.color.holo_red_light),
                Item("Hello5", android.R.color.holo_blue_bright)
            )
        )
    }

    class Adapter(private var items: List<Item>) : BaseAdapter() {

        @SuppressLint("ResourceType")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var itemView = convertView
            val viewHolder: ViewHolder
            if (itemView == null) {
                itemView = LayoutInflater.from(parent!!.context)
                    .inflate(android.R.layout.activity_list_item, parent, false)
                viewHolder = ViewHolder(
                    itemView.findViewById(android.R.id.text1),
                    itemView.findViewById(android.R.id.icon)
                )
                itemView.tag = viewHolder
            } else {
                viewHolder = itemView.tag as ViewHolder
            }


            val item = this.items[position]
            viewHolder.text.text = item.name
            viewHolder.image.setBackgroundResource(item.image)

            itemView?.setOnClickListener {
                Toast.makeText(itemView.context, item.name, Toast.LENGTH_SHORT).show()
            }
            return itemView!!
        }

        override fun getItem(position: Int): Item {
            return this.items[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return this.items.size;
        }
    }

    data class Item(var name: String, @ColorRes var image: Int)

    class ViewHolder(var text: TextView, var image: ImageView)
}

