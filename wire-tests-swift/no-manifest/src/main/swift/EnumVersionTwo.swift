// Code generated by Wire protocol buffer compiler, do not edit.
// Source: squareup.protos.kotlin.unknownfields.EnumVersionTwo in unknown_fields.proto
import Wire

@objc
public enum EnumVersionTwo : Int32, CaseIterable, Proto2Enum {

    case SHREK_V2 = 1
    case DONKEY_V2 = 2
    case FIONA_V2 = 3
    case PUSS_IN_BOOTS_V2 = 4

    public var description: String {
        switch self {
        case .SHREK_V2: return "SHREK_V2"
        case .DONKEY_V2: return "DONKEY_V2"
        case .FIONA_V2: return "FIONA_V2"
        case .PUSS_IN_BOOTS_V2: return "PUSS_IN_BOOTS_V2"
        }
    }

}

extension EnumVersionTwo : Sendable {
}
