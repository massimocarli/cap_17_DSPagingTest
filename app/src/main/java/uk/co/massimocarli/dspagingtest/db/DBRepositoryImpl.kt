package uk.co.massimocarli.dspagingtest.db

import androidx.paging.DataSource
import uk.co.massimocarli.dspagingtest.datasource.SimplePositionalDataSourceFactory
import uk.co.massimocarli.dspagingtest.model.Item
import uk.co.massimocarli.dspagingtest.networking.ItemFetcher

/**
 * Implementation of the Repository interface using DAO from Room
 */
class DBRepositoryImpl(
  val itemFetcher: ItemFetcher
) : Repository {

  override fun findAll(): DataSource.Factory<Int, Item> =
    SimplePositionalDataSourceFactory(itemFetcher)
}
