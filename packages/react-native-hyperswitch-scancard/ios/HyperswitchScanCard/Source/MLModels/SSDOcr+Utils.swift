import CoreML

extension SSDOcr: MLModelClassType {
}

extension SSDOcr: AsyncMLModelLoading {
    typealias ModelClassType = SSDOcr

    static func createModelClass(using model: MLModel) -> SSDOcr {
        return SSDOcr(model: model)
    }
}
