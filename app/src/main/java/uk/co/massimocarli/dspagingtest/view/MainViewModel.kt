package uk.co.massimocarli.dspagingtest.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import uk.co.massimocarli.dspagingtest.App.Companion.PAGE_SIZE
import uk.co.massimocarli.dspagingtest.db.Repository
import uk.co.massimocarli.dspagingtest.model.Item
import java.util.concurrent.Executors

class MainViewModel(
  app: Application,
  repository: Repository
) : AndroidViewModel(app) {

  val liveData: LiveData<PagedList<Item>>

  init {
    val factory = repository.findAll()
    val pagedListConfig = PagedList.Config.Builder()
      .setPageSize(PAGE_SIZE)
      .setEnablePlaceholders(true)
      .build()
    liveData = LivePagedListBuilder(factory, pagedListConfig)
      .setFetchExecutor(Executors.newFixedThreadPool(5))
      .build()
  }
}

class RepositoryModelFactory(
  val app: Application,
  val repository: Repository
) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return modelClass.getConstructor(Application::class.java, Repository::class.java)
      .newInstance(app, repository)
  }
}
