import CoreGraphics

extension CGRect {
    func centerY() -> CGFloat {
        return (minY / 2 + maxY / 2)
    }

    func centerX() -> CGFloat {
        return (minX / 2 + maxX / 2)
    }
}
