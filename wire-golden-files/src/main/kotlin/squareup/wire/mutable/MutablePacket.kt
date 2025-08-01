// Code generated by Wire protocol buffer compiler, do not edit.
// Source: squareup.wire.mutable.Packet in squareup/wire/mutable_types.proto
@file:Suppress(
  "DEPRECATION",
  "RUNTIME_ANNOTATION_NOT_SUPPORTED",
)

package squareup.wire.mutable

import com.squareup.wire.FieldEncoding
import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
import com.squareup.wire.ProtoReader
import com.squareup.wire.ProtoWriter
import com.squareup.wire.ReverseProtoWriter
import com.squareup.wire.Syntax.PROTO_2
import com.squareup.wire.WireField
import com.squareup.wire.`internal`.JvmField
import kotlin.Any
import kotlin.Boolean
import kotlin.Deprecated
import kotlin.DeprecationLevel
import kotlin.Int
import kotlin.Long
import kotlin.Nothing
import kotlin.String
import kotlin.Suppress
import kotlin.UnsupportedOperationException
import kotlin.collections.List
import okio.ByteString

public class MutablePacket(
  @field:WireField(
    tag = 1,
    adapter = "squareup.wire.mutable.MutableHeader#ADAPTER",
    declaredName = "header",
    schemaIndex = 0,
  )
  public var header_: MutableHeader? = null,
  @field:WireField(
    tag = 2,
    adapter = "squareup.wire.mutable.MutablePayload#ADAPTER",
    label = WireField.Label.REPEATED,
    schemaIndex = 1,
  )
  public var payload: List<MutablePayload> = emptyList(),
  override var unknownFields: ByteString = ByteString.EMPTY,
) : Message<MutablePacket, Nothing>(ADAPTER, unknownFields) {
  @Deprecated(
    message = "Shouldn't be used in Kotlin",
    level = DeprecationLevel.HIDDEN,
  )
  override fun newBuilder(): Nothing = throw UnsupportedOperationException("newBuilder() is unsupported for mutable message types")

  override fun equals(other: Any?): Boolean {
    if (other === this) return true
    if (other !is MutablePacket) return false
    if (unknownFields != other.unknownFields) return false
    if (header_ != other.header_) return false
    if (payload != other.payload) return false
    return true
  }

  override fun hashCode(): Int {
    var result = 0
    result = unknownFields.hashCode()
    result = result * 37 + (header_?.hashCode() ?: 0)
    result = result * 37 + payload.hashCode()
    return result
  }

  override fun toString(): String {
    val result = mutableListOf<String>()
    if (header_ != null) result += """header_=$header_"""
    if (payload.isNotEmpty()) result += """payload=$payload"""
    return result.joinToString(prefix = "MutablePacket{", separator = ", ", postfix = "}")
  }

  public companion object {
    @JvmField
    public val ADAPTER: ProtoAdapter<MutablePacket> = object : ProtoAdapter<MutablePacket>(
      FieldEncoding.LENGTH_DELIMITED, 
      MutablePacket::class, 
      "type.googleapis.com/squareup.wire.mutable.Packet", 
      PROTO_2, 
      null, 
      "squareup/wire/mutable_types.proto"
    ) {
      override fun encodedSize(`value`: MutablePacket): Int {
        var size = value.unknownFields.size
        size += MutableHeader.ADAPTER.encodedSizeWithTag(1, value.header_)
        size += MutablePayload.ADAPTER.asRepeated().encodedSizeWithTag(2, value.payload)
        return size
      }

      override fun encode(writer: ProtoWriter, `value`: MutablePacket) {
        MutableHeader.ADAPTER.encodeWithTag(writer, 1, value.header_)
        MutablePayload.ADAPTER.asRepeated().encodeWithTag(writer, 2, value.payload)
        writer.writeBytes(value.unknownFields)
      }

      override fun encode(writer: ReverseProtoWriter, `value`: MutablePacket) {
        writer.writeBytes(value.unknownFields)
        MutablePayload.ADAPTER.asRepeated().encodeWithTag(writer, 2, value.payload)
        MutableHeader.ADAPTER.encodeWithTag(writer, 1, value.header_)
      }

      override fun decode(reader: ProtoReader): MutablePacket {
        var header_: MutableHeader? = null
        val payload = mutableListOf<MutablePayload>()
        val unknownFields = reader.forEachTag { tag ->
          when (tag) {
            1 -> header_ = MutableHeader.ADAPTER.decode(reader)
            2 -> payload.add(MutablePayload.ADAPTER.decode(reader))
            else -> reader.readUnknownField(tag)
          }
        }
        return MutablePacket(
          header_ = header_,
          payload = payload,
          unknownFields = unknownFields
        )
      }

      override fun redact(`value`: MutablePacket): MutablePacket = throw UnsupportedOperationException("redact() is unsupported for mutable message types")
    }

    private const val serialVersionUID: Long = 0L
  }
}
