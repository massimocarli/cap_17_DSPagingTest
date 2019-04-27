package uk.co.massimocarli.dspagingtest.networking.util

import java.io.InputStream

interface StringReader {

  /**
   * Reads a String from an InputStream
   */
  fun asString(inputstream: InputStream): String
}