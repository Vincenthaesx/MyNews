package com.example.megaport.mynews.controllers.utils

import android.view.View

typealias OnItemClickListener = (androidx.recyclerview.widget.RecyclerView, position: Int, v: View) -> Unit
class ItemClickSupport private constructor(private val mRecyclerView: androidx.recyclerview.widget.RecyclerView, mItemID: Int) {
    private var myItemClickListener: OnItemClickListener? = null
    private var myItemLongClickListener: OnItemLongClickListener? = null

    private val myClickListener = View.OnClickListener { v ->
        if (myItemClickListener != null) {
            val holder = mRecyclerView.getChildViewHolder(v)
            myItemClickListener!!(mRecyclerView, holder.adapterPosition, v)
        }
    }

    private val myLongClickListener = View.OnLongClickListener { v ->
        if (myItemLongClickListener != null) {
            val holder = mRecyclerView.getChildViewHolder(v)
            return@OnLongClickListener myItemLongClickListener!!.onItemLongClicked(mRecyclerView, holder.adapterPosition, v)
        }
        false
    }

    private val mAttachListener = object : androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            if (myItemClickListener != null) {
                view.setOnClickListener(myClickListener)
            }
            if (myItemLongClickListener != null) {
                view.setOnLongClickListener(myLongClickListener)
            }
        }

        override fun onChildViewDetachedFromWindow(view: View) {

        }
    }

    init {
        mRecyclerView.setTag(mItemID, this)
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener)
    }

    fun setOnItemClickListener(listener: OnItemClickListener): ItemClickSupport {
        myItemClickListener = listener
        return this
    }

    internal interface OnItemLongClickListener {

        fun onItemLongClicked(recyclerView: androidx.recyclerview.widget.RecyclerView, position: Int, v: View): Boolean
    }

    companion object {

        fun addTo(view: androidx.recyclerview.widget.RecyclerView, itemID: Int): ItemClickSupport {
            var support: ItemClickSupport? = view.getTag(itemID) as? ItemClickSupport
            if (support == null) {
                support = ItemClickSupport(view, itemID)
            }
            return support
        }

    }

}
