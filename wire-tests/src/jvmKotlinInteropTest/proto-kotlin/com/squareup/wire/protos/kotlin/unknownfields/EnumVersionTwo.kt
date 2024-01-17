// Code generated by Wire protocol buffer compiler, do not edit.
// Source: squareup.protos.kotlin.unknownfields.EnumVersionTwo in unknown_fields.proto
@file:Suppress("DEPRECATION")

package com.squareup.wire.protos.kotlin.unknownfields

import com.squareup.wire.EnumAdapter
import com.squareup.wire.ProtoAdapter
import com.squareup.wire.Syntax.PROTO_2
import com.squareup.wire.WireEnum
import com.squareup.wire.`internal`.JvmField
import com.squareup.wire.`internal`.JvmStatic
import kotlin.Int
import kotlin.Suppress

public enum class EnumVersionTwo(
  override val `value`: Int,
) : WireEnum {
  SHREK_V2(1),
  DONKEY_V2(2),
  FIONA_V2(3),
  PUSS_IN_BOOTS_V2(4),
  ;

  public companion object {
    @JvmField
    public val ADAPTER: ProtoAdapter<EnumVersionTwo> = object : EnumAdapter<EnumVersionTwo>(
      EnumVersionTwo::class, 
      PROTO_2, 
      null
    ) {
      override fun fromValue(`value`: Int): EnumVersionTwo? = EnumVersionTwo.fromValue(`value`)
    }

    @JvmStatic
    public fun fromValue(`value`: Int): EnumVersionTwo? = when (`value`) {
      1 -> SHREK_V2
      2 -> DONKEY_V2
      3 -> FIONA_V2
      4 -> PUSS_IN_BOOTS_V2
      else -> null
    }
  }
}
