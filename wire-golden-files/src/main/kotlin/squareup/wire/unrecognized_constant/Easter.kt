// Code generated by Wire protocol buffer compiler, do not edit.
// Source: squareup.wire.unrecognized_constant.Easter in squareup/wire/easter_unrecognized_enum.proto
@file:Suppress(
  "DEPRECATION",
  "RUNTIME_ANNOTATION_NOT_SUPPORTED",
)

package squareup.wire.unrecognized_constant

import com.squareup.wire.FieldEncoding
import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
import com.squareup.wire.ProtoReader
import com.squareup.wire.ProtoWriter
import com.squareup.wire.ReverseProtoWriter
import com.squareup.wire.Syntax.PROTO_3
import com.squareup.wire.WireField
import com.squareup.wire.`internal`.JvmField
import com.squareup.wire.`internal`.JvmSynthetic
import com.squareup.wire.`internal`.checkElementsNotNull
import com.squareup.wire.`internal`.immutableCopyOf
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import okio.ByteString

/**
 * Proto3 message with a proto3 enum.
 */
public class Easter private constructor(
  builder: Builder,
  unknownFields: ByteString = ByteString.EMPTY,
) : Message<Easter, Easter.Builder>(ADAPTER, unknownFields) {
  @field:WireField(
    tag = 2,
    adapter = "squareup.wire.unrecognized_constant.EasterAnimal#ADAPTER",
    jsonName = "optionalEasterAnimal",
    schemaIndex = 0,
  )
  @JvmField
  public val optional_easter_animal: EasterAnimal? = builder.optional_easter_animal

  @field:WireField(
    tag = 3,
    adapter = "squareup.wire.unrecognized_constant.EasterAnimal#ADAPTER",
    label = WireField.Label.OMIT_IDENTITY,
    jsonName = "identityEasterAnimal",
    schemaIndex = 1,
  )
  @JvmField
  public val identity_easter_animal: EasterAnimal = builder.identity_easter_animal

  @field:WireField(
    tag = 4,
    adapter = "squareup.wire.unrecognized_constant.EasterAnimal#ADAPTER",
    label = WireField.Label.REPEATED,
    jsonName = "easterAnimals",
    schemaIndex = 2,
  )
  @JvmField
  public val easter_animals: List<EasterAnimal> =
      immutableCopyOf("easter_animals", builder.easter_animals)

  override fun newBuilder(): Builder {
    val builder = Builder()
    builder.optional_easter_animal = optional_easter_animal
    builder.identity_easter_animal = identity_easter_animal
    builder.easter_animals = easter_animals
    builder.addUnknownFields(unknownFields)
    return builder
  }

  override fun equals(other: Any?): Boolean {
    if (other === this) return true
    if (other !is Easter) return false
    if (unknownFields != other.unknownFields) return false
    if (optional_easter_animal != other.optional_easter_animal) return false
    if (identity_easter_animal != other.identity_easter_animal) return false
    if (easter_animals != other.easter_animals) return false
    return true
  }

  override fun hashCode(): Int {
    var result = super.hashCode
    if (result == 0) {
      result = unknownFields.hashCode()
      result = result * 37 + (optional_easter_animal?.hashCode() ?: 0)
      result = result * 37 + identity_easter_animal.hashCode()
      result = result * 37 + easter_animals.hashCode()
      super.hashCode = result
    }
    return result
  }

  override fun toString(): String {
    val result = mutableListOf<String>()
    if (optional_easter_animal != null) result += """optional_easter_animal=$optional_easter_animal"""
    result += """identity_easter_animal=$identity_easter_animal"""
    if (easter_animals.isNotEmpty()) result += """easter_animals=$easter_animals"""
    return result.joinToString(prefix = "Easter{", separator = ", ", postfix = "}")
  }

  public class Builder : Message.Builder<Easter, Builder>() {
    @JvmField
    public var optional_easter_animal: EasterAnimal? = null

    @JvmField
    public var identity_easter_animal: EasterAnimal = EasterAnimal.EASTER_ANIMAL_DEFAULT

    @JvmField
    public var easter_animals: List<EasterAnimal> = emptyList()

    public fun optional_easter_animal(optional_easter_animal: EasterAnimal?): Builder {
      this.optional_easter_animal = optional_easter_animal
      return this
    }

    public fun identity_easter_animal(identity_easter_animal: EasterAnimal): Builder {
      this.identity_easter_animal = identity_easter_animal
      return this
    }

    public fun easter_animals(easter_animals: List<EasterAnimal>): Builder {
      checkElementsNotNull(easter_animals)
      this.easter_animals = easter_animals
      return this
    }

    override fun build(): Easter = Easter(
      builder = this,
      unknownFields = buildUnknownFields()
    )
  }

  public companion object {
    @JvmField
    public val ADAPTER: ProtoAdapter<Easter> = object : ProtoAdapter<Easter>(
      FieldEncoding.LENGTH_DELIMITED, 
      Easter::class, 
      "type.googleapis.com/squareup.wire.unrecognized_constant.Easter", 
      PROTO_3, 
      null, 
      "squareup/wire/easter_unrecognized_enum.proto"
    ) {
      override fun encodedSize(`value`: Easter): Int {
        var size = value.unknownFields.size
        size += EasterAnimal.ADAPTER.encodedSizeWithTag(2, value.optional_easter_animal)
        if (value.identity_easter_animal != squareup.wire.unrecognized_constant.EasterAnimal.EASTER_ANIMAL_DEFAULT) {
          size += EasterAnimal.ADAPTER.encodedSizeWithTag(3, value.identity_easter_animal)
        }
        size += EasterAnimal.ADAPTER.asRepeated().encodedSizeWithTag(4, value.easter_animals)
        return size
      }

      override fun encode(writer: ProtoWriter, `value`: Easter) {
        EasterAnimal.ADAPTER.encodeWithTag(writer, 2, value.optional_easter_animal)
        if (value.identity_easter_animal != squareup.wire.unrecognized_constant.EasterAnimal.EASTER_ANIMAL_DEFAULT) {
          EasterAnimal.ADAPTER.encodeWithTag(writer, 3, value.identity_easter_animal)
        }
        EasterAnimal.ADAPTER.asRepeated().encodeWithTag(writer, 4, value.easter_animals)
        writer.writeBytes(value.unknownFields)
      }

      override fun encode(writer: ReverseProtoWriter, `value`: Easter) {
        writer.writeBytes(value.unknownFields)
        EasterAnimal.ADAPTER.asRepeated().encodeWithTag(writer, 4, value.easter_animals)
        if (value.identity_easter_animal != squareup.wire.unrecognized_constant.EasterAnimal.EASTER_ANIMAL_DEFAULT) {
          EasterAnimal.ADAPTER.encodeWithTag(writer, 3, value.identity_easter_animal)
        }
        EasterAnimal.ADAPTER.encodeWithTag(writer, 2, value.optional_easter_animal)
      }

      override fun decode(reader: ProtoReader): Easter {
        val builder = Builder()
        val easter_animals = mutableListOf<EasterAnimal>()
        val unknownFields = reader.forEachTag { tag ->
          when (tag) {
            2 -> try {
              builder.optional_easter_animal(EasterAnimal.ADAPTER.decode(reader))
            } catch (e: ProtoAdapter.EnumConstantNotFoundException) {
              reader.addUnknownField(tag, FieldEncoding.VARINT, e.value.toLong())
            }
            3 -> try {
              builder.identity_easter_animal(EasterAnimal.ADAPTER.decode(reader))
            } catch (e: ProtoAdapter.EnumConstantNotFoundException) {
              reader.addUnknownField(tag, FieldEncoding.VARINT, e.value.toLong())
            }
            4 -> try {
              EasterAnimal.ADAPTER.tryDecode(reader, easter_animals)
            } catch (e: ProtoAdapter.EnumConstantNotFoundException) {
              reader.addUnknownField(tag, FieldEncoding.VARINT, e.value.toLong())
            }
            else -> reader.readUnknownField(tag)
          }
        }
        return Easter(
          builder = builder
            .easter_animals(easter_animals),
          unknownFields = unknownFields
        )
      }

      override fun redact(`value`: Easter): Easter = Easter(
        builder = value.newBuilder(),
        unknownFields = ByteString.EMPTY,
      )
    }

    private const val serialVersionUID: Long = 0L

    @JvmSynthetic
    public inline fun build(body: Builder.() -> Unit): Easter = Builder().apply(body).build()
  }
}
