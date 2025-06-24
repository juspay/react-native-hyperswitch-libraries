/// Data structure used to store all the detected boxes per frame or scan

struct DetectedAllBoxes {
    var allBoxes: [DetectedSSDBox] = []

    init() {}

    func toArray() -> [[String: Any]] {
        let frameArray = self.allBoxes.map { $0.toDict() }
        return frameArray
    }
}
