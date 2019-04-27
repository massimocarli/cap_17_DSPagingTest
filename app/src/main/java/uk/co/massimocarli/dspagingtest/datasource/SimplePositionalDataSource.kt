package uk.co.massimocarli.dspagingtest.datasource

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import uk.co.massimocarli.dspagingtest.App
import uk.co.massimocarli.dspagingtest.model.Item
import uk.co.massimocarli.dspagingtest.networking.ItemFetcher

/**
 * Simple implementation of a PositionalDataSource. Remember to change the URL in the
 * ItemFetcher implementation
 */
class SimplePositionalDataSource(
  val itemFetcher: ItemFetcher
) : PositionalDataSource<Item>() {

  override fun loadRange(
    params: LoadRangeParams,
    callback: LoadRangeCallback<Item>
  ) {
    val result = itemFetcher.fetchItem(params.startPosition, App.PAGE_SIZE)
    callback.onResult(result.items)
  }

  override fun loadInitial(
    params: LoadInitialParams,
    callback: LoadInitialCallback<Item>
  ) {
    val result = itemFetcher.fetchItem(0, App.PAGE_SIZE)
    if (params.placeholdersEnabled) {
      callback.onResult(result.items, 0, 1000)
    } else {
      callback.onResult(result.items, 0)
    }
  }
}


class SimplePositionalDataSourceFactory(
  val itemFetcher: ItemFetcher
) : DataSource.Factory<Int, Item>() {

  override fun create(): DataSource<Int, Item> =
    SimplePositionalDataSource(itemFetcher)
}