package uk.co.massimocarli.dspagingtest.view

import androidx.recyclerview.widget.RecyclerView
import uk.co.massimocarli.dspagingtest.databinding.ItemLayoutBinding
import uk.co.massimocarli.dspagingtest.model.Item

class ItemViewHolder(
  val binding: ItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

  fun bindModel(item: Item?) {
    binding.item = item
  }
}
