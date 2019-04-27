package uk.co.massimocarli.dspagingtest.view

import androidx.recyclerview.widget.DiffUtil
import uk.co.massimocarli.dspagingtest.model.Item
import java.util.*

class ItemDiff(val before: List<Item>, val after: List<Item>) : DiffUtil.Callback() {

  // They are the same if they have the same Id, name and description
  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val before = before[oldItemPosition]
    val after = after[newItemPosition]
    return Objects.equals(before, after)
  }

  override fun getOldListSize(): Int = before.size

  override fun getNewListSize(): Int = after.size

  // They are the same if they have the same Id
  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
    areItemsTheSame(oldItemPosition, newItemPosition)

}