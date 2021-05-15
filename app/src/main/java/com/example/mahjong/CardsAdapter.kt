package com.example.mahjong

import android.animation.Animator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CardsAdapter(val data: ArrayList<CardData>) : RecyclerView.Adapter<CardViewHolder>() {

    private var holder1: CardViewHolder? = null
    private var holder2: CardViewHolder? = null

    private var position1: Int? = null
    private var position2: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_cards_adapter, null, false)
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val realPosition = holder.adapterPosition

        holder.cardView?.setOnClickListener {
            holder2?.let { swap(it, position2!!) }
            holder2 = holder1
            position2 = position1
            holder1 = holder
            position1 = position

            holder.cardView!!
                .animate()
                .rotationY(90f)
                .setDuration(150)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        holder.cardView?.rotationY = -90f
                        swap(holder, realPosition)


                        holder.cardView!!
                            .animate()
                            .rotationY(0f)
                            .setDuration(150)
                            .setListener(object : Animator.AnimatorListener {
                                override fun onAnimationStart(animation: Animator?) {
                                }

                                override fun onAnimationEnd(animation: Animator?) {
                                    if (holder2 != null &&
                                        holder1?.textView?.text?.isNotEmpty() == true &&
                                        holder2?.textView?.text?.isNotEmpty() == true &&
                                        holder1?.textView?.text?.toString()
                                            .equals(holder2?.textView?.text?.toString())
                                    ) {
                                        holder1?.cardView?.animate()?.alpha(0f)
                                            ?.setDuration(200)?.start()
                                        holder2?.cardView?.animate()?.alpha(0f)
                                            ?.setDuration(200)?.start()
                                        holder1 = null
                                        holder2 = null
                                    }
                                }

                                override fun onAnimationCancel(animation: Animator?) {
                                }

                                override fun onAnimationRepeat(animation: Animator?) {
                                }
                            }).start()
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                }).start()
        }
    }

    override fun getItemCount(): Int = data.size

    private fun swap(holder: CardViewHolder, realPosition: Int) {
        if (holder.textView?.text?.isEmpty() == true)
            holder.textView?.text = data[realPosition].text
        else holder.textView?.text = ""
    }
}

class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var textView: TextView? = null
    var cardView: CardView? = null

    init {
        textView = itemView.findViewById(R.id.text)
        cardView = itemView.findViewById(R.id.card)
    }
}

data class CardData(val text: String)