package com.example.gamesetmatch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gamesetmatch.R
import com.example.gamesetmatch.data.Player

class PlayersAdapter(
    val context: Context,
    val dataset: List<Player>
): RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>(){
    class PlayerViewHolder(view: View): ViewHolder(view){
        val playerNameTv = view.findViewById<TextView>(R.id.player_name_tv)
        val playerGradeTv = view.findViewById<TextView>(R.id.player_rating_value)
        val footballIcon = view.findViewById<ImageView>(R.id.football_iv)
        val basketballIcon = view.findViewById<ImageView>(R.id.basketball_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = dataset[position]

        holder.playerNameTv.text = item.name

        if(item.isBasketball)
            holder.basketballIcon.visibility = View.VISIBLE
        else
            holder.basketballIcon.visibility = View.GONE

        if(item.isFootball)
            holder.footballIcon.visibility = View.VISIBLE
        else
            holder.footballIcon.visibility = View.GONE

        val averageGrade = item.basketballGrade + item.footballGrade

        if(item.basketballGrade == 0){
            when(item.footballGrade){
                3 -> {
                    holder.playerGradeTv.text = "A"
                    holder.playerGradeTv.setTextColor(ContextCompat.getColor(context, R.color.green))
                }
                2 -> {
                    holder.playerGradeTv.text = "B"
                    holder.playerGradeTv.setTextColor(ContextCompat.getColor(context, R.color.yellow))
                }
                1 -> {
                    holder.playerGradeTv.text = "C"
                    holder.playerGradeTv.setTextColor(ContextCompat.getColor(context, R.color.red))
                }
            }
        }
        else if(item.footballGrade == 0){
            when(item.basketballGrade){
                3 -> {
                    holder.playerGradeTv.text = "A"
                    holder.playerGradeTv.setTextColor(ContextCompat.getColor(context, R.color.green))
                }
                2 -> {
                    holder.playerGradeTv.text = "B"
                    holder.playerGradeTv.setTextColor(ContextCompat.getColor(context, R.color.yellow))
                }
                1 -> {
                    holder.playerGradeTv.text = "C"
                    holder.playerGradeTv.setTextColor(ContextCompat.getColor(context, R.color.red))
                }
            }
        }
        else{
            if(averageGrade > 4){
                holder.playerGradeTv.text = "A"
                holder.playerGradeTv.setTextColor(ContextCompat.getColor(context, R.color.green))
            }
            else if (averageGrade > 2 ){
                holder.playerGradeTv.text = "B"
                holder.playerGradeTv.setTextColor(ContextCompat.getColor(context, R.color.yellow))
            }
            else{
                holder.playerGradeTv.text = "C"
                holder.playerGradeTv.setTextColor(ContextCompat.getColor(context, R.color.red))
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}