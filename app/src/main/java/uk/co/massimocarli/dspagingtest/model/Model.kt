package uk.co.massimocarli.dspagingtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
  @PrimaryKey
  val id: Int,
  val name: String,
  val description: String,
  val qta: Int
)