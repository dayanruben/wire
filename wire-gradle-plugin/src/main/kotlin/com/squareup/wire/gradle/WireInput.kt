/*
 * Copyright (C) 2019 Square, Inc.
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
package com.squareup.wire.gradle

import com.squareup.wire.gradle.WireExtension.ProtoRootSet
import com.squareup.wire.schema.Location
import java.io.EOFException
import java.io.File
import java.io.RandomAccessFile
import kotlin.io.relativeTo
import kotlin.io.startsWith
import org.gradle.api.internal.file.FileOperations

internal val List<ProtoRootSet>.inputLocations: List<InputLocation>
  get() = map { rootSet -> rootSet.inputLocation() }

private fun ProtoRootSet.inputLocation(): InputLocation {
  val includes = when {
    includes.isEmpty() && excludes.isEmpty() -> listOf("**/*.proto")
    else -> includes
  }
  return InputLocation(configuration, includes, excludes)
}

/**
 * Expand a jar or directory to the specific `.proto` files we want to build, applying includes and
 * excludes.
 */
internal fun InputLocation.toLocations(
  fileOperations: FileOperations,
  projectFile: File,
): List<Location> {
  return configuration.files.flatMap { base ->
    return@flatMap buildList {
      val fileTree = when {
        base.isZip -> fileOperations.zipTree(base)
        base.isDirectory -> fileOperations.fileTree(base)
        else -> throw IllegalArgumentException(
          """
          |Invalid path string: "${if (base.startsWith(projectFile)) { base.relativeTo(projectFile) } else { base }}".
          |For individual files, use the following syntax:
          |wire {
          |  sourcePath {
          |    srcDir("dirPath")
          |    include("relativePath")
          |  }
          |}
          """.trimMargin(),
        )
      }

      fileTree
        .matching { pattern ->
          when {
            includes.isNotEmpty() || excludes.isNotEmpty() -> {
              pattern.include(*includes.toTypedArray())
              pattern.exclude(*excludes.toTypedArray())
            }

            else -> pattern.include("**/*.proto")
          }
        }
        .visit { entry ->
          if (!entry.isDirectory) add(Location(base.path, entry.path))
        }
    }
  }
}

internal val File.isZip: Boolean
  get() {
    if (!exists() || !isFile) {
      return false
    }
    if (path.endsWith(".jar") || path.endsWith(".zip")) {
      return true
    }
    return try {
      // See "magic numbers": https://en.wikipedia.org/wiki/ZIP_(file_format)
      val signature = RandomAccessFile(this, "r").use { it.readInt() }
      signature == 0x504B0304 || signature == 0x504B0506 || signature == 0x504B0708
    } catch (_: EOFException) {
      false
    }
  }
