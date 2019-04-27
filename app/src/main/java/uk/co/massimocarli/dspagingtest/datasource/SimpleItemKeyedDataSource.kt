package uk.co.massimocarli.dspagingtest.datasource

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import uk.co.massimocarli.dspagingtest.App
import uk.co.massimocarli.dspagingtest.model.Item
import uk.co.massimocarli.dspagingtest.networking.ItemFetcher

/**
 * Simple implementation of the ItemKeyedDataSource
 */
class SimpleItemKeyedDataSource(
  val itemFetcher: ItemFetcher
) : ItemKeyedDataSource<Int, Item>() {

  override fun loadInitial(
    params: LoadInitialParams<Int>,
    callback: LoadInitialCallback<Item>
  ) {
    val response = itemFetcher.fetchItem(0, App.PAGE_SIZE)
    callback.onResult(response.items)
  }

  override fun loadAfter(
    params: LoadParams<Int>,
    callback: LoadCallback<Item>
  ) {
    val response = itemFetcher.fetchItem(params.key, App.PAGE_SIZE)
    callback.onResult(response.items)
  }

  override fun loadBefore(
    params: LoadParams<Int>,
    callback: LoadCallback<Item>
  ) {
    // NOOP
  }

  override fun getKey(item: Item): Int {
    return (item.id / App.PAGE_SIZE) + 1
  }
}


class SimpleItemKeyedDataSourceFactory(
  val itemFetcher: ItemFetcher
) : DataSource.Factory<Int, Item>() {

  override fun create(): DataSource<Int, Item> =
    SimpleItemKeyedDataSource(itemFetcher)
}