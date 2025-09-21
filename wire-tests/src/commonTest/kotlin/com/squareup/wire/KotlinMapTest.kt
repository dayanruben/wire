/*
 * Copyright (C) 2018 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.wire

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.squareup.wire.protos.kotlin.map.Mappy
import com.squareup.wire.protos.kotlin.map.Thing
import kotlin.test.Test
import kotlin.test.fail
import okio.ByteString
import okio.ByteString.Companion.decodeHex

class KotlinMapTest {
  private val adapter = Mappy.ADAPTER

  @Test fun serialize() {
    assertThat(ByteString.of(*adapter.encode(THREE))).isEqualTo(BYTES)

    assertThat(adapter.encode(EMPTY).size).isEqualTo(0)
  }

  @Test fun deserialize() {
    assertThat(adapter.decode(BYTES)).isEqualTo(THREE)

    val empty = adapter.decode(ByteArray(0))
    assertThat(empty.things).isNotNull()
  }

  @IgnoreJs
  @IgnoreNative
  @Test
  fun mapsAreImmutable() {
    val map = mutableMapOf("one" to Thing("One"))

    val mappy = Mappy(things = map)
    try {
      (mappy.things as MutableMap<*, *>).clear()
      fail()
    } catch (_: UnsupportedOperationException) {
      // Mutation failed as expected.
    }

    // Mutate the values used to create the map. Wire should have defensive copies.
    map.clear()
    assertThat(mappy.things).isEqualTo(mapOf("one" to Thing("One")))
  }

  companion object {
    private val BYTES =
      "0a0c0a036f6e6512050a034f6e650a0c0a0374776f12050a0354776f0a100a05746872656512070a055468726565".decodeHex()
    private val EMPTY = Mappy(things = emptyMap())
    private val THREE = Mappy(
      things = mapOf(
        "one" to Thing("One"),
        "two" to Thing("Two"),
        "three" to Thing("Three"),
      ),
    )
  }
}
