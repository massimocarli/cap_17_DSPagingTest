package uk.co.massimocarli.dspagingtest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uk.co.massimocarli.dspagingtest.databinding.ItemLayoutBinding
import uk.co.massimocarli.dspagingtest.model.Item

class ItemAdapter : PagedListAdapter<Item, ItemViewHolder>(ITEM_COMPARATOR) {

  companion object {
    private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
      override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem.id == newItem.id &&
            oldItem.name == newItem.name &&
            oldItem.description == newItem.description


      override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem == newItem
    }
  }

  lateinit var binding: ItemLayoutBinding

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ItemViewHolder {
    binding = ItemLayoutBinding.inflate(
      LayoutInflater.from(parent.context),
      parent,
      false
    )
    return ItemViewHolder(binding)
  }

  override fun onBindViewHolder(
    holder: ItemViewHolder,
    position: Int
  ) = holder.bindModel(getItem(position))
}

@BindingAdapter("android:model")
fun RecyclerView.setModel(data: PagedList<Item>?) {
  data?.let { newData ->
    val adapter = adapter as ItemAdapter
    adapter.submitList(data)
  }
}


