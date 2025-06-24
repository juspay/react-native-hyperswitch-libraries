import Foundation

class MachineLearningResult {
    let duration: Double
    let frames: Int
    var framePerSecond: Double {
        return Double(frames) / duration
    }

    init(
        duration: Double,
        frames: Int
    ) {
        self.duration = duration
        self.frames = frames
    }
}
