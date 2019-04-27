package uk.co.massimocarli.dspagingtest.networking

import uk.co.massimocarli.dspagingtest.model.Item

/**
 * Response from the ItemFetcher
 */
data class ItemResponse(
  val items: List<Item>,
  val page: Int,
  val length: Int,
  val precPage: Int? = null,
  val nextPage: Int? = null
)

val ITEM_ERROR_RESPONSE = ItemResponse(emptyList(), -1, -1, -1, -1)