package com.sachtech.datingapp.customcountrypicker


import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView


import com.l4digital.fastscroll.FastScroller
import com.sachtech.datingapp.R


import java.util.ArrayList
import java.util.Arrays
import java.util.Collections
import java.util.Comparator
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.country_item_image.view.checkbox

class CountryPickAdapter internal constructor(private val context: Context) :
    RecyclerView.Adapter<CountryPickAdapter.ViewHolder>(), FastScroller.SectionIndexer,
    Comparator<Country>, Filterable {
    var countries: List<Country>? = null
    var selectedCountry:ArrayList<String>?=null
    private var filteredCountries: List<Country>? = null
    private var filter: Filter? = null
var isChecked=false
    var isFlagShown = CountryPicker.DEFAULT_FLAG_SHOWN
        set(shown) {
            field = shown
            notifyDataSetChanged()
        }
    var isDialShown = CountryPicker.DEFAULT_DIAL_SHOWN
        set(shown) {
            field = shown
            notifyDataSetChanged()
        }
    var listener: CountryPicker.OnSelectedListener? = null

    init {
        replaceItems(ArrayList(Arrays.asList(*Country.values())))
    }

    fun setItems(countries: List<Country>) {
        replaceItems(countries)
        notifyDataSetChanged()
    }

    private fun replaceItems(countries: List<Country>) {
        Collections.sort(countries, this)
        this.countries = countries
        this.filteredCountries = countries
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            TYPE_TEXT -> return TextHolder(
                inflater.inflate(
                    R.layout.country_item_text,
                    parent, false
                )
            )
            TYPE_TEXT_IMAGE -> return TextImageHolder(
                inflater.inflate(
                    R.layout.country_item_image,
                    parent, false
                )
            )
            TYPE_TEXT_EMOJI -> return TextEmojiHolder(
                inflater.inflate(
                    R.layout.country_item_emoji,
                    parent, false
                )
            )
            else -> return ViewHolder(
                inflater.inflate(
                    R.layout.country_item_empty,
                    parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder !is TextHolder) {
            return
        }
        selectedCountry= ArrayList()
      //  val countryList=ArrayList<Country>()
        val country = getItem(position)
        if (holder is TextImageHolder) {
            Log.e("image", country.dial)
            holder.imageView.setImageResource(country.getFlagDrawableRes(context))
           /* holder.itemView.checkbox.setOnClickListener {
                if (listener != null) {
                    listener!!.selectedSubscription(country)
                }
            }*/
        } else if (holder is TextEmojiHolder) {
            Log.e("flags", country.flagSymbols)
            holder.emojiView.text = country.flagSymbols
          /*  holder.itemView.checkbox.setOnClickListener {
                if (listener != null) {
                    listener!!.selectedSubscription(country)
                }
            }*/
        }
        holder.textView.text = if (this.isDialShown)
            String.format("%s (%s)", country.getName(context), country.getDial())
        else
            country.getName(context)
        holder.itemView.checkbox.setOnClickListener {
            getUpdatedList(country.getName(context))
        }
    }

    fun getUpdatedList(name: String) {
        selectedCountry?.add(name)
    }

    override fun getItemViewType(position: Int): Int {
        if (filteredCountries!!.isEmpty()) {
            return TYPE_EMPTY
        }
        if (!this.isFlagShown || Build.VERSION.SDK_INT < 21) {
            return TYPE_TEXT
        }
        return if (filteredCountries!![position]
                .isFlagDrawableAvailable(context)
        )
            TYPE_TEXT_IMAGE
        else
            TYPE_TEXT_EMOJI
    }

    override fun getItemCount(): Int {
        return if (filteredCountries!!.isEmpty()) {
            1
        } else filteredCountries!!.size
    }

    override fun getSectionText(position: Int): CharSequence {
        return getItem(position).getName(context)[0].toString()
    }

    override fun compare(country: Country, other: Country): Int {
        return country.getName(context).compareTo(other.getName(context))
    }

    override fun getFilter(): Filter {
        if (filter == null) {
            filter = object : Filter() {
                override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                    val values: List<Country>?
                    val s = charSequence.toString()
                    if (s.isEmpty()) {
                        values = countries
                    } else {
                        val filteredList = ArrayList<Country>()
                        for (country in countries!!) {
                            val locale = country.toLocale(context)
                            if (country.getName(context).toLowerCase(locale)
                                    .contains(s.toLowerCase(locale))
                            ) {
                                filteredList.add(country)
                            }
                        }
                        values = filteredList
                    }

                    val filterResults = Filter.FilterResults()
                    filterResults.values = values
                    return filterResults
                }

                override fun publishResults(
                    charSequence: CharSequence,
                    results: FilterResults
                ) {
                    filteredCountries = results.values as List<Country>?
                    notifyDataSetChanged()
                }
            }
        }
        return filter as Filter
    }

    private fun getItem(index: Int): Country {
        return filteredCountries!![index]
    }

    internal class TextImageHolder(itemView: View) : TextHolder(itemView) {
        val imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.icon)
        }
    }

    internal class TextEmojiHolder(itemView: View) : TextHolder(itemView) {
        val emojiView: TextView

        init {
            emojiView = itemView.findViewById(R.id.text2)
        }
    }

    internal open class TextHolder(itemView: View) : ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text1)
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {

        private val TYPE_TEXT = 0
        private val TYPE_TEXT_IMAGE = 1
        private val TYPE_TEXT_EMOJI = 2
        private val TYPE_EMPTY = 3
    }
}