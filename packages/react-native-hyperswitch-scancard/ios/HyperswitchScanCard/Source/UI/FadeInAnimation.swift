import UIKit

extension UIView {
    
    func fadeIn(_ duration: TimeInterval? = 0.4, onCompletion: (() -> Void)? = nil) {
        self.alpha = 0
        self.isHidden = false
        UIView.animate(
            withDuration: duration!,
            animations: { self.alpha = 1 },
            completion: { (_: Bool) in
                if let complete = onCompletion { complete() }
            }
        )
    }
}

