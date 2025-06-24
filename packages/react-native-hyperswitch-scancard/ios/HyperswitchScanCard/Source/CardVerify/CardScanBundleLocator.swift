import Foundation

/// :nodoc:
final class CardScanBundleLocator: BundleLocatorProtocol {
    static let internalClass: AnyClass = CardScanBundleLocator.self
    static let bundleName = "HyperswitchScanCardBundle"
    #if SWIFT_PACKAGE
        static let spmResourcesBundle = Bundle.module
    #endif
    static let resourcesBundle = CardScanBundleLocator.computeResourcesBundle()
}

 public protocol BundleLocatorProtocol {
    /// A final class that is internal to the bundle implementing this protocol.
    ///
    /// - Note: The class must be `final` to ensure that it can't be subclassed,
    ///   which may change the result of `bundleForClass`.
    static var internalClass: AnyClass { get }

    /// Name of the bundle.
    static var bundleName: String { get }

    /// Cached result from `computeResourcesBundle()` so it doesn't need to be recomputed.
    static var resourcesBundle: Bundle { get }

    #if SWIFT_PACKAGE
        /// SPM Bundle, if available.
        ///
        /// Implementation should be should be `Bundle.module`.
        static var spmResourcesBundle: Bundle { get }
    #endif
}
extension BundleLocatorProtocol {

    public static func computeResourcesBundle() -> Bundle {
        var ourBundle: Bundle?

        #if SWIFT_PACKAGE
            ourBundle = spmResourcesBundle
        #endif

        if ourBundle == nil {
            ourBundle = Bundle(path: "\(bundleName).bundle")
        }

        if ourBundle == nil {
            // This might be the same as the previous check if not using a dynamic framework
            if let path = Bundle(for: internalClass).path(
                forResource: bundleName,
                ofType: "bundle"
            ) {
                ourBundle = Bundle(path: path)
            }
        }

        if ourBundle == nil {
            // This will be the same as mainBundle if not using a dynamic framework
            ourBundle = Bundle(for: internalClass)
        }

        if let ourBundle = ourBundle {
            return ourBundle
        } else {
            return Bundle.main
        }
    }
}
