package uk.co.massimocarli.dspagingtest.networking.util

import java.io.ByteArrayOutputStream
import java.io.InputStream

const val BUFFER_SIZE = 1024

class SimpleStringReaderImpl : StringReader {

  override fun asString(inputstream: InputStream): String {
    var outputBytes = ByteArrayOutputStream()
    var buffer = ByteArray(BUFFER_SIZE)
    while (true) {
      val currentSize = inputstream.read(buffer)
      if (currentSize > 0) {
        outputBytes.write(buffer, 0, currentSize)
      } else {
        break
      }
    }
    return String(outputBytes.toByteArray())
  }
}