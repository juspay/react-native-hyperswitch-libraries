import CoreGraphics
import Foundation

struct DetectedAllOcrBoxes {
    var allBoxes: [DetectedSSDOcrBox] = []

    init() {}

    func toArray() -> [[String: Any]] {
        let frameArray = self.allBoxes.map { $0.toDict() }
        return frameArray
    }

    func getBoundingBoxesOfDigits() -> [CGRect] {
        return self.allBoxes.map { $0.rect }
    }
}
