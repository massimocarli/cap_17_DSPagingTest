package uk.co.massimocarli.dspagingtest.datasource

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import uk.co.massimocarli.dspagingtest.App
import uk.co.massimocarli.dspagingtest.model.Item
import uk.co.massimocarli.dspagingtest.networking.ItemFetcher

const val TAG = "DataSource"

/**
 * This uses the service which provides information about the next and prec pages
 */
class SimplePageKeyedDataSource(
  val itemFetcher: ItemFetcher
) : PageKeyedDataSource<Int, Item>() {

  override fun loadInitial(
    params: LoadInitialParams<Int>,
    callback: LoadInitialCallback<Int, Item>
  ) {
    // We load the initial data
    log("loadInitial")
    val response = itemFetcher.fetchItem(0, App.PAGE_SIZE)
    callback.onResult(response.items, null, response.nextPage)
  }

  override fun loadAfter(
    params: LoadParams<Int>,
    callback: LoadCallback<Int, Item>
  ) {
    log("loadAfter")
    val response = itemFetcher.fetchItem(params.key, App.PAGE_SIZE)
    callback.onResult(response.items, response.nextPage)
  }

  override fun loadBefore(
    params: LoadParams<Int>,
    callback: LoadCallback<Int, Item>
  ) {
    log("loadBefore")
    val response = itemFetcher.fetchItem(params.key, App.PAGE_SIZE)
    callback.onResult(response.items, response.precPage)
  }

  private fun log(msg: String) {
    Log.d(TAG, "$msg in Thread ${Thread.currentThread()}")
  }
}

/**
 * This is the Factory responsible to create the DataSource
 */
class SimplePageKeyedFactory(
  val itemFetcher: ItemFetcher
) : DataSource.Factory<Int, Item>() {

  override fun create(): DataSource<Int, Item> =
    SimpleItemKeyedDataSource(itemFetcher)
}