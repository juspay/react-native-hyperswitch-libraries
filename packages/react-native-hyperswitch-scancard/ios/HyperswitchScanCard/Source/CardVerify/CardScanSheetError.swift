import Foundation

/// Errors specific to the `CardImageVerificationSheet`.
public enum CardScanSheetError: Error {
    /// The provided client secret is invalid.
    case invalidClientSecret
    /// An unknown error.
    case unknown(debugDescription: String)
}

extension CardScanSheetError: LocalizedError {
    /// Localized description of the error
    public var localizedDescription: String {
        return "There was an unexpected error -- try again in a few seconds"
    }

}

extension CardScanSheetError: CustomDebugStringConvertible {
    public var debugDescription: String {
        switch self {
        case .invalidClientSecret:
            return "Invalid client secret"
        case .unknown(let debugDescription):
            return debugDescription
        }
    }
}
