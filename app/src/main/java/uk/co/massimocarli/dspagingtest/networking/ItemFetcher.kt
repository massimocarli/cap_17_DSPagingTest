package uk.co.massimocarli.dspagingtest.networking

/**
 * Abstraction for the Fetcher reponsible to access the server and get the response
 */
interface ItemFetcher {

  /**
   * Fetches the Item from the server
   */
  fun fetchItem(page: Int = 0, length: Int = 20): ItemResponse
}