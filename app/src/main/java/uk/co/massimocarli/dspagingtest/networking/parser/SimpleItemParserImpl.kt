package uk.co.massimocarli.dspagingtest.networking.parser

import android.util.JsonReader
import uk.co.massimocarli.dspagingtest.model.Item
import uk.co.massimocarli.dspagingtest.networking.ItemResponse
import java.io.StringReader

/**
 * Simple implementation for the ItemParser
 */
class SimpleItemParserImpl : ItemParser {

  override fun parse(json: String): ItemResponse {
    val itemList = mutableListOf<Item>()
    val jsonReader = JsonReader(StringReader(json))
    var page = 0
    var length = 10
    var nextPage: Int? = null
    var precPage: Int? = null
    jsonReader.beginObject()
    while (jsonReader.hasNext()) {
      val elementName = jsonReader.nextName()
      when (elementName) {
        "items" -> {
          jsonReader.beginArray()
          while (jsonReader.hasNext()) {
            itemList.add(parseItem(jsonReader))
          }
          jsonReader.endArray()
        }
        "page" -> {
          page = jsonReader.nextInt()
        }
        "length" -> {
          length = jsonReader.nextInt()
        }
        "nextPage" -> {
          nextPage = jsonReader.nextInt()
        }
        "precPage" -> {
          precPage = jsonReader.nextInt()
        }
        else -> {
          jsonReader.skipValue()
        }
      }
    }
    jsonReader.endObject()
    return ItemResponse(itemList, page, length, precPage, nextPage)
  }


  /**
   * Parses WindInfo
   */
  private fun parseItem(
    jsonReader: JsonReader
  ): Item {
    var id = 0
    var name = ""
    var description = ""
    var qta = 0
    jsonReader.beginObject()
    while (jsonReader.hasNext()) {
      val elementName = jsonReader.nextName()
      if ("id".equals(elementName)) {
        id = jsonReader.nextInt()
      } else if ("name".equals(elementName)) {
        name = jsonReader.nextString()
      } else if ("description".equals(elementName)) {
        description = jsonReader.nextString()
      } else if ("qta".equals(elementName)) {
        qta = jsonReader.nextInt()
      } else {
        jsonReader.skipValue()
      }
    }
    jsonReader.endObject()
    return Item(id, name, description, qta)
  }

}


