package uk.co.massimocarli.dspagingtest.db

import androidx.paging.DataSource
import uk.co.massimocarli.dspagingtest.model.Item

/**
 * Repository pattern implementation
 */
interface Repository {

  fun findAll(): DataSource.Factory<Int, Item>
}
