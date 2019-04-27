package uk.co.massimocarli.dspagingtest

import android.app.Application
import android.content.Context
import uk.co.massimocarli.dspagingtest.db.DBRepositoryImpl
import uk.co.massimocarli.dspagingtest.db.Repository
import uk.co.massimocarli.dspagingtest.networking.ItemFetcher
import uk.co.massimocarli.dspagingtest.networking.JSONItemFetcherImpl
import uk.co.massimocarli.dspagingtest.networking.parser.SimpleItemParserImpl
import uk.co.massimocarli.dspagingtest.networking.util.SimpleStringReaderImpl

class App : Application() {

  companion object {
    const val PAGE_SIZE = 20
  }

  lateinit var repository: Repository
  lateinit var itemFetcher: ItemFetcher

  override fun onCreate() {
    super.onCreate()
    itemFetcher = JSONItemFetcherImpl(SimpleStringReaderImpl(), SimpleItemParserImpl())
    repository = DBRepositoryImpl(itemFetcher)
  }
}


fun Context.getItemRepo() = (this.applicationContext as App).repository