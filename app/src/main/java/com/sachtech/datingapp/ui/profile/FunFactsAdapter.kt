/*
package com.sachtech.datingapp.ui.profile


import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.sachtech.datingapp.R
import com.sachtech.datingapp.ui.home.dialog.FunFactDialog
import gurtek.mrgurtekbase.adapter.BaseAdapter
import kotlinx.android.synthetic.main.item_fun_fact.view.*

class FunFactsAdapter(
    val funFactList: ArrayList<String>,
    val context: FunFactFragment?
) : BaseAdapter<String>(R.layout.item_fun_fact) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.funfact.text=funFactList[position]
        holder.itemView.setOnClickListener {
            openDialog(holder.itemView.funfact.text.toString())
        }
    }

    private fun openDialog(toString: String) {
        val dialog = FunFactDialog()
        dialog.setStyle(DialogFragment.STYLE_NO_FRAME, 0)
        val bundle=Bundle()
        bundle.putString("fact_name",toString)
        dialog.arguments=bundle
      dialog.show(context?.fragmentManager!!,"fun fact")
    }

    override fun getItemCount(): Int {
        return funFactList.size
    }
}
*/
