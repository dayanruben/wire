// Code generated by Wire protocol buffer compiler, do not edit.
// Source: squareup.protos.kotlin.OtherMessageWithStatus in same_name_enum.proto
import Wire

public struct OtherMessageWithStatus {

    public var unknownFields: UnknownFields = .init()

    public init() {
    }

}

#if !WIRE_REMOVE_EQUATABLE
extension OtherMessageWithStatus : Equatable {
}
#endif

#if !WIRE_REMOVE_HASHABLE
extension OtherMessageWithStatus : Hashable {
}
#endif

extension OtherMessageWithStatus : Sendable {
}

extension OtherMessageWithStatus : ProtoDefaultedValue {

    public static var defaultedValue: Self {
        .init()
    }
}

extension OtherMessageWithStatus : ProtoMessage {

    public static func protoMessageTypeURL() -> String {
        return "type.googleapis.com/squareup.protos.kotlin.OtherMessageWithStatus"
    }

}

extension OtherMessageWithStatus : Proto2Codable {

    public init(from protoReader: ProtoReader) throws {
        let token = try protoReader.beginMessage()
        while let tag = try protoReader.nextTag(token: token) {
            switch tag {
            default: try protoReader.readUnknownField(tag: tag)
            }
        }
        self.unknownFields = try protoReader.endMessage(token: token)

    }

    public func encode(to protoWriter: ProtoWriter) throws {
        try protoWriter.writeUnknownFields(unknownFields)
    }

}

#if !WIRE_REMOVE_CODABLE
extension OtherMessageWithStatus : Codable {

    public enum CodingKeys : CodingKey {
    }

}
#endif

/**
 * Subtypes within OtherMessageWithStatus
 */
extension OtherMessageWithStatus {

    @objc
    public enum Status : Int32, CaseIterable, Proto2Enum {

        case A = 1

        public var description: String {
            switch self {
            case .A: return "A"
            }
        }

    }

}

extension OtherMessageWithStatus.Status : Sendable {
}
