// Code generated by Wire protocol buffer compiler, do not edit.
// Source: squareup.protos.kotlin.swift_modules.SwiftModuleOneEnum in swift_module_one.proto
import Wire

public enum SwiftModuleOneEnum : Int32, CaseIterable, ProtoEnum {

    case DO_NOT_USE = 0
    case ONE = 1
    case TWO = 2

    public var description: String {
        switch self {
        case .DO_NOT_USE: return "DO_NOT_USE"
        case .ONE: return "ONE"
        case .TWO: return "TWO"
        }
    }

}

#if swift(>=5.5)
extension SwiftModuleOneEnum : Sendable {
}
#endif
