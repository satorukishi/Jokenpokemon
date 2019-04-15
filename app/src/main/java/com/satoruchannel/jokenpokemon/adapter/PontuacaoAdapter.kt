package com.satoruchannel.jokenpokemon.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.satoruchannel.jokenpokemon.R
import com.satoruchannel.jokenpokemon.entity.Pontuacao
import com.satoruchannel.jokenpokemon.listener.OnItemClickListener

class PontuacaoAdapter(private var context: Context, private var data: List<Pontuacao>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var inflater: LayoutInflater

    lateinit var clickListener: OnItemClickListener

    init {
        this.inflater = LayoutInflater.from(context)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.item_pontuacao, parent, false)
        val holder = AndroidItemHolder(view)

        return holder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current: Pontuacao = data.get(position)
        val myHolder: AndroidItemHolder
        if (holder is AndroidItemHolder) {
            myHolder = holder
            myHolder.tvTrainerName?.text = current.nome
            myHolder.tvPontuacao?.text = current.pontos
        }

    }

    fun getItem(position: Int): Pontuacao {
        return data.get(position)
    }

    private inner class AndroidItemHolder : RecyclerView.ViewHolder, View.OnClickListener {

        var tvTrainerName: TextView? = null
        var tvPontuacao: TextView? = null

        constructor(itemView: View) : super(itemView) {
            itemView.setOnClickListener(this)

            tvTrainerName = itemView.findViewById(R.id.tvTrainerName)
            tvPontuacao = itemView.findViewById(R.id.tvPontuacao)
        }

        override fun onClick(view: View?) {
            if (clickListener != null)
                clickListener.onClick(view!!, adapterPosition)
        }

    }
}