package uk.co.massimocarli.dspagingtest.networking.parser

import uk.co.massimocarli.dspagingtest.networking.ItemResponse

/**
 * Abstraction for the Parser
 */
interface ItemParser {

  fun parse(json: String): ItemResponse
}